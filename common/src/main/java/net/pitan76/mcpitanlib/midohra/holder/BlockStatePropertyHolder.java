package net.pitan76.mcpitanlib.midohra.holder;

import net.pitan76.mcpitanlib.api.state.property.IProperty;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

public interface BlockStatePropertyHolder {
    BlockState getBlockState();

    default <T extends Comparable<T>, V extends T> BlockState with(IProperty<T> property, V value) {
        return getBlockState().with(property, value);
    }

    default boolean has(IProperty<?> property) {
        return getBlockState().contains(property);
    }

    default <T extends Comparable<T>> T get(IProperty<T> property) {
        return getBlockState().get(property);
    }

    default <T extends Comparable<T>> BlockState cycle(IProperty<T> property) {
        return getBlockState().cycle(property);
    }
}
