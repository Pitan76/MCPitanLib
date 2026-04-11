package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.world.item.component.ItemAttributeModifiers;

public class CompatAttributeModifiersComponent {
    private final ItemAttributeModifiers raw;

    @Deprecated
    public CompatAttributeModifiersComponent(ItemAttributeModifiers component) {
        this.raw = component;
    }

    @Deprecated
    public ItemAttributeModifiers raw() {
        return raw;
    }

    public static CompatAttributeModifiersComponent of(ItemAttributeModifiers component) {
        return new CompatAttributeModifiersComponent(component);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatAttributeModifiersComponent that = (CompatAttributeModifiersComponent) obj;
        return raw.equals(that.raw);
    }

    @Override
    public int hashCode() {
        return raw.hashCode();
    }
}
