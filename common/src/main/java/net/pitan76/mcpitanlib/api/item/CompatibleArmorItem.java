package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.ArmorItem;
import net.pitan76.mcpitanlib.api.item.v2.CompatItemProvider;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;

public class CompatibleArmorItem extends ArmorItem implements CompatItemProvider {
    public final ArmorEquipmentType type;


    public CompatibleItemSettings settings;

    public CompatibleArmorItem(CompatibleArmorMaterial material, ArmorEquipmentType type, CompatibleItemSettings settings) {
        super(material.build(), type.getSlot(), settings.build());
        this.type = type;

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
        if (!(this.getMaterial() instanceof CompatibleArmorMaterial)) {
            throw new IllegalStateException("ArmorMaterial is not CompatibleArmorMaterial");
        }
        return (CompatibleArmorMaterial) this.getMaterial();
    }
}
