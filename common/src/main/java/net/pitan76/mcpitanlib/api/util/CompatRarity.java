package net.pitan76.mcpitanlib.api.util;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class CompatRarity implements CompatStringIdentifiable {
    private final Rarity rarity;

    public static final CompatRarity NONE = of(Rarity.COMMON);
    public static final CompatRarity COMMON = of(Rarity.COMMON);
    public static final CompatRarity UNCOMMON = of(Rarity.UNCOMMON);
    public static final CompatRarity RARE = of(Rarity.RARE);
    public static final CompatRarity EPIC = of(Rarity.EPIC);

    public CompatRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public static CompatRarity of(Rarity rarity) {
        return new CompatRarity(rarity);
    }

    public Rarity get() {
        return rarity;
    }

    public ChatFormatting getFormatting() {
        return rarity.color();
    }

    public String getName() {
        return rarity.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    public static CompatRarity fromString(String name) {
        switch (name) {
            case "common":
                return COMMON;
            case "uncommon":
                return UNCOMMON;
            case "rare":
                return RARE;
            case "epic":
                return EPIC;
            default:
                return NONE;
        }
    }
}
