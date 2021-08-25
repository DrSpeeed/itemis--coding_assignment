import java.util.ArrayList;

/**
 * The track class creates a track.
 * A track has an individual id, a morning session, and an afternoon session.
 */

public class Track {

    ArrayList<Session> morningSession; // Contains the session in the morning
    ArrayList<Session> afternoonSession; // Contains the session in the afternoon
    int trackID; // id of the track

    /**
     * Constructor for the track object.
     *
     * @param id of the track
     * @param morningSession contains the sessions in the morning
     * @param afternoonSession contains the sessions in the afternoon
     */
    public Track(int id, ArrayList<Session> morningSession, ArrayList<Session> afternoonSession){
        this.trackID = id;
        this.morningSession = morningSession;
        this.afternoonSession = afternoonSession;

    }

    /**
     * Gets the morning session from a track.
     *
     * @return the morning session
     */
    public ArrayList<Session> getMorningSession() {
        return morningSession;
    }

    /**
     * Gets the afternoon session from a track.
     *
     * @return the afternoon session
     */
    public ArrayList<Session> getAfternoonSession() {
        return afternoonSession;
    }

    /**
     * Gets the id of the track.
     *
     * @return the id
     */
    public int getTrackID() {
        return trackID;
    }
}