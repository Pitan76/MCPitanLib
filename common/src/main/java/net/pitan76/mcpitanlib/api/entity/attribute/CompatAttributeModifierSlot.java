package net.pitan76.mcpitanlib.api.entity.attribute;

import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatAttributeModifierSlot implements CompatStringIdentifiable {
    
    public static final CompatAttributeModifierSlot ANY = new CompatAttributeModifierSlot("ANY");
    public static final CompatAttributeModifierSlot MAIN_HAND = new CompatAttributeModifierSlot("MAINHAND");
    public static final CompatAttributeModifierSlot OFF_HAND = new CompatAttributeModifierSlot("OFFHAND");
    public static final CompatAttributeModifierSlot HEAD = new CompatAttributeModifierSlot("HEAD");
    public static final CompatAttributeModifierSlot FEET = new CompatAttributeModifierSlot("FEET");
    public static final CompatAttributeModifierSlot LEGS = new CompatAttributeModifierSlot("LEGS");
    public static final CompatAttributeModifierSlot CHEST = new CompatAttributeModifierSlot("CHEST");
    public static final CompatAttributeModifierSlot ARMOR = new CompatAttributeModifierSlot("ARMOR");
    public static final CompatAttributeModifierSlot BODY = new CompatAttributeModifierSlot("BODY");
    public static final CompatAttributeModifierSlot SADDLE = new CompatAttributeModifierSlot("SADDLE");

    private String raw;

    @Deprecated
    public CompatAttributeModifierSlot(String str) {
        this.raw = raw;
    }

    @Override
    public String asString_compat() {
        return raw;
    }
}
