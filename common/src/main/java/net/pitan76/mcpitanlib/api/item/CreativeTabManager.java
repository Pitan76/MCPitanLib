package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.ItemUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;
import net.pitan76.mcpitanlib.core.registry.CreativeTabEventRegistry;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry1_20;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.concurrent.CopyOnWriteArrayList;

public class CreativeTabManager {
    private static List<BookingItem> bookingItems = new CopyOnWriteArrayList<>();
    private static List<BookingStack> bookingStacks = new CopyOnWriteArrayList<>();

    // グループ予約済みアイテム
    public static class BookingItem {
        public Identifier itemGroupId;
        @Deprecated
        public ItemGroup itemGroup;
        @Deprecated
        public Supplier<ItemGroup> itemGroupSupplier;
        public Identifier identifier;

        public BookingItem(Identifier itemGroupId, Identifier identifier) {
            this.itemGroupId = itemGroupId;
            this.identifier = identifier;
        }

        @Deprecated
        private BookingItem(ItemGroup itemGroup, Identifier identifier) {
            this.itemGroup = itemGroup;
            this.identifier = identifier;
            this.itemGroupId = ItemGroupUtil.toID(itemGroup);
        }

        @Deprecated
        private BookingItem(Supplier<ItemGroup> itemGroup, Identifier identifier) {
            this.itemGroupSupplier = itemGroup;
            this.identifier = identifier;
        }

        public Identifier getItemGroupId() {
            if (itemGroupId != null) return itemGroupId;
            try {
                if (itemGroupSupplier != null) return ItemGroupUtil.toID(itemGroupSupplier.get());
                if (itemGroup != null) return ItemGroupUtil.toID(itemGroup);
            } catch (Exception ignored) {}
            return null;
        }
    }

    // グループ予約済みアイテムスタック
    public static class BookingStack {
        public Identifier itemGroupId;
        @Deprecated
        public ItemGroup itemGroup;
        @Deprecated
        public Supplier<ItemGroup> itemGroupSupplier;
        public ItemStack stack;

        public BookingStack(Identifier itemGroupId, ItemStack stack) {
            this.itemGroupId = itemGroupId;
            this.stack = stack;
        }

        @Deprecated
        private BookingStack(ItemGroup itemGroup, ItemStack stack) {
            this.itemGroup = itemGroup;
            this.stack = stack;
            this.itemGroupId = ItemGroupUtil.toID(itemGroup);
        }

        @Deprecated
        private BookingStack(Supplier<ItemGroup> itemGroup, ItemStack stack) {
            this.itemGroupSupplier = itemGroup;
            this.stack = stack;
        }

        public Identifier getItemGroupId() {
            if (itemGroupId != null) return itemGroupId;
            try {
                if (itemGroupSupplier != null) return ItemGroupUtil.toID(itemGroupSupplier.get());
                if (itemGroup != null) return ItemGroupUtil.toID(itemGroup);
            } catch (Exception ignored) {}
            return null;
        }
    }

    public static void allRegister() {
        if (!bookingItems.isEmpty()) {
            for (BookingItem bookingItem : bookingItems) {
                RegistryKey<ItemGroup> key = resolveKey(bookingItem.getItemGroupId());
                if (key != null) {
                    CreativeTabEventRegistry.addStack(key, () -> new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
                } else {
                    CreativeTabEventRegistry.addStackLazy(() -> resolveKey(bookingItem.getItemGroupId()),
                            () -> new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
                }
            }
            bookingItems = new CopyOnWriteArrayList<>();
        }

        if (!bookingStacks.isEmpty()) {
            for (BookingStack bookingStack : bookingStacks) {
                RegistryKey<ItemGroup> key = resolveKey(bookingStack.getItemGroupId());
                if (key != null) {
                    CreativeTabEventRegistry.addStack(key, () -> bookingStack.stack);
                } else {
                    CreativeTabEventRegistry.addStackLazy(() -> resolveKey(bookingStack.getItemGroupId()),
                            () -> bookingStack.stack);
                }
            }
            bookingStacks = new CopyOnWriteArrayList<>();
        }
    }

    /**
     * アイテムグループのIDからRegistryKeyを解決する。
     */
    private static RegistryKey<ItemGroup> resolveKey(Identifier id) {
        if (id == null) return null;
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, id);
    }

    public static void register(Identifier identifier) {
        if (bookingItems.isEmpty()) return;
        for (BookingItem bookingItem : bookingItems) {
            if (!bookingItem.identifier.toString().equals(identifier.toString())) continue;

            RegistryKey<ItemGroup> key = resolveKey(bookingItem.getItemGroupId());
            // この時点で解決できない場合は予約のまま残し、allRegister()で遅延登録する
            if (key == null) break;

            CreativeTabEventRegistry.addStack(key, () -> new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
            bookingItems.remove(bookingItem);
            break;
        }
    }

    public static void addItem(Identifier itemGroupId, Identifier identifier) {
        registerLazy(new BookingItem(itemGroupId, identifier));
    }

    public static void addStack(Identifier itemGroupId, ItemStack stack) {
        registerLazy(new BookingStack(itemGroupId, stack));
    }

    @Deprecated
    public static void addItem(ItemGroup itemGroup, Identifier identifier) {
        registerLazy(new BookingItem(itemGroup, identifier));
    }

    @Deprecated
    public static void addStack(ItemGroup itemGroup, ItemStack stack) {
        registerLazy(new BookingStack(itemGroup, stack));
    }

    public static void addItem(Supplier<ItemGroup> itemGroup, Identifier identifier) {
        registerLazy(new BookingItem(itemGroup, identifier));
    }

    public static void addStack(Supplier<ItemGroup> itemGroup, ItemStack stack) {
        registerLazy(new BookingStack(itemGroup, stack));
    }

    /**
     * 予約せずその場で遅延登録する。
     */
    private static void registerLazy(BookingItem bookingItem) {
        RegistryKey<ItemGroup> key = resolveKey(bookingItem.getItemGroupId());
        if (key != null) {
            CreativeTabEventRegistry.addStack(key, () -> new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
        } else {
            CreativeTabEventRegistry.addStackLazy(() -> resolveKey(bookingItem.getItemGroupId()),
                    () -> new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
        }
    }

    private static void registerLazy(BookingStack bookingStack) {
        RegistryKey<ItemGroup> key = resolveKey(bookingStack.getItemGroupId());
        if (key != null) {
            CreativeTabEventRegistry.addStack(key, () -> bookingStack.stack);
        } else {
            CreativeTabEventRegistry.addStackLazy(() -> resolveKey(bookingStack.getItemGroupId()),
                    () -> bookingStack.stack);
        }
    }
}
