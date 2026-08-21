package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.math.Direction;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;

public class BlockMirrors {
    public static BlockMirror NONE = BlockMirror.NONE;
    public static BlockMirror LEFT_RIGHT = BlockMirror.LEFT_RIGHT;
    public static BlockMirror FRONT_BACK = BlockMirror.FRONT_BACK;

    public static Direction mirror(BlockMirror mirror, Direction direction) {
        return mirror.apply(direction);
    }

    public static BlockRotation getRotation(BlockMirror mirror, Direction direction) {
        return mirror.getRotation(direction);
    }
}
