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
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

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
        @Deprecated
        public ItemGroup itemGroup;

        public Supplier<ItemGroup> itemGroupSupplier;
        public Identifier identifier;

        @Deprecated
        private BookingItem(ItemGroup itemGroup, Identifier identifier) {
            this.itemGroup = itemGroup;
            this.identifier = identifier;
        }

        private BookingItem(Supplier<ItemGroup> itemGroup, Identifier identifier) {
            this.itemGroupSupplier = itemGroup;
            this.identifier = identifier;
        }

        public ItemGroup getItemGroup() {
            if (itemGroupSupplier != null)
                return itemGroupSupplier.get();
            return itemGroup;
        }

        // 未登録のグループを参照した場合の例外を握りつぶす
        public ItemGroup getItemGroupOrNull() {
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
        public ItemGroup itemGroup;

        public Supplier<ItemGroup> itemGroupSupplier;
        public ItemStack stack;

        @Deprecated
        private BookingStack(ItemGroup itemGroup, ItemStack stack) {
            this.itemGroup = itemGroup;
            this.stack = stack;
        }

        private BookingStack(Supplier<ItemGroup> itemGroup, ItemStack stack) {
            this.itemGroupSupplier = itemGroup;
            this.stack = stack;
        }

        public ItemGroup getItemGroup() {
            if (itemGroupSupplier != null)
                return itemGroupSupplier.get();
            return itemGroup;
        }

        // 未登録のグループを参照した場合の例外を握りつぶす
        public ItemGroup getItemGroupOrNull() {
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
                CreativeTabEventRegistry.addStackLazy(() -> resolveKey(bookingItem.getItemGroupOrNull()),
                        () -> new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
            }
            bookingItems = new ArrayList<>();
        }

        if (!bookingStacks.isEmpty()) {
            for (BookingStack bookingStack : bookingStacks) {
                CreativeTabEventRegistry.addStackLazy(() -> resolveKey(bookingStack.getItemGroupOrNull()),
                        () -> bookingStack.stack);
            }
            bookingStacks = new ArrayList<>();
        }
    }

    /**
     * アイテムグループからRegistryKeyを解決する。
     * 未登録などで解決できない場合はnullを返す。
     */
    private static RegistryKey<ItemGroup> resolveKey(ItemGroup itemGroup) {
        if (itemGroup == null) return null;

        Identifier id = null;
        for (Map.Entry<Identifier, RegistrySupplier<ItemGroup>> entry : MCPLRegistry1_20.REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getOrNull() == itemGroup) {
                id = entry.getKey();
                break;
            }
        }

        if (id == null) {
            try {
                id = ItemGroupUtil.toID(itemGroup);
            } catch (Exception e) {
                return null;
            }
        }
        if (id == null) return null;

        return RegistryKey.of(RegistryKeys.ITEM_GROUP, id);
    }

    public static void register(Identifier identifier) {
        if (bookingItems.isEmpty()) return;
        for (BookingItem bookingItem : bookingItems) {
            if (!bookingItem.identifier.toString().equals(identifier.toString())) continue;

            // この時点で解決できない場合は予約のまま残し、allRegister()で遅延登録する
            if (resolveKey(bookingItem.getItemGroupOrNull()) == null) break;

            CreativeTabEventRegistry.addStackLazy(() -> resolveKey(bookingItem.getItemGroupOrNull()),
                    () -> new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
            bookingItems.remove(bookingItem);
            break;
        }
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
     * <p>
     * NeoForgeではアイテムの生成(=addItemの呼び出し)がallRegister()より後になるため、
     * 予約を貯めてallRegister()でまとめて登録する方式では登録漏れになる。
     */
    private static void registerLazy(BookingItem bookingItem) {
        CreativeTabEventRegistry.addStackLazy(() -> resolveKey(bookingItem.getItemGroupOrNull()),
                () -> new ItemStack(ItemUtil.fromId(bookingItem.identifier)));
    }

    private static void registerLazy(BookingStack bookingStack) {
        CreativeTabEventRegistry.addStackLazy(() -> resolveKey(bookingStack.getItemGroupOrNull()),
                () -> bookingStack.stack);
    }
}
