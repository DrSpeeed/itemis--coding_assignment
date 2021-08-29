import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The Session_Test class checks the methods of the Session class.
 *
 */

class Session_Test {

    Talk Talk1 = new Talk(LocalTime.of(9, 0), "Talk_A", 30);
    Talk Talk2 = new Talk(LocalTime.of(9, 30), "Talk_B", 30);
    Talk Talk3 = new Talk(LocalTime.of(14, 0), "Talk_C", 30);
    Talk Talk4 = new Talk(LocalTime.of(14, 30), "Talk_D", 30);

    ArrayList<Talk> morningTalks = new ArrayList<>();
    ArrayList<Talk> morningTalksEmpty = new ArrayList<>();
    ArrayList<Talk> morningTalksCheck = new ArrayList<>();
    ArrayList<Talk> morningTalksCheckEmpty = new ArrayList<>();

    ArrayList<Talk> afternoonTalks = new ArrayList<>();
    ArrayList<Talk> afternoonTalksEmpty = new ArrayList<>();
    ArrayList<Talk> afternoonTalksCheck = new ArrayList<>();
    ArrayList<Talk> afternoonTalksCheckEmpty = new ArrayList<>();

    @Test
    void getMorningSession() {

        morningTalks.add(Talk1);
        morningTalks.add(Talk2);
        morningTalksCheck.add(Talk1);
        morningTalksCheck.add(Talk2);
        afternoonTalks.add(Talk3);
        afternoonTalks.add(Talk4);
        afternoonTalksCheck.add(Talk3);
        afternoonTalksCheck.add(Talk4);

        Session testSession = new Session(1, morningTalks, afternoonTalks);
        Session testSessionEmpty = new Session(1, morningTalksEmpty, afternoonTalksEmpty);

        assertEquals(morningTalksCheck, testSession.getMorningSession());
        assertNotEquals(afternoonTalksCheck, testSession.getMorningSession());
        assertEquals(morningTalksCheckEmpty, testSessionEmpty.getMorningSession());
        assertNotEquals(afternoonTalksCheck, testSessionEmpty.getMorningSession());
    }

    @Test
    void getAfternoonSession() {

        morningTalks.add(Talk1);
        morningTalks.add(Talk2);
        morningTalksCheck.add(Talk1);
        morningTalksCheck.add(Talk2);
        afternoonTalks.add(Talk3);
        afternoonTalks.add(Talk4);
        afternoonTalksCheck.add(Talk3);
        afternoonTalksCheck.add(Talk4);

        Session testSession = new Session(1, morningTalks, afternoonTalks);
        Session testSessionEmpty = new Session(1, morningTalksEmpty, afternoonTalksEmpty);

        assertEquals(afternoonTalksCheck, testSession.getAfternoonSession());
        assertNotEquals(morningTalksCheck, testSession.getAfternoonSession());
        assertEquals(afternoonTalksCheckEmpty, testSessionEmpty.getAfternoonSession());
        assertNotEquals(morningTalksCheck, testSessionEmpty.getAfternoonSession());
    }

    @Test
    void getTrackID() {

        morningTalks.add(Talk1);
        morningTalks.add(Talk2);
        afternoonTalks.add(Talk3);
        afternoonTalks.add(Talk4);

        Session testSession = new Session(1, morningTalks, afternoonTalks);
        Session testSessionCheck = new Session(2, morningTalks, afternoonTalks);

        assertEquals(1, testSession.getTrackID());
        assertNotEquals(2, testSession.getTrackID());
        assertNotEquals(1, testSessionCheck.getTrackID());


    }
}