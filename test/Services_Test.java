import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The Service_Test class checks the methods of the Service class.
 *
 */

class Services_Test {

    ArrayList<String> rawSessionListTest = new ArrayList<>();
    ArrayList<String> rawSessionListTestCheck = new ArrayList<>();

    @Test
    void importTracks() {

        rawSessionListTest.add("Writing Fast Tests Against Enterprise Rails 60min");
        rawSessionListTest.add("Overdoing it in Python 45min");
        rawSessionListTest.add("Lua for the Masses 30min");
        rawSessionListTest.add("Ruby Errors from Mismatched Gem Versions 45min");
        rawSessionListTest.add("Common Ruby Errors 45min");
        rawSessionListTest.add("Rails for Python Developers lightning");
        rawSessionListTest.add("Communicating Over Distance 60min");
        rawSessionListTest.add("Accounting-Driven Development 45min");
        rawSessionListTest.add("Woah 30min");
        rawSessionListTest.add("Sit Down and Write 30min");
        rawSessionListTest.add("Pair Programming vs Noise 45min");
        rawSessionListTest.add("Rails Magic 60min");
        rawSessionListTest.add("Ruby on Rails: Why We Should Move On 60min");
        rawSessionListTest.add("Clojure Ate Scala (on my project) 45min");
        rawSessionListTest.add("Programming in the Boondocks of Seattle 30min");
        rawSessionListTest.add("Ruby vs. Clojure for Back-End Development 30min");
        rawSessionListTest.add("Ruby on Rails Legacy App Maintenance 60min");
        rawSessionListTest.add("A World Without HackerNews 30min");
        rawSessionListTest.add("User Interface CSS in Rails Apps 30min");

        rawSessionListTestCheck.add("TestTrack_A 30min");

        ConferenceTrackManager testCTM = new ConferenceTrackManager();

        assertEquals(rawSessionListTest, testCTM.rawSessionsList);
        assertNotEquals(rawSessionListTestCheck, testCTM.rawSessionsList);
    }
}