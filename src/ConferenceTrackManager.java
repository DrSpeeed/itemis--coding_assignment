import java.util.ArrayList;

/**
 * The ConferenceTrackManager class creates the object to initialise the process to generate the track lists.
 *
 */

public class ConferenceTrackManager {

    ArrayList<String> rawSessionsList; // Contains the given data of sessions
    ArrayList<Session> sessionList; // Contains the data converted into session objects

    /**
     * Constructor to initialise and run the process.
     *
     */
    public ConferenceTrackManager() {

        rawSessionsList = new ArrayList<>(); //raw list with raw information on sessions
        sessionList = new ArrayList<>(); //list containing the actual session objects

        // importing the given raw data
        Services.importTracks(this, "tracks.txt");

        // converting the raw data into session objects
        Services.createSessionList(this, rawSessionsList);

        // manipulating the sessions objects and printing the track lists to the console
        Services.createTrackLists(this, sessionList);

    }
}