package net.pitan76.mcpitanlib.api.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import net.pitan76.mcpitanlib.core.mc261.CreativeModeTabEventRegistry;
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

        // 未登録のグループを参照した場合の例外を握りつぶす
        public CreativeModeTab getItemGroupOrNull() {
            try {
                return getItemGroup();
            } catch (Exception e) {
                return null;
            }
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

        // 未登録のグループを参照した場合の例外を握りつぶす
        public CreativeModeTab getItemGroupOrNull() {
            try {
                return getItemGroup();
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static void allRegister() {
        if (!bookingItems.isEmpty()) {
            for (BookingItem bookingItem : bookingItems) {
                CreativeModeTabEventRegistry.addStackLazy(() -> resolveKey(bookingItem.getItemGroupOrNull()), () -> new ItemStack(ItemUtil.fromId(CompatIdentifier.fromMinecraft(bookingItem.identifier))));
            }
            bookingItems = new ArrayList<>();
        }

        if (!bookingStacks.isEmpty()) {
            for (BookingStack bookingStack : bookingStacks) {
                CreativeModeTabEventRegistry.addStackLazy(() -> resolveKey(bookingStack.getItemGroupOrNull()), () -> bookingStack.stack);
            }
            bookingStacks = new ArrayList<>();
        }
    }

    /**
     * アイテムグループからResourceKeyを解決する。
     * 未登録などで解決できない場合はnullを返す。
     */
    private static ResourceKey<CreativeModeTab> resolveKey(CreativeModeTab itemGroup) {
        if (itemGroup == null) return null;

        Identifier id;
        try {
            id = ItemGroupUtil.toID(itemGroup);
        } catch (Exception e) {
            return null;
        }
        if (id == null) return null;

        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
    }

    public static void register(Identifier identifier) {
        if (bookingItems.isEmpty()) return;
        for (BookingItem bookingItem : bookingItems) {
            if (!bookingItem.identifier.toString().equals(identifier.toString())) continue;

            // この時点で解決できない場合は予約のまま残し、allRegister()で遅延登録する
            if (resolveKey(bookingItem.getItemGroupOrNull()) == null) break;

            CreativeModeTabEventRegistry.addStackLazy(() -> resolveKey(bookingItem.getItemGroupOrNull()), () -> new ItemStack(ItemUtil.fromId(CompatIdentifier.fromMinecraft(bookingItem.identifier))));
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
