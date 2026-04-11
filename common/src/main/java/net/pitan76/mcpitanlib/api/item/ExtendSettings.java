package net.pitan76.mcpitanlib.api.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

/**
 * Use {@link CompatibleItemSettings} instead.
 */
public class ExtendSettings extends Item.Properties {

    // ～1.19.2
    @Deprecated
    public ExtendSettings addGroup(CreativeModeTab itemGroup) {
        return this;
    }

    // 1.19.3～
    // identifier: Item ID
    @Deprecated
    public ExtendSettings addGroup(CreativeModeTab itemGroup, Identifier identifier) {
//        this.arch$tab(itemGroup);
        CreativeTabManager.addItem(itemGroup, identifier);
        return this;
    }

    @Deprecated
    public ExtendSettings addGroup(Supplier<CreativeModeTab> itemGroup, Identifier identifier) {
        CreativeTabManager.addItem(itemGroup, identifier);
        return this;
    }
}
