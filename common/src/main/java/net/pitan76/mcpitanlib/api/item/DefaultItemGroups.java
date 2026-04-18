package net.pitan76.mcpitanlib.api.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.BuiltInRegistries;

public class DefaultItemGroups {

    private static final ResourceKey<CreativeModeTab> _BUILDING_BLOCKS = createKey("building_blocks");
    private static final ResourceKey<CreativeModeTab> _COLORED_BLOCKS = createKey("colored_blocks");
    private static final ResourceKey<CreativeModeTab> _NATURAL_BLOCKS = createKey("natural_blocks");
    private static final ResourceKey<CreativeModeTab> _FUNCTIONAL_BLOCKS = createKey("functional_blocks");
    private static final ResourceKey<CreativeModeTab> _REDSTONE_BLOCKS = createKey("redstone_blocks");
    private static final ResourceKey<CreativeModeTab> _HOTBAR = createKey("hotbar");
    private static final ResourceKey<CreativeModeTab> _SEARCH = createKey("search");
    private static final ResourceKey<CreativeModeTab> _TOOLS_AND_UTILITIES = createKey("tools_and_utilities");
    private static final ResourceKey<CreativeModeTab> _COMBAT = createKey("combat");
    private static final ResourceKey<CreativeModeTab> _FOOD_AND_DRINKS = createKey("food_and_drinks");
    private static final ResourceKey<CreativeModeTab> _INGREDIENTS = createKey("ingredients");
    private static final ResourceKey<CreativeModeTab> _SPAWN_EGGS = createKey("spawn_eggs");
    private static final ResourceKey<CreativeModeTab> _OP_BLOCKS = createKey("op_blocks");
    private static final ResourceKey<CreativeModeTab> _INVENTORY = createKey("inventory");

    private static ResourceKey<CreativeModeTab> createKey(final String id) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.withDefaultNamespace(id));
    }
    
    public static final CreativeModeTab BUILDING_BLOCKS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_BUILDING_BLOCKS);
    public static final CreativeModeTab COLORED_BLOCKS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_COLORED_BLOCKS); // if 1.19.2 and below, BUILDING_BLOCKS
    public static final CreativeModeTab NATURAL = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_NATURAL_BLOCKS); // if 1.19.2 and below, DECORATIONS
    public static final CreativeModeTab FUNCTIONAL = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_FUNCTIONAL_BLOCKS); // if 1.19.2 and below, TRANSPORTATION
    public static final CreativeModeTab REDSTONE = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_REDSTONE_BLOCKS);
    public static final CreativeModeTab HOTBAR = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_HOTBAR);
    public static final CreativeModeTab SEARCH = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_SEARCH);
    public static final CreativeModeTab TOOLS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_TOOLS_AND_UTILITIES);
    public static final CreativeModeTab COMBAT = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_COMBAT);
    public static final CreativeModeTab FOOD_AND_DRINK = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_FOOD_AND_DRINKS); // if 1.19.2 and below, FOOD
    public static final CreativeModeTab INGREDIENTS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_INGREDIENTS); // if 1.19.2 and below, MISC
    public static final CreativeModeTab SPAWN_EGGS = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_SPAWN_EGGS); // if 1.19.2 and below, MISC
    public static final CreativeModeTab OPERATOR = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_OP_BLOCKS); // if 1.19.2 and below, MISC
    public static final CreativeModeTab INVENTORY = BuiltInRegistries.CREATIVE_MODE_TAB.getValue(_INVENTORY);

    // ～1.19.2 Item Group
    public static final CreativeModeTab BREWING = FOOD_AND_DRINK;
    public static final CreativeModeTab TRANSPORTATION = FUNCTIONAL;
    public static final CreativeModeTab DECORATIONS = NATURAL;
    public static final CreativeModeTab MISC = INGREDIENTS;
}