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

    Property<T> getProperty();
}
