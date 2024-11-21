package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int[] samples = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};

        timeGetLastWithSamples(Ns, times, opCounts, samples);

        printTimingTable(Ns, times, opCounts);
    }

    private static void timeGetLastWithSamples(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts, int[] samples) {
        int opCount = 10000;

        for (int i : samples) {
            SLList<Integer> list = createSLList(i);
            Ns.addLast(i);
            times.addLast(getItems(list, opCount));
            opCounts.addLast(opCount);
        }
    }

    private static SLList<Integer> createSLList(int size) {
        SLList<Integer> list = new SLList<>();
        for (int i = 0; i < size; i++) {
            list.addLast(i);
        }
        return list;
    }

    private static double getItems(SLList<Integer> list, int m) {
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < m; i++) {
            list.getLast();
        }
        return sw.elapsedTime();
    }
}
