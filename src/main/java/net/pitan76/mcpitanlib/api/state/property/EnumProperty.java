package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.util.StringRepresentable;

import java.util.function.Predicate;

public class EnumProperty<T extends Enum<T> & StringRepresentable> implements IProperty<T> {

    private final net.minecraft.world.level.block.state.properties.EnumProperty<T> property;

    public EnumProperty(String name, Class<T> type) {
        this(net.minecraft.world.level.block.state.properties.EnumProperty.create(name, type));
    }

    public EnumProperty(String name, Class<T> type, Predicate<T> filter) {
        this(net.minecraft.world.level.block.state.properties.EnumProperty.create(name, type, filter));
    }

    public EnumProperty(net.minecraft.world.level.block.state.properties.EnumProperty<T> property) {
        this.property = property;
    }

    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> of(String name, Class<T> type) {
        return new EnumProperty<>(name, type);
    }

    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> of(String name, Class<T> type, Predicate<T> filter) {
        return new EnumProperty<>(name, type, filter);
    }


    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> of(net.minecraft.world.level.block.state.properties.EnumProperty<T> property) {
        return new EnumProperty<>(property);
    }

    @Override
    public net.minecraft.world.level.block.state.properties.EnumProperty<T> getProperty() {
        return property;
    }
}
