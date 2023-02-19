import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    /*Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
    @Test
    public void equalCharsOffByOne() {
        assertEquals(offByOne.equalChars('a', 'b'), true);
        assertEquals(offByOne.equalChars('r', 'q'), true);
        assertEquals(offByOne.equalChars('&', '%'), true);
        assertEquals(offByOne.equalChars('a', 'e'), false);
        assertEquals(offByOne.equalChars('z', 'e'), false);
        assertEquals(offByOne.equalChars('a', 'a'), false);
    }


}
