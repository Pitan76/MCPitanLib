package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Map;
import net.pitan76.mcpitanlib.api.item.v2.CompatItemProvider;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;

public class CompatibleArmorItem extends ArmorItem implements CompatItemProvider {
    public final ArmorEquipmentType type;
    public final CompatibleArmorMaterial material;

    @Deprecated
    public static final Map<CompatibleArmorMaterial, RegistryEntry<ArmorMaterial>> CACHE = new java.util.HashMap<>();

    public CompatibleItemSettings settings;

    public CompatibleArmorItem(CompatibleArmorMaterial material, ArmorEquipmentType type, CompatibleItemSettings settings) {
        super(material.build(), type.getType(), settings.build());
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
