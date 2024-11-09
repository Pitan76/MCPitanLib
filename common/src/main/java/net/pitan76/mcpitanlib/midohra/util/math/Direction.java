package net.pitan76.mcpitanlib.midohra.util.math;

public class Direction {

    public static final Direction UP = of(net.minecraft.util.math.Direction.UP);
    public static final Direction DOWN = of(net.minecraft.util.math.Direction.DOWN);
    public static final Direction NORTH = of(net.minecraft.util.math.Direction.NORTH);
    public static final Direction SOUTH = of(net.minecraft.util.math.Direction.SOUTH);
    public static final Direction EAST = of(net.minecraft.util.math.Direction.EAST);
    public static final Direction WEST = of(net.minecraft.util.math.Direction.WEST);

    private final net.minecraft.util.math.Direction direction;

    protected Direction(net.minecraft.util.math.Direction direction) {
        this.direction = direction;
    }

    public static Direction of(net.minecraft.util.math.Direction direction) {
        switch (direction) {
            case UP:
                return UP;
            case DOWN:
                return DOWN;
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case EAST:
                return EAST;
            case WEST:
                return WEST;
            default:
                return new Direction(direction);
        }
    }

    public Direction getOpposite() {
        switch (direction) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                return null;
        }
    }

    public net.minecraft.util.math.Direction toMinecraft() {
        return direction;
    }

    public boolean isHorizontal() {
        return direction.getAxis().isHorizontal();
    }

    public boolean isVertical() {
        return direction.getAxis().isVertical();
    }

    public boolean isPositive() {
        return direction.getDirection() == net.minecraft.util.math.Direction.AxisDirection.POSITIVE;
    }

    public boolean isNegative() {
        return direction.getDirection() == net.minecraft.util.math.Direction.AxisDirection.NEGATIVE;
    }

    public int getOffsetX() {
        return direction.getOffsetX();
    }

    public int getOffsetY() {
        return direction.getOffsetY();
    }

    public int getOffsetZ() {
        return direction.getOffsetZ();
    }

    public Direction rotateYClockwise() {
        return of(direction.rotateYClockwise());
    }

    public Direction rotateYCounterclockwise() {
        return of(direction.rotateYCounterclockwise());
    }

}
