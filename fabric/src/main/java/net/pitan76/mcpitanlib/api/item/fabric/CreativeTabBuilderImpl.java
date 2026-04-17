package net.pitan76.mcpitanlib.api.item.fabric;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.pitan76.mcpitanlib.api.util.IdentifierUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;

import java.util.function.Supplier;

public class CreativeTabBuilderImpl {

    public static CreativeModeTab build(Identifier identifier, Component displayName, Supplier<ItemStack> iconSupplier, boolean noRenderedName, boolean noScrollbar, boolean special, String texture) {
        CreativeModeTab.Builder builder = FabricCreativeModeTab.builder();

        if (displayName != null) builder.title(displayName);
        else builder.title(TextUtil.translatable("itemGroup." + identifier.getNamespace() + "." + identifier.getPath()));

        if (iconSupplier != null) builder.icon(iconSupplier);
        if (noRenderedName) builder.hideTitle();
        if (noScrollbar) builder.noScrollBar();
        if (special) builder.alignedRight();
        if (texture != null) builder.backgroundTexture(IdentifierUtil.id(texture));

        return builder.build();
    }
}
