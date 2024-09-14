package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;

public class MathUtil {

    public static CompatRandom createRandom(long seed) {
        return CompatRandom.of(seed);
    }

    public static CompatRandom createRandom() {
        return CompatRandom.of();
    }

    @Deprecated
    public static Quaternion getRotationDegrees(RotationAxisType type, float deg) {
        return type.axis.getDegreesQuaternion(deg);
    }

    public static class RotationAxisType {

        public static RotationAxisType POSITIVE_X = new RotationAxisType(Vec3f.POSITIVE_X);
        public static RotationAxisType POSITIVE_Y = new RotationAxisType(Vec3f.POSITIVE_Y);
        public static RotationAxisType POSITIVE_Z = new RotationAxisType(Vec3f.POSITIVE_Z);

        protected final Vec3f axis;
        protected RotationAxisType(Vec3f axis) {
            this.axis = axis;
        }
    }
}
