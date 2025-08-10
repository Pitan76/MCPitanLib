package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.component.type.AttributeModifiersComponent;

public class CompatAttributeModifiersComponent {
    private final AttributeModifiersComponent raw;

    @Deprecated
    public CompatAttributeModifiersComponent(AttributeModifiersComponent component) {
        this.raw = component;
    }

    @Deprecated
    public AttributeModifiersComponent raw() {
        return raw;
    }

    public static CompatAttributeModifiersComponent of(AttributeModifiersComponent component) {
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
