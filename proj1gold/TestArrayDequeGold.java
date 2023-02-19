import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {

    @Test
    public void testGold() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
        String sequence = new String();
        for (int i = 0; i < 10; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                sad2.addLast(i);
                if (sequence.length() == 0) {
                    sequence += i;
                } else {
                    sequence = i + " " + sequence;
                }
            } else {
                sad1.addFirst(i);
                sad2.addFirst(i);
                if (sequence.length() == 0) {
                    sequence += i;
                } else {
                    sequence = sequence + " " + i;
                }
            }
        }

        assertEquals(sequence, sad2.size(), sad1.size());
        for (int i = 0; i < sad1.size(); i++) {
            assertEquals(sequence, sad2.get(i), sad1.get(i));
        }

        for (int i = 0; i < sad1.size(); i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                Integer item1 = sad1.removeLast();
                Integer item2 = sad2.removeLast();
                assertNotNull(sequence, item1);
                assertEquals(sequence, item2, item1);
            } else {
                Integer item1 = sad1.removeFirst();
                Integer item2 = sad2.removeFirst();
                assertNotNull(sequence, item1);
                assertEquals(sequence, item2, item1);
            }
        }
    }
}
