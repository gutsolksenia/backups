package factory;

import java.util.Random;

public class IntRandom {
    private static final Random random = new Random();

    public static int nextInt(int minValue, int maxValue) {
        if (maxValue < minValue) {
            throw new IllegalArgumentException();
        }
        return random.nextInt(maxValue - minValue) + minValue;
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }

    private IntRandom() {}
}
