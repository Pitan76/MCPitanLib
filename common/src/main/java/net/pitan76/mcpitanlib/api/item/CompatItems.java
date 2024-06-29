package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class CompatItems {
    public static Item SHORT_GRASS = Items.SHORT_GRASS;
    public static Item TALL_GRASS = Items.TALL_GRASS;
    public static Item TURTLE_SCUTE = Items.TURTLE_SCUTE;

    // Compatibility
    public static Item GRASS = SHORT_GRASS;
    public static Item SCUTE = TURTLE_SCUTE;

    public static boolean isExist(Item item) {
        return item != null && item != Items.AIR;
    }
}
