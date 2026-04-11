package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.Property;

public class UnknownProperty implements IProperty {

    private final net.minecraft.world.level.block.state.properties.Property<?> property;

    public UnknownProperty(net.minecraft.world.level.block.state.properties.Property<?> property) {
        this.property = property;
    }

    public static UnknownProperty of(net.minecraft.world.level.block.state.properties.Property<?> property) {
        return new UnknownProperty(property);
    }

    @Override
    public Property<?> getProperty() {
        return property;
    }
}
