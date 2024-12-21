package com.big0soft.resource.utils;

public class GenerateNumbers {

    /**
     * <h1>
     * Example
     * <h2>10 is random number</h2>
     * <h2>2 min range number</h2>
     * <h2>5 max range number</h2>
     * <h3>(10 * (5-2)) + 2</h3>
     * </h1>
     */
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    /**
     *  <h1>
     *  Example
     *  <h2>10 is random number</h2>
     *  <h2>2 min range number</h2>
     *  <h2>5 max range number</h2>
     *  <h3>(10 * (5-2)) + 2</h3>
     *  </h1>
     */
    public static double getRandomNumber(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
    /**
     *  <h1>
     *  Example
     *  <h2>10 is random number</h2>
     *  <h2>2 min range number</h2>
     *  <h2>5 max range number</h2>
     *  <h3>(10 * (5-2)) + 2</h3>
     *  </h1>
     */
    public static float getRandomNumber(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }
}
