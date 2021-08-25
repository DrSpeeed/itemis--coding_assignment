import java.util.ArrayList;

/**
 * The Session class creates a session.
 * A session has an individual id, a morning session, and an afternoon session.
 */

public class Session {

    ArrayList<Talk> morningTalk; // Contains the talks in the morning
    ArrayList<Talk> afternoonTalk; // Contains the talks in the afternoon
    int trackID; // id of the track

    /**
     * Constructor for the session object.
     *
     * @param id of the session
     * @param morningTalk contains the talks in the morning
     * @param afternoonTalk contains the talks in the afternoon
     */
    public Session(int id, ArrayList<Talk> morningTalk, ArrayList<Talk> afternoonTalk){
        this.trackID = id;
        this.morningTalk = morningTalk;
        this.afternoonTalk = afternoonTalk;

    }

    /**
     * Gets the morning talks from a session.
     *
     * @return the morning talk session
     */
    public ArrayList<Talk> getMorningSession() {
        return morningTalk;
    }

    /**
     * Gets the afternoon talks from a session.
     *
     * @return the afternoon talk session
     */
    public ArrayList<Talk> getAfternoonSession() {
        return afternoonTalk;
    }

    /**
     * Gets the id of the session.
     *
     * @return the id
     */
    public int getTrackID() {
        return trackID;
    }
}