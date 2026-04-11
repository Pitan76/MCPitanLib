package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.world.item.component.ItemAttributeModifiers;

public class AttributeModifiersComponentBuilder {

    public final ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

    public AttributeModifiersComponentBuilder() {

    }

    public AttributeModifiersComponentBuilder add(CompatEntityAttribute attribute, CompatEntityAttributeModifier modifier, CompatAttributeModifierSlot slot) {
        builder.add(attribute.raw(), modifier.raw(), slot.raw());
        return this;
    }

    @Deprecated
    public ItemAttributeModifiers build_raw() {
        return builder.build();
    }

    public CompatAttributeModifiersComponent build() {
        return CompatAttributeModifiersComponent.of(build_raw());
    }
}
