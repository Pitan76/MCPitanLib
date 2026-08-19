package net.pitan76.mcpitanlib.api.item.forge;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class CreativeTabBuilderImpl {

    public static ItemGroup build(Identifier identifier, Text displayName, Supplier<ItemStack> iconSupplier, boolean noRenderedName, boolean noScrollbar, boolean special, String texture) {
        ItemGroup itemGroup = new ItemGroup(identifier.getNamespace() + "." + identifier.getPath()) {
            @Override
            public ItemStack createIcon() {
                return iconSupplier == null ? ItemStack.EMPTY : iconSupplier.get();
            }
        };

        // 1.19.2のItemGroupはビルダーを持たないため、生成後に設定する
        if (displayName != null) itemGroup.setName(displayName.getString());
        if (noRenderedName) itemGroup.hideName();
        if (noScrollbar) itemGroup.setNoScrollbar();
        if (texture != null) itemGroup.setTexture(texture);

        return itemGroup;
    }
}
