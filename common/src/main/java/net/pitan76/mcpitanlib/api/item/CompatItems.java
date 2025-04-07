package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class CompatItems {
    public static Item SHORT_GRASS = Items.GRASS;
    public static Item TALL_GRASS = Items.TALL_GRASS;
    public static Item TURTLE_SCUTE = Items.SCUTE;

    // Compatibility
    public static Item GRASS = SHORT_GRASS;
    public static Item SCUTE = TURTLE_SCUTE;

    // ----

    public static Item AIR = Items.AIR;
    public static Item STONE = Items.STONE;
    public static Item COBBLESTONE = Items.COBBLESTONE;
    public static Item GRASS_BLOCK = Items.GRASS_BLOCK;
    public static Item DIRT = Items.DIRT;
    public static Item SAND = Items.SAND;
    public static Item RED_SAND = Items.RED_SAND;
    public static Item GRANITE = Items.GRANITE;
    public static Item POLISHED_GRANITE = Items.POLISHED_GRANITE;
    public static Item DIORITE = Items.DIORITE;
    public static Item POLISHED_DIORITE = Items.POLISHED_DIORITE;
    public static Item ANDESITE = Items.ANDESITE;
    public static Item POLISHED_ANDESITE = Items.POLISHED_ANDESITE;
    public static Item COARSE_DIRT = Items.COARSE_DIRT;
    public static Item PODZOL = Items.PODZOL;
    public static Item OAK_LOG = Items.OAK_LOG;
    public static Item SPRUCE_LOG = Items.SPRUCE_LOG;
    public static Item BIRCH_LOG = Items.BIRCH_LOG;
    public static Item JUNGLE_LOG = Items.JUNGLE_LOG;
    public static Item ACACIA_LOG = Items.ACACIA_LOG;
    public static Item DARK_OAK_LOG = Items.DARK_OAK_LOG;
    public static Item OAK_PLANKS = Items.OAK_PLANKS;
    public static Item SPRUCE_PLANKS = Items.SPRUCE_PLANKS;
    public static Item BIRCH_PLANKS = Items.BIRCH_PLANKS;
    public static Item JUNGLE_PLANKS = Items.JUNGLE_PLANKS;
    public static Item ACACIA_PLANKS = Items.ACACIA_PLANKS;
    public static Item DARK_OAK_PLANKS = Items.DARK_OAK_PLANKS;
    public static Item GLASS = Items.GLASS;

    public static Item STICK = Items.STICK;
    public static Item GLASS_BOTTLE = Items.GLASS_BOTTLE;
    public static Item EXP_BOTTLE = Items.EXPERIENCE_BOTTLE;

    public static Item GUNPOWDER = Items.GUNPOWDER;
    public static Item REDSTONE = Items.REDSTONE;
    public static Item COAL = Items.COAL;
    public static Item CHARCOAL = Items.CHARCOAL;
    public static Item IRON_INGOT = Items.IRON_INGOT;
    public static Item COPPER_INGOT = Items.COPPER_INGOT;
    public static Item GOLD_INGOT = Items.GOLD_INGOT;
    public static Item DIAMOND = Items.DIAMOND;
    public static Item EMERALD = Items.EMERALD;
    public static Item LAPIS_LAZULI = Items.LAPIS_LAZULI;
    public static Item NETHERITE_INGOT = Items.NETHERITE_INGOT;
    public static Item NETHERITE_SCRAP = Items.NETHERITE_SCRAP;
    public static Item QUARTZ = Items.QUARTZ;

    // ----

    public static boolean isExist(Item item) {
        return item != null && item != Items.AIR;
    }
}
