package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.ArmorItem;

public class CompatibleArmorItem extends ArmorItem implements ExtendItemProvider {
    public final ArmorEquipmentType type;

    public CompatibleArmorItem(CompatibleArmorMaterial material, ArmorEquipmentType type, CompatibleItemSettings settings) {
        super(material, type.getType(), settings.build());
        this.type = type;
    }

    /**
     * get ArmorEquipmentType
     * @return ArmorEquipmentType
     */
    public ArmorEquipmentType getArmorEquipmentType() {
        return type;
    }

    /**
     * @deprecated Use {@link #getArmorEquipmentType()} instead
     */
    @Deprecated
    @Override
    public Type getType() {
        return super.getType();
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
