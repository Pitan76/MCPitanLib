package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.item.ICompatItem;
import net.pitan76.mcpitanlib.api.item.v2.CompatItem;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.easybuilder.built.BuiltItem;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ItemWrapper {
    private final net.minecraft.item.Item item;

    protected ItemWrapper() {
        this.item = null;
    }

    protected ItemWrapper(net.minecraft.item.Item item) {
        this.item = item;
    }

    public static ItemWrapper of(net.minecraft.item.Item item) {
        return new ItemWrapper(item);
    }

    public static ItemWrapper of() {
        return new ItemWrapper();
    }

    public static ItemWrapper of(CompatIdentifier id) {
        if (ItemUtil.isExist(id))
            return of(ItemUtil.fromId(id));

        return of();
    }

    public static ItemWrapper of(CompatIdentifier id, CompatIdentifier... ids) {
        if (of(id).isPresent()) return of(id);

        for (CompatIdentifier id1 : ids) {
            if (of(id1).isPresent())
                return of(id1);
        }

        return of();
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean isAir() {
        return isEmpty() || get() == net.minecraft.item.Items.AIR;
    }

    @Nullable
    public net.minecraft.item.Item get() {
        return item;
    }

    public net.minecraft.item.Item gerOrDefault(net.minecraft.item.Item defaultItem) {
        return getOrDefault(defaultItem);
    }

    public net.minecraft.item.Item getOrDefault(net.minecraft.item.Item defaultItem) {
        return isEmpty() ? defaultItem : get();
    }

    public CompatIdentifier getId() {
        if (isEmpty())
            return CompatIdentifier.empty();

        return ItemUtil.toId(get());
    }

    public String getName() {
        if (isEmpty()) return "";
        return ItemUtil.getNameAsString(get());
    }

    public String getTranslationKey() {
        if (isEmpty()) return "";
        return ItemUtil.getTranslationKey(get());
    }

    public ItemStack createStack(int count) {
        if (isEmpty()) return ItemStack.EMPTY;
        return ItemStack.of(ItemStackUtil.create(get(), count));
    }

    public ItemStack createStack() {
        return createStack(1);
    }

    public boolean isInTag(CompatIdentifier id) {
        return ItemUtil.isInTag(get(), id);
    }

    public boolean isBlock() {
        return !isEmpty() && get() instanceof net.minecraft.item.BlockItem;
    }

    public BlockWrapper asBlock() {
        if (!isBlock())
            return BlockWrapper.of();

        return BlockWrapper.of(((net.minecraft.item.BlockItem) get()).getBlock());
    }

    public boolean rawEquals(ItemWrapper item) {
        return get() == item.get();
    }

    @Override
    public int hashCode() {
        return get() != null ? get().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ItemWrapper)) return false;
        ItemWrapper item = (ItemWrapper) obj;
        return rawEquals(item);
    }

    public static ItemWrapper of(BlockWrapper block) {
        if (block.isEmpty())
            return of();

        return of(block.asItem().get());
    }

    public static ItemWrapper of(String id) {
        return of(CompatIdentifier.of(id));
    }

    public static ItemWrapper of(String namespace, String path) {
        return of(CompatIdentifier.of(namespace, path));
    }

    public static ItemWrapper of(CompatItem item) {
        return of((net.minecraft.item.Item) item);
    }

    public Optional<CompatItem> toCompatItem() {
        if (get() instanceof CompatItem) {
            return Optional.of((CompatItem) get());
        }
        return Optional.empty();
    }

    public Optional<BuiltItem> toBuiltItem() {
        if (get() instanceof BuiltItem) {
            return Optional.of((BuiltItem) get());
        }
        return Optional.empty();
    }

    /**
     * instanceof check for the item of this wrapper.
     * @param clazz the class of the item to check
     * @return true if the item of this wrapper is an instance of the given class, false otherwise
     */
    public boolean instanceOf(Class<?> clazz) {
        if (isEmpty()) return false;

        return clazz.isInstance(get());
    }

    /**
     * instanceof check for the item of this wrapper.
     * @param wrapper the item to check
     * @return true if the item of this wrapper is an instance of the given item, false otherwise
     */
    public boolean instanceOf(ItemWrapper wrapper) {
        if (isEmpty()) return false;

        Item item = wrapper.get();
        if (item == null) return false;

        Class<?> clazz = item.getClass();
        return clazz.isInstance(get());
    }

    public <T extends CompatItem> T getCompatItem(Class<T> clazz) {
        if (isEmpty()) return null;
        if (get() instanceof CompatItem) {
            CompatItem compatItem = (CompatItem) get();
            if (clazz.isInstance(compatItem))
                return clazz.cast(compatItem);
        }
        return null;
    }

    public <T extends CompatItem> Optional<T> toCompatItem(Class<T> clazz) {
        return Optional.ofNullable(getCompatItem(clazz));
    }

    public <T extends ICompatItem> T getICompatItem(Class<T> clazz) {
        if (isEmpty()) return null;
        if (get() instanceof ICompatItem) {
            ICompatItem compatItem = (ICompatItem) get();
            if (clazz.isInstance(compatItem))
                return clazz.cast(compatItem);
        }
        return null;
    }

    public <T extends ICompatItem> Optional<T> toICompatItem(Class<T> clazz) {
        return Optional.ofNullable(getICompatItem(clazz));
    }
}
