package net.pitan76.mcpitanlib.api.entity.attribute;

import java.util.ArrayList;
import java.util.List;

public class AttributeModifiersComponentBuilder {

    private List<Data> dataList = new ArrayList<>();

    public AttributeModifiersComponentBuilder() {

    }

    public AttributeModifiersComponentBuilder add(CompatEntityAttribute attribute, CompatEntityAttributeModifier modifier, CompatAttributeModifierSlot slot) {
        dataList.add(new Data(attribute, modifier, slot));
        return this;
    }

    public CompatAttributeModifiersComponent build() {
        return new CompatAttributeModifiersComponent(dataList);
    }

    public static class Data {
        public CompatEntityAttribute attribute;
        public CompatEntityAttributeModifier modifier;
        public CompatAttributeModifierSlot slot;

        public Data(CompatEntityAttribute attribute, CompatEntityAttributeModifier modifier, CompatAttributeModifierSlot slot) {
            this.attribute = attribute;
            this.modifier = modifier;
            this.slot = slot;
        }
    }
}
