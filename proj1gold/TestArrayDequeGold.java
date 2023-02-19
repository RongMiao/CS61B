import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {

    @Test
    public void testGold() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                sad2.addLast(i);
                stringBuilder.append("addLast " + i + "\n");
            } else {
                sad1.addFirst(i);
                sad2.addFirst(i);
                stringBuilder.append("addFirst " + i + "\n");
            }
        }

        stringBuilder.append("size \n");
        assertEquals(stringBuilder.toString(), sad2.size(), sad1.size());
        for (int i = 0; i < sad1.size(); i++) {
            assertEquals("index = " + i, sad2.get(i), sad1.get(i));
        }

        for (int i = 0; i < sad1.size(); i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                Integer item1 = sad1.removeLast();
                Integer item2 = sad2.removeLast();
                stringBuilder.append("removeLast\n");
                assertNotNull(item1);
                assertEquals(stringBuilder.toString(), item2, item1);
            } else {
                Integer item1 = sad1.removeFirst();
                Integer item2 = sad2.removeFirst();
                stringBuilder.append("removeFirst\n");
                assertEquals(stringBuilder.toString(), item2, item1);
            }
        }
    }
}
