package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.math.RotationAxis;
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

        public static RotationAxisType POSITIVE_X = new RotationAxisType(RotationAxis.POSITIVE_X);
        public static RotationAxisType POSITIVE_Y = new RotationAxisType(RotationAxis.POSITIVE_Y);
        public static RotationAxisType POSITIVE_Z = new RotationAxisType(RotationAxis.POSITIVE_Z);

        protected final RotationAxis axis;
        protected RotationAxisType(RotationAxis axis) {
            this.axis = axis;
        }
    }
}
