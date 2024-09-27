package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.math.Direction;

public class DirectionUtil {

    public static Direction north() {
        return Direction.NORTH;
    }

    public static Direction south() {
        return Direction.SOUTH;
    }

    public static Direction east() {
        return Direction.EAST;
    }

    public static Direction west() {
        return Direction.WEST;
    }

    public static Direction up() {
        return Direction.UP;
    }

    public static Direction down() {
        return Direction.DOWN;
    }

    public static Direction getOpposite(Direction direction) {
        return direction.getOpposite();
    }
}
