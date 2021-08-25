import java.time.LocalTime;

/**
 * The Talk class creates an individual talk.
 * A talk has a title and a length (duration of the session).
 * Additionally, a talk can also have a start time.
 */

public class Talk {

    LocalTime start; // Start of the talk
    String title; // Title of the talk
    int length; // Length of the talk

    /**
     * Constructor for a talk object.
     *
     * @param title the title of the talk
     * @param length the length of the talk
     */
    public Talk(String title, int length) {
        this.title = title;
        this.length = length;
    }

    /**
     * Constructor for a talk object.
     *
     * @param start the starting time of the talk
     * @param title the title of the talk
     * @param length the length of the talk
     */
    public Talk(LocalTime start, String title, int length){
        this.start = start;
        this.title = title;
        this.length = length;
    }

    /**
     * Gets the title of a talk.
     *
     * @return the title of a talk
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the length of a talk.
     *
     * @return the length of a talk
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets the starting time of a talk.
     *
     * @return the starting time of a talk
     */
    public LocalTime getStartingTime(){
        return start;
    }

}