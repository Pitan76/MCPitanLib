package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.state.property.Property;

public class UnknownProperty implements IProperty {

    private final net.minecraft.state.property.Property<?> property;

    public UnknownProperty(net.minecraft.state.property.Property<?> property) {
        this.property = property;
    }

    public static UnknownProperty of(net.minecraft.state.property.Property<?> property) {
        return new UnknownProperty(property);
    }

    @Override
    public Property<?> getProperty() {
        return property;
    }
}
