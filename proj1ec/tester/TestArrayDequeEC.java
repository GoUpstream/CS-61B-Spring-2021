package tester;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeEC {
    @Test
    public void Test() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < 1000; i += 1) {
            double r = StdRandom.uniform(4);

            if (r < 1) {
                sad1.addFirst(i);
                sad2.addFirst(i);
                message.append("addFirst(").append(i).append(")\n");
            } else if (r < 2) {
                sad1.addLast(i);
                sad2.addLast(i);
                message.append("addLast(").append(i).append(")\n");
            } else if (r < 3) {
                if (!sad2.isEmpty()) {
                    message.append("removeFirst()\n");
                    assertEquals(message.toString(), sad2.removeFirst(), sad1.removeFirst());
                }
            } else if (r < 4) {
                if (!sad2.isEmpty()) {
                    message.append("removeLast()\n");
                    assertEquals(message.toString(), sad2.removeLast(), sad1.removeLast());
                }
            }
        }
    }
}
