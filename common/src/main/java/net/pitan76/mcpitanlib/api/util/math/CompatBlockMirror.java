package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.BlockMirror;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;

public class CompatBlockMirror implements CompatStringIdentifiable {
    private final BlockMirror mirror;

    public static final CompatBlockMirror NONE = of(BlockMirrors.NONE);
    public static final CompatBlockMirror LEFT_RIGHT = of(BlockMirrors.LEFT_RIGHT);
    public static final CompatBlockMirror FRONT_BACK = of(BlockMirrors.FRONT_BACK);

    public CompatBlockMirror(BlockMirror mirror) {
        this.mirror = mirror;
    }

    public static CompatBlockMirror of(BlockMirror mirror) {
        return new CompatBlockMirror(mirror);
    }

    public BlockMirror getMirror() {
        return mirror;
    }

    public BlockMirror getRaw() {
        return mirror;
    }

    public BlockMirror toMinecraft() {
        return mirror;
    }

    public static CompatBlockMirror[] values() {
        return new CompatBlockMirror[]{NONE, LEFT_RIGHT, FRONT_BACK};
    }

    public net.minecraft.util.math.Direction mirror(net.minecraft.util.math.Direction direction) {
        return BlockMirrors.mirror(getRaw(), direction);
    }

    public Direction mirror(Direction direction) {
        return Direction.of(mirror(direction.getRaw()));
    }

    public CompatBlockRotation getRotation(net.minecraft.util.math.Direction direction) {
        return CompatBlockRotation.of(BlockMirrors.getRotation(getRaw(), direction));
    }

    public CompatBlockRotation getRotation(Direction direction) {
        return getRotation(direction.getRaw());
    }

    public String getName() {
        return getRaw().name();
    }

    @Override
    public String asString_compat() {
        return getName();
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
        CompatBlockMirror other = (CompatBlockMirror) obj;
        return getRaw() == other.getRaw();
    }
}
