package net.pitan76.mcpitanlib.api.state.property;

public class BooleanProperty implements IProperty<Boolean> {

    private final net.minecraft.state.property.BooleanProperty property;

    public BooleanProperty(String name) {
        this(net.minecraft.state.property.BooleanProperty.of(name));
    }

    public BooleanProperty(net.minecraft.state.property.BooleanProperty property) {
        this.property = property;
    }

    public static BooleanProperty of(String name) {
        return new BooleanProperty(name);
    }

    public static BooleanProperty of(net.minecraft.state.property.BooleanProperty property) {
        return new BooleanProperty(property);
    }

    @Override
    public net.minecraft.state.property.BooleanProperty getProperty() {
        return property;
    }
}
