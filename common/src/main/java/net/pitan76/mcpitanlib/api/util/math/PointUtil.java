package net.pitan76.mcpitanlib.api.util.math;

public class PointUtil {
    public static boolean isInRegion(int x, int y, int width, int height, int pointX, int pointY) {
        return pointX >= x && pointX < x + width && pointY >= y && pointY < y + height;
    }

    public static boolean isInRegion(int x, int y, int width, int height, double pointX, double pointY) {
        return pointX >= x && pointX < x + width && pointY >= y && pointY < y + height;
    }

    public static boolean isInRegion2(int x1, int y1, int x2, int y2, int pointX, int pointY) {
        return pointX >= x1 && pointX < x2 && pointY >= y1 && pointY < y2;
    }
}
