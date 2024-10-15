package net.pitan76.mcpitanlib.midohra.item;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;

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
        if (of(id).isExist()) return of(id);

        for (CompatIdentifier id1 : ids) {
            if (of(id1).isExist())
                return of(id1);
        }

        return of();
    }

    public boolean isExist() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return item == null;
    }

    public boolean isAir() {
        return isEmpty() || item == net.minecraft.item.Items.AIR;
    }

    public net.minecraft.item.Item get() {
        return item;
    }

    public net.minecraft.item.Item gerOrDefault(net.minecraft.item.Item defaultItem) {
        return isEmpty() ? defaultItem : item;
    }

    public CompatIdentifier getId() {
        if (isEmpty())
            return CompatIdentifier.empty();

        return ItemUtil.toId(item);
    }

    public String getName() {
        if (isEmpty()) return "";
        return ItemUtil.getNameAsString(item);
    }

    public String getTranslationKey() {
        if (isEmpty()) return "";
        return ItemUtil.getTranslationKey(item);
    }

    public ItemStack createStack(int count) {
        if (isEmpty()) return ItemStack.EMPTY;
        return ItemStack.of(ItemStackUtil.create(item, count));
    }

    public ItemStack createStack() {
        return createStack(1);
    }

    public boolean isInTag(CompatIdentifier id) {
        return ItemUtil.isInTag(item, id);
    }

    public boolean isBlock() {
        return !isEmpty() && item instanceof net.minecraft.item.BlockItem;
    }

    public BlockWrapper asBlock() {
        if (!isBlock())
            return BlockWrapper.of();

        return BlockWrapper.of(((net.minecraft.item.BlockItem) item).getBlock());
    }
}
