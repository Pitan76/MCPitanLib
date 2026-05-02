package net.pitan76.mcpitanlib.api.item;

import net.minecraft.world.item.Item;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

public interface ICompatItem {
    default ItemWrapper getWrapper() {
        return this instanceof Item ? ItemWrapper.of((Item) this) : ItemWrapper.of();
    }
}
