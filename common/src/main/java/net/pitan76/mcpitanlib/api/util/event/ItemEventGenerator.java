package net.pitan76.mcpitanlib.api.util.event;

import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypedActionResult;

public class ItemEventGenerator {
    public static TypedActionResult<ItemStack> onRightClick(Item item, ItemUseEvent e) {
        return item.use(e.world, e.user.getPlayerEntity(), e.hand);
    }
}
