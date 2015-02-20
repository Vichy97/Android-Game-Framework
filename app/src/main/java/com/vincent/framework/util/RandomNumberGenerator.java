package com.vincent.framework.util;

import java.util.Random;

/**
 * Created by Vincent on 1/19/2015.
 *
 * This class makes it easier to create random numbers without
 * creating a new Random object each time. This saves resources
 * and reduces the amount of code needed to create a random number
 */
public class RandomNumberGenerator {

    private static Random rand = new Random();

    public static int getRandIntBetween(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public static int getRandInt(int upperBound) {
        return rand.nextInt(upperBound);
    }
}
