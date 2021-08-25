import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The service class contains all methods and services to manipulate the given raw data in order to print
 * the calculated session lists to the console.
 *
 */

public class Services {

    public Services(){
    }

    /**
     * Import the actual talk list provided in a file and add it to the rawSessionList.
     *
     * @param conferenceTrackManager the class calling this method
     * @param nameFile the file containing the actual talk list
     */
    static void importTracks(ConferenceTrackManager conferenceTrackManager, String nameFile) {

        File file = new File(nameFile);

        if (!file.canRead() || !file.isFile()){
            System.exit(0);
        }
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(file));
            String line;
            while ((line = input.readLine()) != null) {
                conferenceTrackManager.rawSessionsList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * Creates talk objects out of the given rawSessionList and store these in an ArrayList.
     * Critical is here that the talk title must not contain any number.
     *
     * @param conferenceTrackManager the class calling this method
     * @param rawSessionList the list containing all given talks
     */
    static void createSessionList(ConferenceTrackManager conferenceTrackManager, ArrayList<String> rawSessionList) {

        // looping throuhg the list containing the raw data
        for (int i=0; i < rawSessionList.size(); i++){
            String rawSessionEdit = rawSessionList.get(i);
            Matcher matcher = Pattern.compile("\\d+").matcher(rawSessionEdit);
            matcher.find();

            // Find and extract the length of the talk
            try {
                int duration = Integer.parseInt(matcher.group());
                Talk talk = new Talk(rawSessionEdit, duration);
                conferenceTrackManager.talkList.add(talk);

            // Fallback if no length in minutes is provided;
            // per given definition this is a lightning talk with a length of 5 minutes
            } catch (IllegalStateException e){
                Talk talk = new Talk(rawSessionEdit, 5);
                conferenceTrackManager.talkList.add(talk);
            }
        }
    }

    /**
     * Main method to create the session lists.
     * Initialise some params for further usage.
     *
     * @param conferenceTrackManager the class calling this method
     * @param talkArrayList contains all session objects
     */
    static void createTrackLists(ConferenceTrackManager conferenceTrackManager, ArrayList<Talk> talkArrayList){
        int id = 1;
        ArrayList<Session> sessionList = new ArrayList<>();

        // building the session lists
        buildCompleteTrackLists(id, talkArrayList, sessionList);

        // printing the generated session lists to the console
        printTrackLists(sessionList);

    }

    /**
     * Prints the session lists to the console.
     *
     * @param sessionList the generated session lists
     */
    private static void printTrackLists(ArrayList<Session> sessionList) {
        // iterating through the track lists
        for (Session session : sessionList) {

            System.out.println("Track " + session.getTrackID() + ":");

            // printing the morning session
            for (int m = 0; m < session.getMorningSession().size(); m++) {
                // converting the time format to US format
                LocalTime toConvert = session.getMorningSession().get(m).getStartingTime();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("KK:mma").withLocale(Locale.US);
                String convertedToUS = toConvert.format(timeFormatter);

                System.out.println(convertedToUS + " " + session.getMorningSession().get(m).getTitle());
            }

            System.out.println("12:00PM Lunch");

            // printing the afternoon session
            for (int j = 0; j < session.getAfternoonSession().size(); j++) {
                // converting the time format to US format
                LocalTime toConvert = session.getAfternoonSession().get(j).getStartingTime();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("KK:mma").withLocale(Locale.US);
                String convertedToUS = toConvert.format(timeFormatter);

                System.out.println(convertedToUS + " " + session.getAfternoonSession().get(j).getTitle());
            }

            System.out.println("05:00PM Networking Event\n");
        }
    }

    /**
     * Iterates recursively through the sesssionArrayList with all session and builds the session lists.
     *
     * @param id to label the track
     * @param talkArrayList with all talks
     * @param sessionList containing both a morning and an afternoon session */
    private static void buildCompleteTrackLists(int id, ArrayList<Talk> talkArrayList, ArrayList<Session> sessionList) {

        if (!talkArrayList.isEmpty()){
            Session session = getTrackList(talkArrayList, id);
            sessionList.add(session);
            id++;
            buildCompleteTrackLists(id, talkArrayList, sessionList);
        }
    }

    /**
     * Generating both a morning and an afternoon session for a track and returns the later.
     *
     * @param talkArrayList with all talks
     * @param id to label the track
     *
     * @return a track containing id, morning session, and afternoon session
     */
    private static Session getTrackList(ArrayList<Talk> talkArrayList, int id) {
        LocalTime start;
        LocalTime lunchbreak = LocalTime.of(12, 0);
        LocalTime startNetworkingEvent = LocalTime.of(17, 0);
        ArrayList<Talk> morningList = new ArrayList<>();
        ArrayList<Talk> afternoonList = new ArrayList<>();
        Boolean morning = true;

        // generating the morning part
        if (morning){
            start = LocalTime.of(9, 0);
            morningList = buildSingleTrack(talkArrayList, start, lunchbreak);
        }
        morning = false;

        // generating the afternoon part
        if (!morning){
            start = LocalTime.of(13, 0);
            afternoonList = buildSingleTrack(talkArrayList, start, startNetworkingEvent);
        }
        morning = true;

        Session session = new Session(id, morningList, afternoonList);

        return session;
    }

    /**
     * Creates either morning or afternoon part of a track.
     *
     * @param talkArrayList with all talks
     * @param start starting time of a session, either 9am (morning) or 1pm (afternoon)
     * @param endOfTrackPart ending time of a session, either 12pm (morning) or 5pm (afternoon)
     *
     * @return either the morning or the afternoon session of a track
     */
    private static ArrayList<Talk> buildSingleTrack(ArrayList<Talk> talkArrayList, LocalTime start, LocalTime endOfTrackPart) {
        Iterator<Talk> iterateSession = talkArrayList.iterator();
        ArrayList<Talk> talkList = new ArrayList<>();

        while (iterateSession.hasNext()) {
            Talk talk = iterateSession.next();
            if (start.plusMinutes(talk.getLength()).compareTo(endOfTrackPart) < 0) {
                talkList.add(new Talk(start, talk.getTitle(), talk.getLength()));
                start = start.plusMinutes(talk.getLength());
                iterateSession.remove();
            }
        }
        return talkList;
    }
}