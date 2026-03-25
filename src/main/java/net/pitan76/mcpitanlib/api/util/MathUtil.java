package net.pitan76.mcpitanlib.api.util;

import com.mojang.math.Axis;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import org.joml.Quaternionf;

public class MathUtil {

    public static CompatRandom createRandom(long seed) {
        return CompatRandom.of(seed);
    }

    public static CompatRandom createRandom() {
        return CompatRandom.of();
    }

    @Deprecated
    public static Quaternionf getRotationDegrees(RotationAxisType type, float deg) {
        return type.axis.rotationDegrees(deg);
    }

    public static class RotationAxisType {

        public static RotationAxisType POSITIVE_X = new RotationAxisType(Axis.XP);
        public static RotationAxisType POSITIVE_Y = new RotationAxisType(Axis.YP);
        public static RotationAxisType POSITIVE_Z = new RotationAxisType(Axis.ZP);

        protected final Axis axis;
        protected RotationAxisType(Axis axis) {
            this.axis = axis;
        }
    }
}
