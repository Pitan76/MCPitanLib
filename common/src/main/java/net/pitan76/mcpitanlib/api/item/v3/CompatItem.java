package net.pitan76.mcpitanlib.api.item.v3;

import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatItem extends net.pitan76.mcpitanlib.api.item.v2.CompatItem {
    public CompatItem(CompatibleItemSettings settings) {
        super(settings);
    }

    public CompatItem(ItemSettingsBuilder builder, CompatIdentifier id) {
        super(builder.build(id));
    }

    public CompatItem(ItemSettingsBuilder builder) {
        super(builder.build());
    }
}
