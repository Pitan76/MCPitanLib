package net.pitan76.mcpitanlib.api.item;

import me.shedaniel.architectury.registry.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

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
        ItemGroup itemGroup = CreativeTabs.create(identifier, iconSupplier);
        if (displayName != null) itemGroup.setName(displayName.getString());
        if (noRenderedName) itemGroup.setName("");
        if (noScrollbar) itemGroup.setNoScrollbar();
        if (special) itemGroup.isSpecial();
        if (texture != null) itemGroup.setTexture(texture);
        itemGroupMap.put(identifier, itemGroup);
        return itemGroup;
    }

    public Identifier getIdentifier() {
        return identifier;
    }
}
