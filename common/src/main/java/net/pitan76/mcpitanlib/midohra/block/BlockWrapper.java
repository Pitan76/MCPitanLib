package net.pitan76.mcpitanlib.midohra.block;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.block.BlockUtil;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import org.jetbrains.annotations.Nullable;

public class BlockWrapper {
    private final net.minecraft.block.Block block;

    protected BlockWrapper() {
        this.block = null;
    }

    protected BlockWrapper(net.minecraft.block.Block block) {
        this.block = block;
    }

    public static BlockWrapper of(net.minecraft.block.Block block) {
        return new BlockWrapper(block);
    }

    public static BlockWrapper of() {
        return new BlockWrapper();
    }

    public static BlockWrapper of(CompatIdentifier id) {
        if (BlockUtil.isExist(id))
            return of(BlockUtil.fromId(id));

        return of();
    }

    public static BlockWrapper of(CompatIdentifier id, CompatIdentifier... ids) {
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
        return get() == null;
    }

    @Nullable
    public net.minecraft.block.Block get() {
        return block;
    }

    public net.minecraft.block.Block gerOrDefault(net.minecraft.block.Block defaultItem) {
        return isEmpty() ? defaultItem : get();
    }

    public CompatIdentifier getId() {
        if (isEmpty())
            return CompatIdentifier.empty();

        return BlockUtil.toId(get());
    }

    public String getName() {
        if (isEmpty()) return "";
        return BlockUtil.getNameAsString(get());
    }

    public String getTranslationKey() {
        if (isEmpty()) return "";
        return BlockUtil.getTranslationKey(get());
    }

    public ItemWrapper asItem() {
        if (isEmpty())
            return ItemWrapper.of();

        return ItemWrapper.of(BlockUtil.toItem(get()));
    }

    public BlockState getDefaultState() {
        return BlockState.of(get());
    }
}
