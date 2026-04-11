package net.pitan76.mcpitanlib.midohra.util.math;

public class Direction {

    public static final Direction UP = new Direction(net.minecraft.core.Direction.UP);
    public static final Direction DOWN = new Direction(net.minecraft.core.Direction.DOWN);
    public static final Direction NORTH = new Direction(net.minecraft.core.Direction.NORTH);
    public static final Direction SOUTH = new Direction(net.minecraft.core.Direction.SOUTH);
    public static final Direction EAST = new Direction(net.minecraft.core.Direction.EAST);
    public static final Direction WEST = new Direction(net.minecraft.core.Direction.WEST);

    private final net.minecraft.core.Direction direction;

    protected Direction(net.minecraft.core.Direction direction) {
        this.direction = direction;
    }

    public static Direction of(net.minecraft.core.Direction direction) {
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
        switch (getRaw()) {
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

    public net.minecraft.core.Direction getRaw() {
        return direction;
    }

    public net.minecraft.core.Direction toMinecraft() {
        return getRaw();
    }

    public boolean isHorizontal() {
        return getRaw().getAxis().isHorizontal();
    }

    public boolean isVertical() {
        return getRaw().getAxis().isVertical();
    }

    public boolean isPositive() {
        return getRaw().getAxisDirection() == net.minecraft.core.Direction.AxisDirection.POSITIVE;
    }

    public boolean isNegative() {
        return getRaw().getAxisDirection() == net.minecraft.core.Direction.AxisDirection.NEGATIVE;
    }

    public int getOffsetX() {
        return getRaw().getStepX();
    }

    public int getOffsetY() {
        return getRaw().getStepY();
    }

    public int getOffsetZ() {
        return getRaw().getStepZ();
    }

    public Direction rotateYClockwise() {
        return of(getRaw().getClockWise());
    }

    public Direction rotateYCounterclockwise() {
        return of(getRaw().getCounterClockWise());
    }

    @Override
    public String toString() {
        return getRaw().toString();
    }

    public String getName() {
        return getRaw().name();
    }

    public static Direction[] values() {
        return new Direction[]{UP, DOWN, NORTH, SOUTH, EAST, WEST};
    }

    public Direction[] horizontal() {
        return new Direction[]{NORTH, SOUTH, EAST, WEST};
    }

    public Direction[] vertical() {
        return new Direction[]{UP, DOWN};
    }

    @Override
    public int hashCode() {
        return getRaw().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Direction dir = (Direction) obj;
        return getRaw() == dir.getRaw();
    }
}
