package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

public class GuitarHero {
    public static final double CONCERT_A = 440.0;
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];
        for (int i = 0; i < KEYBOARD.length(); i++) {
            strings[i] = new GuitarString(CONCERT_A * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = Arrays.stream(strings).map(GuitarString::sample)
                    .mapToDouble(Double::doubleValue).sum();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            Arrays.stream(strings).forEach(GuitarString::tic);
        }
    }
}
