package net.pitan76.mcpitanlib.api.state.property;

public class BooleanProperty implements IProperty<Boolean> {

    private final net.minecraft.world.level.block.state.properties.BooleanProperty property;

    public BooleanProperty(String name) {
        this(net.minecraft.world.level.block.state.properties.BooleanProperty.create(name));
    }

    public BooleanProperty(net.minecraft.world.level.block.state.properties.BooleanProperty property) {
        this.property = property;
    }

    public static BooleanProperty of(String name) {
        return new BooleanProperty(name);
    }

    public static BooleanProperty of(net.minecraft.world.level.block.state.properties.BooleanProperty property) {
        return new BooleanProperty(property);
    }

    @Override
    public net.minecraft.world.level.block.state.properties.BooleanProperty getProperty() {
        return property;
    }
}
