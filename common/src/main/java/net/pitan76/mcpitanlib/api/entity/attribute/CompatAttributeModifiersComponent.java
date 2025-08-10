package net.pitan76.mcpitanlib.api.entity.attribute;

import java.util.List;

public class CompatAttributeModifiersComponent {

    public List<AttributeModifiersComponentBuilder.Data> dataList;

    @Deprecated
    public CompatAttributeModifiersComponent() {

    }

    @Deprecated
    public CompatAttributeModifiersComponent(List<AttributeModifiersComponentBuilder.Data> data) {
        this.dataList = data;
    }
}
