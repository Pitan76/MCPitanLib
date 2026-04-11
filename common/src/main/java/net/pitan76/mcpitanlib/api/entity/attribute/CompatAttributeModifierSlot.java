package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.world.entity.EquipmentSlotGroup;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatAttributeModifierSlot implements CompatStringIdentifiable {

    private final EquipmentSlotGroup raw;

    public static final CompatAttributeModifierSlot ANY = new CompatAttributeModifierSlot(EquipmentSlotGroup.ANY);
    public static final CompatAttributeModifierSlot MAIN_HAND = new CompatAttributeModifierSlot(EquipmentSlotGroup.MAINHAND);
    public static final CompatAttributeModifierSlot OFF_HAND = new CompatAttributeModifierSlot(EquipmentSlotGroup.OFFHAND);
    public static final CompatAttributeModifierSlot HEAD = new CompatAttributeModifierSlot(EquipmentSlotGroup.HEAD);
    public static final CompatAttributeModifierSlot FEET = new CompatAttributeModifierSlot(EquipmentSlotGroup.FEET);
    public static final CompatAttributeModifierSlot LEGS = new CompatAttributeModifierSlot(EquipmentSlotGroup.LEGS);
    public static final CompatAttributeModifierSlot CHEST = new CompatAttributeModifierSlot(EquipmentSlotGroup.CHEST);
    public static final CompatAttributeModifierSlot ARMOR = new CompatAttributeModifierSlot(EquipmentSlotGroup.ARMOR);
    public static final CompatAttributeModifierSlot BODY = new CompatAttributeModifierSlot(EquipmentSlotGroup.BODY);
    public static final CompatAttributeModifierSlot SADDLE = new CompatAttributeModifierSlot(EquipmentSlotGroup.SADDLE);

    @Deprecated
    public CompatAttributeModifierSlot(EquipmentSlotGroup raw) {
        this.raw = raw;
    }

    public static CompatAttributeModifierSlot of(EquipmentSlotGroup slot) {
        if (slot == EquipmentSlotGroup.ANY) return ANY;
        if (slot == EquipmentSlotGroup.MAINHAND) return MAIN_HAND;
        if (slot == EquipmentSlotGroup.OFFHAND) return OFF_HAND;
        if (slot == EquipmentSlotGroup.HEAD) return HEAD;
        if (slot == EquipmentSlotGroup.FEET) return FEET;
        if (slot == EquipmentSlotGroup.LEGS) return LEGS;
        if (slot == EquipmentSlotGroup.CHEST) return CHEST;
        if (slot == EquipmentSlotGroup.ARMOR) return ARMOR;
        if (slot == EquipmentSlotGroup.BODY) return BODY;
        if (slot == EquipmentSlotGroup.SADDLE) return SADDLE;

        return new CompatAttributeModifierSlot(slot);
    }

    @Deprecated
    public EquipmentSlotGroup raw() {
        return raw;
    }

    @Override
    public String asString_compat() {
        return raw().getSerializedName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompatAttributeModifierSlot)) return false;
        CompatAttributeModifierSlot that = (CompatAttributeModifierSlot) obj;
        return raw().equals(that.raw());
    }

    @Override
    public int hashCode() {
        if (raw() == null) return super.hashCode();
        return raw().hashCode();
    }
}
