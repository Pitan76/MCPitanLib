package net.pitan76.mcpitanlib.api.item.fabric;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class CreativeTabBuilderImpl {

    public static ItemGroup build(Identifier identifier, Text displayName, Supplier<ItemStack> iconSupplier, boolean noRenderedName, boolean noScrollbar, boolean special, String texture) {
        FabricItemGroupBuilder builder = FabricItemGroupBuilder.create(identifier);

        if (iconSupplier != null) builder.icon(iconSupplier);

        ItemGroup itemGroup = builder.build();

        return apply(itemGroup, displayName, noRenderedName, noScrollbar, texture);
    }

    // 1.19.2のItemGroupはビルダーを持たないため、生成後に設定する
    static ItemGroup apply(ItemGroup itemGroup, Text displayName, boolean noRenderedName, boolean noScrollbar, String texture) {
        if (displayName != null) itemGroup.setName(displayName.getString());
        if (noRenderedName) itemGroup.hideName();
        if (noScrollbar) itemGroup.setNoScrollbar();
        if (texture != null) itemGroup.setTexture(texture);

        return itemGroup;
    }
}
