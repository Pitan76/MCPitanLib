package net.pitan76.mcpitanlib.api.item.equipment;

import net.minecraft.world.item.equipment.Equippable;

public class CompatEquippableComponent {
    private final Equippable raw;

    @Deprecated
    public CompatEquippableComponent(Equippable component) {
        this.raw = component;
    }

    @Deprecated
    public Equippable raw() {
        return raw;
    }

    @Deprecated
    public static CompatEquippableComponent of(Equippable component) {
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
