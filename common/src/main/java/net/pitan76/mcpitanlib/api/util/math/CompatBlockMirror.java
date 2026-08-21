package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.world.level.block.Mirror;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;

public class CompatBlockMirror implements CompatStringIdentifiable {
    private final Mirror mirror;

    public static final CompatBlockMirror NONE = of(BlockMirrors.NONE);
    public static final CompatBlockMirror LEFT_RIGHT = of(BlockMirrors.LEFT_RIGHT);
    public static final CompatBlockMirror FRONT_BACK = of(BlockMirrors.FRONT_BACK);

    public CompatBlockMirror(Mirror mirror) {
        this.mirror = mirror;
    }

    public static CompatBlockMirror of(Mirror mirror) {
        return new CompatBlockMirror(mirror);
    }

    public Mirror getMirror() {
        return mirror;
    }

    public Mirror getRaw() {
        return mirror;
    }

    public Mirror toMinecraft() {
        return mirror;
    }

    public static CompatBlockMirror[] values() {
        return new CompatBlockMirror[]{NONE, LEFT_RIGHT, FRONT_BACK};
    }

    public net.minecraft.core.Direction mirror(net.minecraft.core.Direction direction) {
        return BlockMirrors.mirror(getRaw(), direction);
    }

    public Direction mirror(Direction direction) {
        return Direction.of(mirror(direction.getRaw()));
    }

    public CompatBlockRotation getRotation(net.minecraft.core.Direction direction) {
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
