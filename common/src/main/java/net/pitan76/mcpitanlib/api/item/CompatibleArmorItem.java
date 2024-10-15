package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.ArmorItem;

public class CompatibleArmorItem extends ArmorItem implements ExtendItemProvider {
    public final ArmorEquipmentType type;
    public final CompatibleArmorMaterial material;

    public CompatibleArmorItem(CompatibleArmorMaterial material, ArmorEquipmentType type, CompatibleItemSettings settings) {
        super(material.build(), type.getType(), settings.build());
        this.type = type;
        this.material = material;
    }

    /**
     * get ArmorEquipmentType
     * @return ArmorEquipmentType
     */
    public ArmorEquipmentType getArmorEquipmentType() {
        return type;
    }

    /**
     * get ArmorMaterial
     * @return CompatibleArmorMaterial
     */
    public CompatibleArmorMaterial getArmorMaterial() {
        return material;
    }
}
