import java.util.ArrayList;

/**
 * The ConferenceTrackManager class creates the object to initialise the process to generate the track lists.
 *
 */

public class ConferenceTrackManager {

    ArrayList<String> rawSessionsList; // Contains the given data of all talks
    ArrayList<Talk> talkList; // Contains the data converted into talk objects

    /**
     * Constructor to initialise and run the process.
     *
     */
    public ConferenceTrackManager() {

        rawSessionsList = new ArrayList<>(); //raw list with raw information on talks
        talkList = new ArrayList<>(); //list containing the actual talk objects

        // importing the given raw data
        Services.importTracks(this, "tracks.txt");

        // converting the raw data into talk objects
        Services.createSessionList(this, rawSessionsList);

        // manipulating the talk objects and printing the session lists to the console
        Services.createTrackLists(this, talkList);

    }
}