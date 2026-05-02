package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.Mth;

public class MathUtil {
    public static double clamp(double value, double min, double max) {
        return Mth.clamp(value, min, max);
    }

    public static float clamp(float value, float min, float max) {
        return Mth.clamp(value, min, max);
    }

    public static int clamp(int value, int min, int max) {
        return Mth.clamp(value, min, max);
    }

    public static long clamp(long value, long min, long max) {
        return Mth.clamp(value, min, max);
    }

    public static double lerp(double a, double b, double t) {
        return Mth.lerp(t, a, b);
    }

    public static float lerp(float a, float b, float t) {
        return Mth.lerp(t, a, b);
    }

    public static double inverseLerp(double a, double b, double value) {
        return Mth.inverseLerp(a, b, value);
    }

    public static int hsvToRgb(float hue, float saturation, float value) {
        return Mth.hsvToRgb(hue, saturation, value);
    }

    public static float hsvToArgb(float hue, float saturation, float value, int alpha) {
        return Mth.hsvToArgb(hue, saturation, value, alpha);
    }
}
