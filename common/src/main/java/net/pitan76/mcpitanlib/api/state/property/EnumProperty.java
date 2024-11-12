package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.util.StringIdentifiable;

import java.util.function.Predicate;

public class EnumProperty<T extends Enum<T> & StringIdentifiable> implements IProperty<T> {

    private final net.minecraft.state.property.EnumProperty<T> property;

    public EnumProperty(String name, Class<T> type) {
        this(net.minecraft.state.property.EnumProperty.of(name, type));
    }

    public EnumProperty(String name, Class<T> type, Predicate<T> filter) {
        this(net.minecraft.state.property.EnumProperty.of(name, type, filter));
    }

    public EnumProperty(net.minecraft.state.property.EnumProperty<T> property) {
        this.property = property;
    }

    public static <T extends Enum<T> & StringIdentifiable> EnumProperty<T> of(String name, Class<T> type) {
        return new EnumProperty<>(name, type);
    }

    public static <T extends Enum<T> & StringIdentifiable> EnumProperty<T> of(String name, Class<T> type, Predicate<T> filter) {
        return new EnumProperty<>(name, type, filter);
    }


    public static <T extends Enum<T> & StringIdentifiable> EnumProperty<T> of(net.minecraft.state.property.EnumProperty<T> property) {
        return new EnumProperty<>(property);
    }

    @Override
    public net.minecraft.state.property.EnumProperty<T> getProperty() {
        return property;
    }
}
