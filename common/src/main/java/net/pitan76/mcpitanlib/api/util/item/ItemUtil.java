package net.pitan76.mcpitanlib.api.util.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class ItemUtil {

    /**
     * Check if the item is equal.
     * @param item Item to compare.
     * @param item2 Item to compare.
     * @return If the item is equal.
     */
    public static boolean isEqual(Item item, Item item2) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.isEqual(item, item2);
    }

    /**
     * Get item from CompatIdentifier.
     * @param id CompatIdentifier of the item.
     * @return Item of the CompatIdentifier.
     */
    public static Item fromId(CompatIdentifier id) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.fromId(id);
    }

    public static Item fromId(String id) {
        return fromId(CompatIdentifier.of(id));
    }

    public static Item fromId(String namespace, String path) {
        return fromId(CompatIdentifier.of(namespace, path));
    }

    /**
     * Get CompatIdentifier from Item.
     * @param item Item to get CompatIdentifier.
     * @return CompatIdentifier of the Item.
     */
    public static CompatIdentifier toId(Item item) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.toCompatID(item);
    }

    public static String toIdAsString(Item item) {
        return toId(item).toString();
    }

    /**
     * Check if the item exist.
     * @param id CompatIdentifier of the item.
     * @return If the item exist.
     */
    public static boolean isExist(CompatIdentifier id) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.isExist(id);
    }

    public static boolean isExist(String id) {
        return isExist(CompatIdentifier.of(id));
    }

    public static boolean isExist(String namespace, String path) {
        return isExist(CompatIdentifier.of(namespace, path));
    }

    public static boolean isMinecraftItem(Item item) {
        return CompatIdentifier.isMinecraftNamespace(toId(item));
    }

    /**
     * Create a new BlockItem.
     * @param block Block to create BlockItem.
     * @param settings CompatItemSettings of the BlockItem.
     * @return The new BlockItem.
     */
    public static BlockItem create(Block block, CompatibleItemSettings settings) {
        if (!settings.changedTranslationKey)
            settings.useBlockPrefixedTranslationKey();

        return net.pitan76.mcpitanlib.api.util.ItemUtil.ofBlock(block, settings);
    }

    /**
     * Create a new Item.
     * @param settings CompatItemSettings of the Item.
     * @return The new Item.
     */
    public static Item create(CompatibleItemSettings settings) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.of(settings);
    }

    /**
     * Get all items.
     * @return List of all items.
     */
    public static List<Item> getItems() {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.getAllItems();
    }

    /**
     * Get items in the tag.
     * @param tagKey TagKey of the items.
     * @return List of items in the tag.
     */
    public static List<Item> getInTag(TagKey<Item> tagKey) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.getItems(tagKey);
    }

    /**
     * Get items in the tag.
     * @param id CompatIdentifier of the tag.
     * @return List of items in the tag.
     */
    public static List<Item> getInTag(CompatIdentifier id) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.getItems(id);
    }

    public static List<Item> getInTag(String id) {
        return getInTag(CompatIdentifier.of(id));
    }

    public static List<Item> getInTag(String namespace, String path) {
        return getInTag(CompatIdentifier.of(namespace, path));
    }

    /**
     * Check if the item is in the tag.
     * @param item Item to check.
     * @param tagKey TagKey of the tag.
     * @return If the item is in the tag.
     */
    public static boolean isInTag(Item item, TagKey<Item> tagKey) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.isIn(item, tagKey);
    }

    /**
     * Check if the item is in the tag.
     * @param item Item to check.
     * @param id CompatIdentifier of the tag.
     * @return If the item is in the tag.
     */
    public static boolean isInTag(Item item, CompatIdentifier id) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.isItemInTag(item, id);
    }

    public static boolean isInTag(Item item, String id) {
        return isInTag(item, CompatIdentifier.of(id));
    }

    public static boolean isInTag(Item item, String namespace, String path) {
        return isInTag(item, CompatIdentifier.of(namespace, path));
    }

    /**
     * Get raw id of the item.
     * @param item Item to get raw id.
     * @return Raw id of the item.
     */
    public static int toRawId(Item item) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.getRawId(item);
    }

    /**
     * Get item from raw id.
     * @param rawId Raw id of the item.
     * @return Item from raw id.
     */
    public static Item fromRawId(int rawId) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.fromIndex(rawId);
    }

    /**
     * Get items by namespace.
     * @param namespace Namespace of the items.
     * @return List of items by namespace.
     */
    public static List<Item> getItemsByNamespace(String namespace) {
        List<Item> items = new ArrayList<>();

        for (Item item : getItems()) {
            if (toId(item).getNamespace().equals(namespace))
                items.add(item);
        }

        return items;
    }

    /**
     * Get number of all items.
     * @return Number of all items.
     */
    public static int getNumberOfItems() {
        return getItems().size();
    }

    /**
     * Get item from stack.
     * @param stack ItemStack to get item.
     * @return Item of the stack.
     */
    public static Item of(ItemStack stack) {
        return stack.getItem();
    }

    /**
     * Check if the item is of the item.
     * @param stack ItemStack to check.
     * @param item Item to check.
     * @return If the item is of the item.
     */
    public static boolean isOf(ItemStack stack, Item item) {
        return net.pitan76.mcpitanlib.api.util.ItemUtil.isOf(stack, item);
    }

    /**
     * Get name of the item.
     * @param item Item
     * @return Name of the item.
     */
    public static String getNameAsString(Item item) {
        return item.getName().getString();
    }

    /**
     * Get name of the item.
     * @param item Item
     * @return Name of the item.
     */
    public static TextComponent getName(Item item) {
        return new TextComponent(item.getName());
    }

    /**
     * Get translation key of the item.
     * @param item Item
     * @return Translation key of the item.
     */
    public static String getTranslationKey(Item item) {
        return item.getTranslationKey();
    }
}
