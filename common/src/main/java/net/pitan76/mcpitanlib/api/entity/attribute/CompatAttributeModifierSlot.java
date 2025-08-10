package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.component.type.AttributeModifierSlot;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatAttributeModifierSlot implements CompatStringIdentifiable {

    private final AttributeModifierSlot raw;

    public static final CompatAttributeModifierSlot ANY = new CompatAttributeModifierSlot(AttributeModifierSlot.ANY);
    public static final CompatAttributeModifierSlot MAIN_HAND = new CompatAttributeModifierSlot(AttributeModifierSlot.MAINHAND);
    public static final CompatAttributeModifierSlot OFF_HAND = new CompatAttributeModifierSlot(AttributeModifierSlot.OFFHAND);
    public static final CompatAttributeModifierSlot HEAD = new CompatAttributeModifierSlot(AttributeModifierSlot.HEAD);
    public static final CompatAttributeModifierSlot FEET = new CompatAttributeModifierSlot(AttributeModifierSlot.FEET);
    public static final CompatAttributeModifierSlot LEGS = new CompatAttributeModifierSlot(AttributeModifierSlot.LEGS);
    public static final CompatAttributeModifierSlot CHEST = new CompatAttributeModifierSlot(AttributeModifierSlot.CHEST);
    public static final CompatAttributeModifierSlot ARMOR = new CompatAttributeModifierSlot(AttributeModifierSlot.ARMOR);
    public static final CompatAttributeModifierSlot BODY = new CompatAttributeModifierSlot(AttributeModifierSlot.BODY);
    public static final CompatAttributeModifierSlot SADDLE = new CompatAttributeModifierSlot(AttributeModifierSlot.BODY);

    @Deprecated
    public CompatAttributeModifierSlot(AttributeModifierSlot raw) {
        this.raw = raw;
    }

    public static CompatAttributeModifierSlot of(AttributeModifierSlot slot) {
        if (slot == AttributeModifierSlot.ANY) return ANY;
        if (slot == AttributeModifierSlot.MAINHAND) return MAIN_HAND;
        if (slot == AttributeModifierSlot.OFFHAND) return OFF_HAND;
        if (slot == AttributeModifierSlot.HEAD) return HEAD;
        if (slot == AttributeModifierSlot.FEET) return FEET;
        if (slot == AttributeModifierSlot.LEGS) return LEGS;
        if (slot == AttributeModifierSlot.CHEST) return CHEST;
        if (slot == AttributeModifierSlot.ARMOR) return ARMOR;
        if (slot == AttributeModifierSlot.BODY) return BODY;

        return new CompatAttributeModifierSlot(slot);
    }

    @Deprecated
    public AttributeModifierSlot raw() {
        return raw;
    }

    @Override
    public String asString_compat() {
        return raw().asString();
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
