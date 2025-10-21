import org.junit.Test;
import static org.junit.Assert.*;

public class CodingBatTest {


    @Test
    public void testSleepIn() {
        assertTrue(CodingBat.sleepIn(false, true));
        assertFalse(CodingBat.sleepIn(true, false));
        assertTrue(CodingBat.sleepIn(true, true));
    }

    @Test
    public void testMonkeyTrouble() {
        assertTrue(CodingBat.monkeyTrouble(true, true));
        assertTrue(CodingBat.monkeyTrouble(false, false));
        assertFalse(CodingBat.monkeyTrouble(false, true));
    }

    @Test
    public void testCountEvens() {
        assertEquals("Tylko parzyste", 3, CodingBat.countEvens(new int[]{2, 4, 6}));
        assertEquals("Tylko nieparzyste", 0, CodingBat.countEvens(new int[]{1, 3, 5}));
        assertEquals("Mieszane", 2, CodingBat.countEvens(new int[]{1, 2, 3, 4}));
        assertEquals("Pusta tablica", 0, CodingBat.countEvens(new int[]{}));
    }

    @Test
    public void testHelloName() {
        assertEquals("Hello Bob!", CodingBat.helloName("Bob"));
        assertEquals("Hello Kate!", CodingBat.helloName("Kate"));
        assertEquals("Hello Maurycy!", CodingBat.helloName("Maurycy"));
    }

}