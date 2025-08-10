package net.pitan76.mcpitanlib.api.item.equipment;

import net.minecraft.component.type.EquippableComponent;

public class CompatEquippableComponent {
    private final EquippableComponent raw;

    @Deprecated
    public CompatEquippableComponent(EquippableComponent component) {
        this.raw = component;
    }

    @Deprecated
    public EquippableComponent raw() {
        return raw;
    }

    @Deprecated
    public static CompatEquippableComponent of(EquippableComponent component) {
        return new CompatEquippableComponent(component);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompatEquippableComponent)) return false;
        CompatEquippableComponent that = (CompatEquippableComponent) obj;
        return raw().equals(that.raw());
    }

    @Override
    public int hashCode() {
        if (raw() == null) return super.hashCode();
        return raw().hashCode();
    }
}
