package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;

public interface IProperty<T extends Comparable<T>> {
    default void apply(AppendPropertiesArgs args) {
        args.addProperty(getProperty());
    }

    default T get(BlockState state) {
        return state.get(getProperty());
    }

    default BlockState with(BlockState state, T value) {
        return state.with(getProperty(), value);
    }

    default boolean contains(BlockState state) {
        return state.contains(getProperty());
    }

    default BlockState cycle(BlockState state) {
        return state.cycle(getProperty());
    }

    default String getName() {
        return getProperty().getName();
    }

    Property<T> getProperty();

    default net.pitan76.mcpitanlib.midohra.block.BlockState with(net.pitan76.mcpitanlib.midohra.block.BlockState state, T value) {
        return state.with(getProperty(), value);
    }

    default T get(net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return state.get(getProperty());
    }

    default boolean contains(net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return state.contains(getProperty());
    }

    default net.pitan76.mcpitanlib.midohra.block.BlockState cycle(net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return state.cycle(getProperty());
    }
}
