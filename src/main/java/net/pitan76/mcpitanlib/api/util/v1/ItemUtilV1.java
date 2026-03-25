package net.pitan76.mcpitanlib.api.util.v1;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.block.BlockItemByExtendBlock1215;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.tag.TagKey;

import java.util.ArrayList;
import java.util.List;

public class ItemUtilV1 {
    public static Item item(Identifier id) {
        return BuiltInRegistries.ITEM.getValue(id);
    }

    public static boolean isEqual(Item item, Item item2) {
        return item == item2;
    }

    public static boolean isOf(ItemStack stack, Item item) {
        return stack.is(item);
    }

    public static boolean isIn(ItemStack stack, TagKey<Item> tagKey) {
        return isIn(stack.getItem(), tagKey);
    }

    public static boolean isIn(Item item, TagKey<Item> tagKey) {
        if (item.builtInRegistryHolder().is(tagKey.getTagKey())) return true;
        return tagKey.isOf(item);
    }

    public static boolean isExist(Identifier identifier) {
        return BuiltInRegistries.ITEM.containsKey(identifier);
    }
    public static Identifier toID(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public static Item fromId(Identifier identifier) {
        return BuiltInRegistries.ITEM.getValue(identifier);
    }

    @Deprecated
    public static BlockItem ofBlock(Block block, Item.Properties settings) {
        if (block instanceof ExtendBlock) {
            return new BlockItemByExtendBlock1215((ExtendBlock) block, settings);
        }

        if (block instanceof ExtendBlockProvider) {
            return new BlockItemByExtendBlock1215((ExtendBlockProvider) block, settings);
        }

        return new BlockItem(block, settings);
    }

    public static BlockItem ofBlock(Block block, CompatibleItemSettings settings) {
        return ofBlock(block, settings.build());
    }

    @Deprecated
    public static Item of(Item.Properties settings) {
        return new Item(settings);
    }

    public static Item of(CompatibleItemSettings settings) {
        return of(settings.build());
    }

    public static List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        for (Item item : BuiltInRegistries.ITEM) {
            items.add(item);
        }
        return items;
    }

    public static int getRawId(Item item) {
        return BuiltInRegistries.ITEM.getId(item);
    }

    public static Item fromIndex(int index) {
        return BuiltInRegistries.ITEM.byId(index);
    }
}
