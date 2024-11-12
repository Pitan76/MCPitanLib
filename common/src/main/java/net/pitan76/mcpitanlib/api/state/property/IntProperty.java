package net.pitan76.mcpitanlib.api.state.property;

public class IntProperty implements IProperty<Integer> {

    private final net.minecraft.state.property.IntProperty property;

    public IntProperty(String name, int min, int max) {
        this(net.minecraft.state.property.IntProperty.of(name, min, max));
    }

    public IntProperty(String name) {
        this(name, 0, 15);
    }

    public IntProperty(net.minecraft.state.property.IntProperty property) {
        this.property = property;
    }

    public static IntProperty of(String name) {
        return new IntProperty(name);
    }

    public static IntProperty of(String name, int min, int max) {
        return new IntProperty(name, min, max);
    }

    public static IntProperty of(net.minecraft.state.property.IntProperty property) {
        return new IntProperty(property);
    }

    @Override
    public net.minecraft.state.property.IntProperty getProperty() {
        return property;
    }
}
