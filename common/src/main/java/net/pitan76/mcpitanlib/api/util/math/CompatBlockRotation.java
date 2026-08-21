package net.pitan76.mcpitanlib.api.util.math;

import net.minecraft.util.BlockRotation;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class CompatBlockRotation implements CompatStringIdentifiable {
    private final BlockRotation rotation;

    public static final CompatBlockRotation NONE = of(BlockRotations.NONE);
    public static final CompatBlockRotation CLOCKWISE_90 = of(BlockRotations.CLOCKWISE_90);
    public static final CompatBlockRotation CLOCKWISE_180 = of(BlockRotations.CLOCKWISE_180);
    public static final CompatBlockRotation COUNTERCLOCKWISE_90 = of(BlockRotations.COUNTERCLOCKWISE_90);

    public CompatBlockRotation(BlockRotation rotation) {
        this.rotation = rotation;
    }

    public static CompatBlockRotation of(BlockRotation rotation) {
        return new CompatBlockRotation(rotation);
    }

    public BlockRotation getRotation() {
        return rotation;
    }

    public BlockRotation getRaw() {
        return rotation;
    }

    public BlockRotation toMinecraft() {
        return rotation;
    }

    public static CompatBlockRotation[] values() {
        return new CompatBlockRotation[]{NONE, CLOCKWISE_90, CLOCKWISE_180, COUNTERCLOCKWISE_90};
    }

    public CompatBlockRotation rotate(CompatBlockRotation rotation) {
        return of(BlockRotations.rotate(getRaw(), rotation.getRaw()));
    }

    public net.minecraft.util.math.Direction rotate(net.minecraft.util.math.Direction direction) {
        return getRaw().rotate(direction);
    }

    public Direction rotate(Direction direction) {
        return Direction.of(rotate(direction.getRaw()));
    }

    public static CompatBlockRotation random(CompatRandom random) {
        return of(BlockRotations.random(random));
    }

    public static List<CompatBlockRotation> shuffled(CompatRandom random) {
        List<CompatBlockRotation> rotations = new ArrayList<>();
        for (BlockRotation rotation : BlockRotation.randomRotationOrder(random.getJavaRandom())) {
            rotations.add(of(rotation));
        }
        return rotations;
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
        CompatBlockRotation other = (CompatBlockRotation) obj;
        return getRaw() == other.getRaw();
    }
}
