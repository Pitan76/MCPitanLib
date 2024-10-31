package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Map;

public class CompatibleArmorItem extends ArmorItem implements ExtendItemProvider {
    public final ArmorEquipmentType type;
    public final CompatibleArmorMaterial material;

    @Deprecated
    public static final Map<CompatibleArmorMaterial, RegistryEntry<ArmorMaterial>> CACHE = new java.util.HashMap<>();

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
