package ru.ravvcheck.web3lab.model.services;

public final class AreaCheck {
    private static final double X_MAX = 5;
    private static final double X_MIN = -5;
    private static final double Y_MAX = 3;
    private static final double Y_MIN = -3;
    private static final double R_MAX = 5;
    private static final double R_MIN = 2;

    public static boolean isHit(double x, double y, double r) {
        if (x > X_MAX || x < X_MIN) {
            return false;
        }
        if (y > Y_MAX || y < Y_MIN) {
            return false;
        }
        if (r > R_MAX || r < R_MIN) {
            return false;
        }
        return isCircle(x, y, r) || isRectangle(x, y, r) || isTriangle(x, y, r);
    }

    private static boolean isRectangle(double x, double y, double r) {
        return y >= -r && x >= -r && x <= 0 && y <= 0;
    }

    private static boolean isCircle(double x, double y, double r) {
        return x * x + y * y <= (r * r) && x >= 0 && y >= 0;
    }

    private static boolean isTriangle(double x, double y, double r) {
        return x <= 0 && y >= 0 && y <= x / 2 + r / 2;
    }
}
