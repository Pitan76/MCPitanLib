package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.BlockRotation;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;

public class BlockRotations {
    public static BlockRotation NONE = BlockRotation.NONE;
    public static BlockRotation CLOCKWISE_90 = BlockRotation.CLOCKWISE_90;
    public static BlockRotation CLOCKWISE_180 = BlockRotation.CLOCKWISE_180;
    public static BlockRotation COUNTERCLOCKWISE_90 = BlockRotation.COUNTERCLOCKWISE_90;

    public static BlockRotation rotate(BlockRotation rotation, BlockRotation rotation2) {
        return rotation.rotate(rotation2);
    }

    public static BlockRotation random(CompatRandom random) {
        return BlockRotation.random(random.getJavaRandom());
    }
}
