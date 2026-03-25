package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.item.v2.CompatItemProvider;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;

public class CompatibleArmorItem extends Item implements CompatItemProvider {
    public final ArmorEquipmentType type;
    public final CompatibleArmorMaterial material;

    public CompatibleItemSettings settings;

    public CompatibleArmorItem(CompatibleArmorMaterial material, ArmorEquipmentType type, CompatibleItemSettings settings) {
        super(settings.build().armor(material.build(), type.getType()));
        this.type = type;
        this.material = material;

        this.settings = settings;
    }

    @Override
    public CompatibleItemSettings getCompatSettings() {
        return settings;
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
