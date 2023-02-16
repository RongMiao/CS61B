import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return a.equals(b);
    }

    @Test
    public void testIsSameNumber() {
        assertTrue(isSameNumber(1,1));
        assertTrue(isSameNumber(100,100));
        assertTrue(isSameNumber(128, 128));
        assertTrue(isSameNumber(200, 200));
    }
}
