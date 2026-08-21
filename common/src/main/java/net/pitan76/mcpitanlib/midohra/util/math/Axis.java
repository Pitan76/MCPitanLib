package net.pitan76.mcpitanlib.midohra.util.math;

import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class Axis implements Predicate<Direction> {

    public static final Axis X = new Axis(net.minecraft.core.Direction.Axis.X);
    public static final Axis Y = new Axis(net.minecraft.core.Direction.Axis.Y);
    public static final Axis Z = new Axis(net.minecraft.core.Direction.Axis.Z);

    private final net.minecraft.core.Direction.Axis axis;

    protected Axis(net.minecraft.core.Direction.Axis axis) {
        this.axis = axis;
    }

    public static Axis of(net.minecraft.core.Direction.Axis axis) {
        switch (axis) {
            case X:
                return X;
            case Y:
                return Y;
            case Z:
                return Z;
            default:
                return new Axis(axis);
        }
    }

    public net.minecraft.core.Direction.Axis getRaw() {
        return axis;
    }

    public net.minecraft.core.Direction.Axis toMinecraft() {
        return getRaw();
    }

    public Direction getPositive() {
        return Direction.of(getRaw().getPositive());
    }

    public Direction getNegative() {
        return Direction.of(getRaw().getNegative());
    }

    public Direction[] getDirections() {
        return new Direction[]{getPositive(), getNegative()};
    }

    public boolean isHorizontal() {
        return getRaw().isHorizontal();
    }

    public boolean isVertical() {
        return getRaw().isVertical();
    }

    public String getName() {
        return getRaw().getName();
    }

    public static Axis[] values() {
        return new Axis[]{X, Y, Z};
    }

    public int choose(int x, int y, int z) {
        return getRaw().choose(x, y, z);
    }

    public double choose(double x, double y, double z) {
        return getRaw().choose(x, y, z);
    }

    public boolean choose(boolean x, boolean y, boolean z) {
        return getRaw().choose(x, y, z);
    }

    @Override
    public String toString() {
        return getRaw().toString();
    }

    @Override
    public int hashCode() {
        return getRaw().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Axis that = (Axis) obj;
        return getRaw().equals(that.getRaw());
    }

    public static Axis getRandom(CompatRandom random) {
        return random.choose(values());
    }

    @Override
    public boolean test(@Nullable Direction input) {
        return getRaw().test(input != null ? input.getRaw() : null);
    }
}
