package net.pitan76.mcpitanlib.api.item.neoforge;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.IdentifierUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;

import java.util.function.Supplier;

public class CreativeTabBuilderImpl {

    public static ItemGroup build(Identifier identifier, Text displayName, Supplier<ItemStack> iconSupplier, boolean noRenderedName, boolean noScrollbar, boolean special, String texture) {
        ItemGroup.Builder builder = ItemGroup.builder();

        if (displayName != null) builder.displayName(displayName);
        else builder.displayName(TextUtil.translatable("itemGroup." + identifier.getNamespace() + "." + identifier.getPath()));

        if (iconSupplier != null) builder.icon(iconSupplier);
        if (noRenderedName) builder.noRenderedName();
        if (noScrollbar) builder.noScrollbar();
        if (special) builder.special();
        if (texture != null) builder.texture(IdentifierUtil.id(texture));

        return builder.build();
    }
}
