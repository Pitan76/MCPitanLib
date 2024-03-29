package net.pitan76.mcpitanlib.api.util.event;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypedActionResult;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;

public class ItemEventGenerator {
    public static TypedActionResult<ItemStack> onRightClick(Item item, ItemUseEvent e) {
        return item.use(e.world, e.user.getPlayerEntity(), e.hand);
    }
}
