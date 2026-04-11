package net.pitan76.mcpitanlib.api.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.core.registries.BuiltInRegistries;

public class DefaultItemGroups {
    public static final CreativeModeTab BUILDING_BLOCKS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.BUILDING_BLOCKS);
    public static final CreativeModeTab COLORED_BLOCKS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.COLORED_BLOCKS); // if 1.19.2 and below, BUILDING_BLOCKS
    public static final CreativeModeTab NATURAL = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.NATURAL_BLOCKS); // if 1.19.2 and below, DECORATIONS
    public static final CreativeModeTab FUNCTIONAL = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.FUNCTIONAL_BLOCKS); // if 1.19.2 and below, TRANSPORTATION
    public static final CreativeModeTab REDSTONE = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.REDSTONE_BLOCKS);
    public static final CreativeModeTab HOTBAR = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.HOTBAR);
    public static final CreativeModeTab SEARCH = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.SEARCH);
    public static final CreativeModeTab TOOLS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.TOOLS_AND_UTILITIES);
    public static final CreativeModeTab COMBAT = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.COMBAT);
    public static final CreativeModeTab FOOD_AND_DRINK = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.FOOD_AND_DRINKS); // if 1.19.2 and below, FOOD
    public static final CreativeModeTab INGREDIENTS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.INGREDIENTS); // if 1.19.2 and below, MISC
    public static final CreativeModeTab SPAWN_EGGS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.SPAWN_EGGS); // if 1.19.2 and below, MISC
    public static final CreativeModeTab OPERATOR = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.OP_BLOCKS); // if 1.19.2 and below, MISC
    public static final CreativeModeTab INVENTORY = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.INVENTORY);

    // ～1.19.2 Item Group
    public static final CreativeModeTab BREWING = FOOD_AND_DRINK;
    public static final CreativeModeTab TRANSPORTATION = FUNCTIONAL;
    public static final CreativeModeTab DECORATIONS = NATURAL;
    public static final CreativeModeTab MISC = INGREDIENTS;
}