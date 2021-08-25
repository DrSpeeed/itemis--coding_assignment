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
 * the calculated track lists to the console.
 *
 */

public class Services {

    public Services(){
    }

    /**
     * Import the actual session list provided in a file and add it to the rawSessionList.
     *
     * @param conferenceTrackManager the class calling this method
     * @param nameFile the file containing the actual track list
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
     * Creates session objects out of the given rawSessionList and store these in an ArrayList.
     * Critical is here that the session title must not contain any number.
     *
     * @param conferenceTrackManager the class calling this method
     * @param rawSessionList the list containing all given sessions
     */
    static void createSessionList(ConferenceTrackManager conferenceTrackManager, ArrayList<String> rawSessionList) {

        // looping throuhg the list containing the raw data
        for (int i=0; i < rawSessionList.size(); i++){
            String rawSessionEdit = rawSessionList.get(i);
            Matcher matcher = Pattern.compile("\\d+").matcher(rawSessionEdit);
            matcher.find();

            // Find and extract the length of the session
            try {
                int duration = Integer.parseInt(matcher.group());
                Session session = new Session(rawSessionEdit, duration);
                conferenceTrackManager.sessionList.add(session);

            // Fallback if no length in minutes is provided;
            // per given definition this is a lightning session with a length of 5 minutes
            } catch (IllegalStateException e){
                Session session = new Session(rawSessionEdit, 5);
                conferenceTrackManager.sessionList.add(session);
            }
        }
    }

    /**
     * Main method to create the track lists.
     * Initialise some params for further usage.
     *
     * @param conferenceTrackManager the class calling this method
     * @param sessionArrayList contains all session objects
     */
    static void createTrackLists(ConferenceTrackManager conferenceTrackManager, ArrayList<Session> sessionArrayList){
        int id = 1;

        ArrayList<Track> trackList = new ArrayList<>();

        // building the track lists
        buildCompleteTrackLists(id, sessionArrayList, trackList);

        // printing the generated track lists to the console
        printTrackLists(trackList);

    }

    /**
     * Prints the track lists to the console.
     *
     * @param trackList the generated track lists
     */
    private static void printTrackLists(ArrayList<Track> trackList) {
        // iterating through the track lists
        for (Track track : trackList) {

            System.out.println("Track " + track.getTrackID() + ":");

            // printing the morning session
            for (int m = 0; m < track.getMorningSession().size(); m++) {
                // converting the time format to US format
                LocalTime toConvert = track.getMorningSession().get(m).getStartingTime();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("KK:mma").withLocale(Locale.US);
                String convertedToUS = toConvert.format(timeFormatter);

                System.out.println(convertedToUS + " " + track.getMorningSession().get(m).getTitle());
            }

            System.out.println("12:00PM Lunch");

            // printing the afternoon session
            for (int j = 0; j < track.getAfternoonSession().size(); j++) {
                // converting the time format to US format
                LocalTime toConvert = track.getAfternoonSession().get(j).getStartingTime();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("KK:mma").withLocale(Locale.US);
                String convertedToUS = toConvert.format(timeFormatter);

                System.out.println(convertedToUS + " " + track.getAfternoonSession().get(j).getTitle());
            }

            System.out.println("05:00PM Networking Event\n");
        }
    }

    /**
     * Iterates recursively through the sesssionArrayList with all session and builds the track lists.
     *
     * @param id
     * @param sessionArrayList with all sessions
     * @param trackList containing both a morning and an afternoon session */
    private static void buildCompleteTrackLists(int id, ArrayList<Session> sessionArrayList, ArrayList<Track> trackList) {

        if (!sessionArrayList.isEmpty()){
            Track track = getTrackList(sessionArrayList, id);
            trackList.add(track);
            id++;
            buildCompleteTrackLists(id, sessionArrayList, trackList);
        }
    }

    /**
     * Generating both a morning and an afternoon session for a track and returns the later.
     *
     * @param sessionArrayList with all sessions
     * @param id to label the track
     *
     * @return a track containing id, morning session, and afternoon session
     */
    private static Track getTrackList(ArrayList<Session> sessionArrayList, int id) {
        LocalTime start;
        LocalTime lunchbreak = LocalTime.of(12, 0);
        LocalTime startNetworkingEvent = LocalTime.of(17, 0);
        ArrayList<Session> morningList = new ArrayList<>();
        ArrayList<Session> afternoonList = new ArrayList<>();
        Boolean morning = true;

        // generating the morning part
        if (morning){
            start = LocalTime.of(9, 0);
            morningList = buildSingleTrack(sessionArrayList, start, lunchbreak);
        }
        morning = false;

        // generating the afternoon part
        if (!morning){
            start = LocalTime.of(13, 0);
            afternoonList = buildSingleTrack(sessionArrayList, start, startNetworkingEvent);
        }
        morning = true;

        Track track = new Track(id, morningList, afternoonList);

        return track;
    }

    /**
     * Creates either morning or afternoon part of a track.
     *
     * @param sessionArrayList with all sessions
     * @param start starting time, either 9am (morning) or 1pm (afternoon)
     * @param endOfTrackPart ending time, either 12pm (morning) or 5pm (afternoon)
     *
     * @return either the morning or the afternoon part of a track
     */
    private static ArrayList<Session> buildSingleTrack(ArrayList<Session> sessionArrayList, LocalTime start, LocalTime endOfTrackPart) {
        Iterator<Session> iterateSession = sessionArrayList.iterator();
        ArrayList<Session> sessionList = new ArrayList<>();

        while (iterateSession.hasNext()) {
            Session session = iterateSession.next();
            if (start.plusMinutes(session.getLength()).compareTo(endOfTrackPart) < 0) {
                sessionList.add(new Session(start, session.getTitle(), session.getLength()));
                start = start.plusMinutes(session.getLength());
                iterateSession.remove();
            }
        }
        return sessionList;
    }
}