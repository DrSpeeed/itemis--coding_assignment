import java.time.LocalTime;

/**
 * The session class creates an individual session.
 * A session has a title and a length (duration of the session).
 * Additionally, a session can also have  a start time.
 */

public class Session {

    LocalTime start; // Start of the session
    String title; // Title of the session
    int length; // Length of the session

    /**
     * Constructor for a session object.
     *
     * @param title the title of the session
     * @param length the length of the session
     */
    public Session(String title, int length) {
        this.title = title;
        this.length = length;
    }

    /**
     * Constructor for a session object.
     *
     * @param start the starting time of the session
     * @param title the title of the session
     * @param length the length of the session
     */
    public Session(LocalTime start, String title, int length){
        this.start = start;
        this.title = title;
        this.length = length;
    }

    /**
     * Gets the title of a session.
     *
     * @return the title of a session
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the length of a session.
     *
     * @return the length of a session
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets the starting time of a session.
     *
     * @return the starting time of a session
     */
    public LocalTime getStartingTime(){
        return start;
    }

}