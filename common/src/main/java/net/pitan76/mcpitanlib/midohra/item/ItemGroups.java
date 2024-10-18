package net.pitan76.mcpitanlib.midohra.item;

import net.pitan76.mcpitanlib.api.item.DefaultItemGroups;

public class ItemGroups {
    public static final ItemGroupWrapper BUILDING_BLOCKS = of(DefaultItemGroups.BUILDING_BLOCKS);
    public static final ItemGroupWrapper COLORED_BLOCKS = of(DefaultItemGroups.COLORED_BLOCKS);
    public static final ItemGroupWrapper NATURAL = of(DefaultItemGroups.NATURAL);
    public static final ItemGroupWrapper FUNCTIONAL = of(DefaultItemGroups.FUNCTIONAL);
    public static final ItemGroupWrapper REDSTONE = of(DefaultItemGroups.REDSTONE);
    public static final ItemGroupWrapper HOTBAR = of(DefaultItemGroups.HOTBAR);
    public static final ItemGroupWrapper SEARCH = of(DefaultItemGroups.SEARCH);
    public static final ItemGroupWrapper TOOLS = of(DefaultItemGroups.TOOLS);
    public static final ItemGroupWrapper COMBAT = of(DefaultItemGroups.COMBAT);
    public static final ItemGroupWrapper FOOD_AND_DRINK = of(DefaultItemGroups.FOOD_AND_DRINK);
    public static final ItemGroupWrapper INGREDIENTS = of(DefaultItemGroups.INGREDIENTS);
    public static final ItemGroupWrapper SPAWN_EGGS = of(DefaultItemGroups.SPAWN_EGGS);
    public static final ItemGroupWrapper OPERATOR = of(DefaultItemGroups.OPERATOR);
    public static final ItemGroupWrapper INVENTORY = of(DefaultItemGroups.INVENTORY);

    // - 1.19.2 Item Group
    public static final ItemGroupWrapper BREWING = FOOD_AND_DRINK;
    public static final ItemGroupWrapper TRANSPORTATION = FUNCTIONAL;
    public static final ItemGroupWrapper DECORATIONS = NATURAL;
    public static final ItemGroupWrapper MISC = INGREDIENTS;

    private static ItemGroupWrapper of(net.minecraft.item.ItemGroup itemGroup) {
        return ItemGroupWrapper.of(itemGroup);
    }
}
