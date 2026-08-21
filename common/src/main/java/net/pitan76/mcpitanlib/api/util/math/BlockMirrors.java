package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;

public class BlockMirrors {
    public static Mirror NONE = Mirror.NONE;
    public static Mirror LEFT_RIGHT = Mirror.LEFT_RIGHT;
    public static Mirror FRONT_BACK = Mirror.FRONT_BACK;

    public static Direction mirror(Mirror mirror, Direction direction) {
        return mirror.mirror(direction);
    }

    public static Rotation getRotation(Mirror mirror, Direction direction) {
        return mirror.getRotation(direction);
    }
}
