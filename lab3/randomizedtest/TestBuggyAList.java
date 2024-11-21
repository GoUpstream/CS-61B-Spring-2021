package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> goodList = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();

        goodList.addLast(4);
        goodList.addLast(5);
        goodList.addLast(6);
        buggyList.addLast(4);
        buggyList.addLast(5);
        buggyList.addLast(6);

        assertEquals(goodList.removeLast(), buggyList.removeLast());
        assertEquals(goodList.removeLast(), buggyList.removeLast());
        assertEquals(goodList.removeLast(), buggyList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> goodList = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                goodList.addLast(randVal);
                buggyAList.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                assertEquals(goodList.size(), buggyAList.size());
            } else if (operationNumber == 2) {
                if (goodList.size() > 0) {
                    assertEquals(goodList.getLast(), buggyAList.getLast());
                }
            } else if (operationNumber == 3) {
                if (goodList.size() > 0) {
                    assertEquals(goodList.removeLast(), buggyAList.removeLast());
                }
            }
        }
    }
}
