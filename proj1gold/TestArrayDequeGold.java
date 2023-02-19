import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {

    @Test
    public void testGold() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                sad2.addLast(i);
            } else {
                sad1.addFirst(i);
                sad2.addFirst(i);
            }
        }

        assertEquals("size is not euqal", sad2.size(), sad1.size());
        for (int i = 0; i < sad1.size(); i++) {
            assertEquals("index = " + i + " sda1 = " + sad1.get(i) + " sda2 = " + sad2.get(i),
                    sad2.get(i), sad1.get(i));
        }

        for (int i = 0; i < sad1.size(); i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                Integer item1 = sad1.removeLast();
                Integer item2 = sad2.removeLast();
                assertEquals("removeLast error", item2, item1);
            } else {
                Integer item1 = sad1.removeFirst();
                Integer item2 = sad2.removeFirst();
                assertEquals("removeFirst error", item2, item1);
            }
            assertEquals("size is not euqal after remove", sad2.size(), sad1.size());
        }
    }
}
