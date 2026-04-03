package net.pitan76.mcpitanlib.api.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.util.ItemUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry1_20;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CreativeTabManager {
    private static List<BookingItem> bookingItems = new ArrayList<>();
    private static List<BookingStack> bookingStacks = new ArrayList<>();

    // グループ予約済みアイテム
    public static class BookingItem {
        @Deprecated
        public CreativeModeTab itemGroup;

        public Supplier<CreativeModeTab> itemGroupSupplier;
        public Identifier identifier;

        @Deprecated
        private BookingItem(CreativeModeTab itemGroup, Identifier identifier) {
            this.itemGroup = itemGroup;
            this.identifier = identifier;
        }

        private BookingItem(Supplier<CreativeModeTab> itemGroup, Identifier identifier) {
            this.itemGroupSupplier = itemGroup;
            this.identifier = identifier;
        }

        public CreativeModeTab getItemGroup() {
            if (itemGroupSupplier != null)
                return itemGroupSupplier.get();
            return itemGroup;
        }
    }

    // グループ予約済みアイテムスタック
    public static class BookingStack {
        @Deprecated
        public CreativeModeTab itemGroup;

        public Supplier<CreativeModeTab> itemGroupSupplier;
        public ItemStack stack;

        @Deprecated
        private BookingStack(CreativeModeTab itemGroup, ItemStack stack) {
            this.itemGroup = itemGroup;
            this.stack = stack;
        }

        private BookingStack(Supplier<CreativeModeTab> itemGroup, ItemStack stack) {
            this.itemGroupSupplier = itemGroup;
            this.stack = stack;
        }

        public CreativeModeTab getItemGroup() {
            if (itemGroupSupplier != null)
                return itemGroupSupplier.get();
            return itemGroup;
        }
    }

    public static void allRegister() {
        if (!MCPLRegistry1_20.ITEM_GROUP_ITEM_ID_CACHE.isEmpty()) {
            for (Map.Entry<ResourceKey<CreativeModeTab>, Identifier> entry : MCPLRegistry1_20.ITEM_GROUP_ITEM_ID_CACHE.entrySet()) {
                CreativeModeTabEvents.modifyOutputEvent(entry.getKey()).register(entries ->
                        entries.accept(new ItemStack(ItemUtil.fromId(entry.getValue()))));
            }
        }

        if (!bookingItems.isEmpty()) {
            for (BookingItem bookingItem : bookingItems) {
//                CreativeTabRegistry.appendBuiltin(bookingItem.getItemGroup(), ItemUtil.fromId(bookingItem.identifier));
                ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ItemGroupUtil.toID(bookingItem.getItemGroup()));
                CreativeModeTabEvents.modifyOutputEvent(key).register(entries -> {
                    entries.accept(new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
                });
            }
            bookingItems = new ArrayList<>();
        }

        if (!bookingStacks.isEmpty()) {
            for (BookingStack bookingStack : bookingStacks) {
//                CreativeTabRegistry.appendBuiltinStack(bookingStack.getItemGroup(), bookingStack.stack);
                ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ItemGroupUtil.toID(bookingStack.getItemGroup()));
                CreativeModeTabEvents.modifyOutputEvent(key).register(entries -> {
                    entries.accept(bookingStack.stack);
                });
            }
            bookingStacks = new ArrayList<>();
        }
    }

    public static void register(Identifier identifier) {
        if (bookingItems.isEmpty()) return;
        for (BookingItem bookingItem : bookingItems) {
            if (!bookingItem.identifier.toString().equals(identifier.toString())) continue;
//            CreativeTabRegistry.appendBuiltin(bookingItem.getItemGroup(), ItemUtil.fromId(bookingItem.identifier));
            ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ItemGroupUtil.toID(bookingItem.getItemGroup()));
            CreativeModeTabEvents.modifyOutputEvent(key).register(entries -> {
                entries.accept(new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
            });
            bookingItems.remove(bookingItem);
            break;
        }
    }

    @Deprecated
    public static void addItem(CreativeModeTab itemGroup, Identifier identifier) {
        bookingItems.add(new BookingItem(itemGroup, identifier));
    }

    @Deprecated
    public static void addStack(CreativeModeTab itemGroup, ItemStack stack) {
        bookingStacks.add(new BookingStack(itemGroup, stack));
    }

    public static void addItem(Supplier<CreativeModeTab> itemGroup, Identifier identifier) {
        bookingItems.add(new BookingItem(itemGroup, identifier));
    }

    public static void addStack(Supplier<CreativeModeTab> itemGroup, ItemStack stack) {
        bookingStacks.add(new BookingStack(itemGroup, stack));
    }
}
