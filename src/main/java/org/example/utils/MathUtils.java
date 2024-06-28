package org.example.utils;

public abstract class MathUtils {

    private static final double EPSILON = 1e-10;

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static boolean greaterThan(double a, double b) {
        return a - b > EPSILON;
    }
}
