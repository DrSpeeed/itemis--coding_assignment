import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The Talk_Test class checks the methods of the Talk class.
 *
 */

class Talk_Test {

    Talk testTalk1 = new Talk(LocalTime.of(12, 0), "TestTitleA 30min", 30);
    Talk testTalk2 = new Talk(LocalTime.of(12, 0), "", 30);
    Talk testTalk3 = new Talk(LocalTime.of(12, 0), "TestTitle 0min", 0);
    Talk testTalk4 = new Talk(LocalTime.of(0, 0), "TestTitle 0min", 0);
    Talk testTalk5 = new Talk(LocalTime.of(23, 59), "TestTitle 0min", 0);

    @Test
    void getTitle() {

        assertEquals("TestTitleA 30min", testTalk1.getTitle());
        assertEquals("", testTalk2.getTitle());
        assertNotEquals("WrongTitle", testTalk1.getTitle());

    }

    @Test
    void getLength() {

        assertEquals(30, testTalk1.getLength());
        assertEquals(0, testTalk3.getLength());
        assertNotEquals(31, testTalk2.getLength());
    }

    @Test
    void getStartingTime() {

        assertEquals(LocalTime.of(12, 0), testTalk1.getStartingTime());
        assertNotEquals(LocalTime.of(12,1), testTalk1.getStartingTime());
        assertEquals(LocalTime.of(0, 0), testTalk4.getStartingTime());
        assertEquals(LocalTime.of(23, 59), testTalk5.getStartingTime());
    }
}