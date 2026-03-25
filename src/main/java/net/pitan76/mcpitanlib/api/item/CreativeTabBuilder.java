package net.pitan76.mcpitanlib.api.item;

import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.IdentifierUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry1_20;
import net.pitan76.mcpitanlib.midohra.item.ItemGroupWrapper;

import java.util.function.Supplier;

public class CreativeTabBuilder {
    private final Identifier identifier;
    private Component displayName = null;
    private Supplier<ItemStack> iconSupplier = null;
    private boolean noRenderedName = false;
    private boolean noScrollbar = false;
    private boolean special = false;
    private String texture;

    @Deprecated
    // Recommend: create(identifier)
    public CreativeTabBuilder(Identifier identifier) {
        this.identifier = identifier;
    }

    public static CreativeTabBuilder create(Identifier identifier) {
        return new CreativeTabBuilder(identifier);
    }

    public static CreativeTabBuilder create(CompatIdentifier identifier) {
        return create(identifier.toMinecraft());
    }

    public CreativeTabBuilder setDisplayName(Component text) {
        this.displayName = text;
        return this;
    }

    /**
     * Set icon
     * @param iconSupplier Icon supplier
     * @return CreativeTabBuilder
     */
    public CreativeTabBuilder setIcon(Supplier<ItemStack> iconSupplier) {
        this.iconSupplier = iconSupplier;
        return this;
    }

    /**
     * Set icon (Already registered item only)
     * @param item Item
     * @return CreativeTabBuilder
     */
    public CreativeTabBuilder setIcon(Item item) {
        return setIcon(() -> new ItemStack(item));
    }

    public void noRenderedName() {
        this.noRenderedName = true;
    }

    public void noScrollbar() {
        this.noScrollbar = true;
    }

    public void special() {
        this.special = true;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    /**
     * Build ItemGroup (If loader is forge, not recommended)
     * @return ItemGroup
     */
    public CreativeModeTab build() {
        return CreativeTabRegistry.create((builder -> {
            if (displayName != null) builder.title(displayName);
            else builder.title(TextUtil.translatable("itemGroup." + identifier.getNamespace() + "." + identifier.getPath()));

            if (iconSupplier != null) builder.icon(iconSupplier);
            if (noRenderedName) builder.hideTitle();
            if (noScrollbar) builder.noScrollBar();
            if (special) builder.alignedRight();
            if (texture != null) builder.backgroundTexture(IdentifierUtil.id(texture));
        }));
    }

    @SuppressWarnings("deprecation")
    public ItemGroupWrapper getBuiltWrapper() {
        if (MCPLRegistry1_20.REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.containsKey(identifier))
            return ItemGroupWrapper.of(MCPLRegistry1_20.REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.get(identifier).getOrNull());

        return ItemGroupWrapper.of(build());
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public CompatIdentifier getCompatIdentifier() {
        return CompatIdentifier.fromMinecraft(identifier);
    }
}
