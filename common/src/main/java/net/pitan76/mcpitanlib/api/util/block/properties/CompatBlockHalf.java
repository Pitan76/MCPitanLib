package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.block.enums.BlockHalf;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatBlockHalf implements CompatStringIdentifiable {
    private final BlockHalf blockHalf;

    public static final CompatBlockHalf TOP = of(BlockHalf.TOP);
    public static final CompatBlockHalf BOTTOM = of(BlockHalf.BOTTOM);

    public CompatBlockHalf(BlockHalf blockHalf) {
        this.blockHalf = blockHalf;
    }

    public static CompatBlockHalf of(BlockHalf blockHalf) {
        return new CompatBlockHalf(blockHalf);
    }

    public BlockHalf getBlockHalf() {
        return blockHalf;
    }

    public String getName() {
        return blockHalf.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return blockHalf.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatBlockHalf other = (CompatBlockHalf) obj;
        return blockHalf == other.blockHalf;
    }
}
