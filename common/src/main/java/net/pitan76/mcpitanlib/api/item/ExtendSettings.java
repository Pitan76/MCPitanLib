package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.midohra.item.ItemGroupWrapper;

import java.util.function.Supplier;

/**
 * Use {@link CompatibleItemSettings} instead.
 */
public class ExtendSettings extends Item.Settings {

    // ～1.19.2
    @Deprecated
    public ExtendSettings addGroup(ItemGroup itemGroup) {
        return this;
    }

    // 1.19.3～
    // identifier: Item ID
    @Deprecated
    public ExtendSettings addGroup(ItemGroup itemGroup, Identifier identifier) {
        CreativeTabManager.addItem(itemGroup, identifier);
        return this;
    }

    @Deprecated
    public ExtendSettings addGroup(Supplier<ItemGroup> itemGroup, Identifier identifier) {
        CreativeTabManager.addItem(itemGroup, identifier);
        return this;
    }

    public ExtendSettings addGroup(ItemGroupWrapper itemGroup, Identifier identifier) {
        if (itemGroup.getKey() != null) {
            CreativeTabManager.addItem(itemGroup.getKey(), identifier);
        } else {
            CreativeTabManager.addItem(itemGroup.getSupplier(), identifier);
        }
        return this;
    }
}
