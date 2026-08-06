package net.pitan76.mcpitanlib.api.item;

import net.minecraft.world.item.Item;
import net.pitan76.mcpitanlib.api.item.v2.CompatItemProvider;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;

public class CompatibleArmorItem extends Item implements CompatItemProvider {
    public final ArmorEquipmentType type;
    public final CompatibleArmorMaterial material;

    public CompatibleItemSettings settings;

    public CompatibleArmorItem(CompatibleArmorMaterial material, ArmorEquipmentType type, CompatibleItemSettings settings) {
        super(buildProperties(material, type, settings));
        this.type = type;
        this.material = material;

        this.settings = settings;
    }

    /**
     * {@link CompatibleArmorMaterial#build()} passes 0 as the durability multiplier, so
     * {@code humanoidArmor} ends up setting a max damage of 0 on every piece and the armor
     * breaks on the first hit that damages it.
     * <p>
     * The multiplier cannot be recovered from the material either: it reports an absolute
     * durability per slot while vanilla multiplies one shared value by a per slot constant, so
     * no single multiplier reproduces all four. Apply the material's own value per item instead,
     * after {@code humanoidArmor} has run.
     */
    private static Item.Properties buildProperties(CompatibleArmorMaterial material, ArmorEquipmentType type, CompatibleItemSettings settings) {
        Item.Properties properties = settings.build().humanoidArmor(material.build(), type.getType());

        int durability = material.getDurability(type);
        if (durability > 0)
            properties = properties.durability(durability);

        return properties;
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
