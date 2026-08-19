package net.pitan76.mcpitanlib.api.item.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.IdentifierUtil;
import net.pitan76.mcpitanlib.api.util.ItemUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry1_20;

import java.util.function.Supplier;

public class CreativeTabBuilderImpl {

    public static ItemGroup build(Identifier identifier, Text displayName, Supplier<ItemStack> iconSupplier, boolean noRenderedName, boolean noScrollbar, boolean special, String texture) {
        ItemGroup.Builder builder = FabricItemGroup.builder();

        if (displayName != null) builder.displayName(displayName);
        else builder.displayName(TextUtil.translatable("itemGroup." + identifier.getNamespace() + "." + identifier.getPath()));

        if (iconSupplier != null) builder.icon(iconSupplier);
        if (noRenderedName) builder.noRenderedName();
        if (noScrollbar) builder.noScrollbar();
        if (special) builder.special();
        if (texture != null) builder.texture(IdentifierUtil.id(texture));

        builder.entries((displayContext, entries) -> {
            RegistryKey<ItemGroup> key = RegistryKey.of(RegistryKeys.ITEM_GROUP, identifier);
            if (MCPLRegistry1_20.ITEM_GROUP_ITEM_ID_CACHE.containsKey(key)) {
                for (Identifier itemId : MCPLRegistry1_20.ITEM_GROUP_ITEM_ID_CACHE.get(key)) {
                    entries.add(ItemUtil.fromId(itemId));
                }
            }
        });

        return builder.build();
    }
}
