package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.world.level.block.Rotation;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;

public class BlockRotations {
    public static Rotation NONE = Rotation.NONE;
    public static Rotation CLOCKWISE_90 = Rotation.CLOCKWISE_90;
    public static Rotation CLOCKWISE_180 = Rotation.CLOCKWISE_180;
    public static Rotation COUNTERCLOCKWISE_90 = Rotation.COUNTERCLOCKWISE_90;

    public static Rotation rotate(Rotation rotation, Rotation rotation2) {
        return rotation.getRotated(rotation2);
    }

    public static Rotation random(CompatRandom random) {
        return Rotation.getRandom(random.getMcRandom());
    }
}
