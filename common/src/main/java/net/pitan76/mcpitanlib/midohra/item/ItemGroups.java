package net.pitan76.mcpitanlib.midohra.item;

import net.pitan76.mcpitanlib.api.item.DefaultItemGroups;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ItemGroups {
    public static final ItemGroupWrapper BUILDING_BLOCKS = of(net.minecraft.item.ItemGroups.BUILDING_BLOCKS);
    public static final ItemGroupWrapper COLORED_BLOCKS = of(net.minecraft.item.ItemGroups.COLORED_BLOCKS);
    public static final ItemGroupWrapper NATURAL = of(net.minecraft.item.ItemGroups.NATURAL);
    public static final ItemGroupWrapper FUNCTIONAL = of(net.minecraft.item.ItemGroups.FUNCTIONAL);
    public static final ItemGroupWrapper REDSTONE = of(net.minecraft.item.ItemGroups.REDSTONE);
    public static final ItemGroupWrapper HOTBAR = of(net.minecraft.item.ItemGroups.HOTBAR);
    public static final ItemGroupWrapper SEARCH = of(net.minecraft.item.ItemGroups.SEARCH);
    public static final ItemGroupWrapper TOOLS = of(net.minecraft.item.ItemGroups.TOOLS);
    public static final ItemGroupWrapper COMBAT = of(net.minecraft.item.ItemGroups.COMBAT);
    public static final ItemGroupWrapper FOOD_AND_DRINK = of(net.minecraft.item.ItemGroups.FOOD_AND_DRINK);
    public static final ItemGroupWrapper INGREDIENTS = of(net.minecraft.item.ItemGroups.INGREDIENTS);
    public static final ItemGroupWrapper SPAWN_EGGS = of(net.minecraft.item.ItemGroups.SPAWN_EGGS);
    public static final ItemGroupWrapper OPERATOR = of(net.minecraft.item.ItemGroups.OPERATOR);
    public static final ItemGroupWrapper INVENTORY = of(net.minecraft.item.ItemGroups.INVENTORY);

    // - 1.19.2 Item Group
    public static final ItemGroupWrapper BREWING = FOOD_AND_DRINK;
    public static final ItemGroupWrapper TRANSPORTATION = FUNCTIONAL;
    public static final ItemGroupWrapper DECORATIONS = NATURAL;
    public static final ItemGroupWrapper MISC = INGREDIENTS;

    private static ItemGroupWrapper of(net.minecraft.item.ItemGroup itemGroup) {
        return ItemGroupWrapper.of(itemGroup);
    }

    private static ItemGroupWrapper of(net.minecraft.registry.RegistryKey<net.minecraft.item.ItemGroup> key) {
        return ItemGroupWrapper.of(key);
    }

    private static ItemGroupWrapper of(Supplier<net.minecraft.item.ItemGroup> itemGroupSupplier) {
        return ItemGroupWrapper.of(itemGroupSupplier);
    }

    public static List<ItemGroupWrapper> getGroupsToDisplay() {
        return net.minecraft.item.ItemGroups.getGroupsToDisplay().stream().map(ItemGroupWrapper::of).collect(Collectors.toList());
    }

    public static List<ItemGroupWrapper> getGroups() {
        return net.minecraft.item.ItemGroups.getGroups().stream().map(ItemGroupWrapper::of).collect(Collectors.toList());
    }
}
