package net.pitan76.mcpitanlib.api.item;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.item.SupplierItemWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CreativeTabBuilder {
    @Deprecated
    public static final Map<Identifier, ItemGroup> itemGroupMap = new HashMap<>();

    @Deprecated
    public static final Map<Identifier, CreativeTabBuilder> itemGroupBuilderMap = new HashMap<>();

    private final Identifier identifier;
    private Text displayName = null;
    private Supplier<ItemStack> iconSupplier = null;
    private boolean noRenderedName = false;
    private boolean noScrollbar = false;
    private boolean special = false;
    private String texture;

    @Deprecated
    // Recommend: create(identifier)
    public CreativeTabBuilder(Identifier identifier) {
        this.identifier = identifier;
        itemGroupBuilderMap.put(identifier, this);
    }

    public static CreativeTabBuilder create(Identifier identifier) {
        return new CreativeTabBuilder(identifier);
    }

    public static CreativeTabBuilder create(CompatIdentifier identifier) {
        return create(identifier.toMinecraft());
    }

    public CreativeTabBuilder setDisplayName(Text text) {
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
    public ItemGroup build() {
        if (itemGroupMap.containsKey(identifier)) return itemGroupMap.get(identifier);
        ItemGroup itemGroup = build(identifier, displayName, iconSupplier, noRenderedName, noScrollbar, special, texture);
        itemGroupMap.put(identifier, itemGroup);
        return itemGroup;
    }

    /**
     * 1.16.5のItemGroupはビルダーを持たないため、ローダーごとに生成方法が異なる。
     */
    @ExpectPlatform
    public static ItemGroup build(Identifier identifier, Text displayName, Supplier<ItemStack> iconSupplier, boolean noRenderedName, boolean noScrollbar, boolean special, String texture) {
        throw new AssertionError();
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public CompatIdentifier getCompatIdentifier() {
        return CompatIdentifier.fromMinecraft(identifier);
    }

    /**
     * Set icon
     * @param iconSupplier Icon supplier
     * @return CreativeTabBuilder
     */
    public CreativeTabBuilder setIconM(Supplier<net.pitan76.mcpitanlib.midohra.item.ItemStack> iconSupplier) {
        this.iconSupplier = () -> iconSupplier.get().toMinecraft();
        return this;
    }

    /**
     * Set icon (Already registered item only)
     * @param item Item
     * @return CreativeTabBuilder
     */
    public CreativeTabBuilder setIcon(ItemWrapper item) {
        return setIcon(() -> item.createStack().toMinecraft());
    }

    /**
     * Set icon (Already registered item only)
     * @param item Item
     * @return CreativeTabBuilder
     */
    public CreativeTabBuilder setIcon(SupplierItemWrapper item) {
        return setIcon(() -> item.createStack().toMinecraft());
    }

    public CreativeTabBuilder setDisplayName(TextComponent text) {
        return setDisplayName(text.getText());
    }
}
