package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class CompatItems {
    public Item SHORT_GRASS = Items.SHORT_GRASS;
    public Item TALL_GRASS = Items.TALL_GRASS;
    public Item TURTLE_SCUTE = Items.SCUTE;

    // Compatibility
    public Item GRASS = SHORT_GRASS;
    public Item SCUTE = TURTLE_SCUTE;

    public static boolean isExist(Item item) {
        return item != null && item != Items.AIR;
    }
}
