package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.component.type.AttributeModifiersComponent;

public class AttributeModifiersComponentBuilder {

    public final AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();

    public AttributeModifiersComponentBuilder() {

    }

    public AttributeModifiersComponentBuilder add(CompatEntityAttribute attribute, CompatEntityAttributeModifier modifier, CompatAttributeModifierSlot slot) {
        builder.add(attribute.raw(), modifier.raw(), slot.raw());
        return this;
    }

    @Deprecated
    public AttributeModifiersComponent build_raw() {
        return builder.build();
    }

    public CompatAttributeModifiersComponent build() {
        return CompatAttributeModifiersComponent.of(build_raw());
    }
}
