package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.math.MathHelper;

public class MathUtil {
    public static double clamp(double value, double min, double max) {
        return MathHelper.clamp(value, min, max);
    }

    public static float clamp(float value, float min, float max) {
        return MathHelper.clamp(value, min, max);
    }

    public static int clamp(int value, int min, int max) {
        return MathHelper.clamp(value, min, max);
    }

    public static long clamp(long value, long min, long max) {
        return (long) MathHelper.clamp(value, min, max);
    }

    public static double lerp(double a, double b, double t) {
        return MathHelper.lerp(t, a, b);
    }

    public static float lerp(float a, float b, float t) {
        return MathHelper.lerp(t, a, b);
    }

    public static double inverseLerp(double a, double b, double value) {
        return MathHelper.getLerpProgress(a, b, value);
    }

    public static int hsvToRgb(float hue, float saturation, float value) {
        return MathHelper.hsvToRgb(hue, saturation, value);
    }

    public static float hsvToArgb(float hue, float saturation, float value, int alpha) {
        return hsvToRgb(hue, saturation, value) | (alpha << 24);
    }
}
