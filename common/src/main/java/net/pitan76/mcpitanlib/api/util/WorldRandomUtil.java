package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.Level;

public class WorldRandomUtil {
    public static int nextInt(Level world) {
        return world.getRandom().nextInt();
    }

    public static int nextInt(Level world, int bound) {
        return world.getRandom().nextInt(bound);
    }

    public static long nextLong(Level world) {
        return world.getRandom().nextLong();
    }

    public static double nextDouble(Level world) {
        return world.getRandom().nextDouble();
    }

    public static double nextGaussian(Level world) {
        return world.getRandom().nextGaussian();
    }

    public static float nextFloat(Level world) {
        return world.getRandom().nextFloat();
    }

    public static int nextBetween(Level world, int min, int max) {
        return world.getRandom().nextIntBetweenInclusive(min, max);
    }

    public static int nextBetweenExclusive(Level world, int min, int max) {
        return world.getRandom().nextInt(min, max);
    }
}
