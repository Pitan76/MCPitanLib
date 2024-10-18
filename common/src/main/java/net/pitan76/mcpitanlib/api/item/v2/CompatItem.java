package net.pitan76.mcpitanlib.api.item.v2;

import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

public class CompatItem extends ExtendItem {

    public CompatibleItemSettings settings;

    public CompatItem(CompatibleItemSettings settings) {
        super(settings);
        this.settings = settings;
    }

    public CompatibleItemSettings getCompatSettings() {
        return settings;
    }

    public ItemWrapper getWrapper() {
        return ItemWrapper.of(this);
    }
}
