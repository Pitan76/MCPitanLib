package net.pitan76.mcpitanlib.api.util.event;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class ItemEventGenerator {
    public static CompatActionResult onRightClick(Item item, ItemUseEvent e) {
        return CompatActionResult.create(item.use(e.world, e.user.getPlayerEntity(), e.hand));
    }
}
