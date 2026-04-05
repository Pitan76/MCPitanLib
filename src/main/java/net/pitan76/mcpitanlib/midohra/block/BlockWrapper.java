package net.pitan76.mcpitanlib.midohra.block;

import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.block.BlockUtil;
import net.pitan76.mcpitanlib.midohra.easybuilder.built.BuiltBlock;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BlockWrapper {
    private final net.minecraft.world.level.block.Block block;

    protected BlockWrapper() {
        this.block = null;
    }

    protected BlockWrapper(net.minecraft.world.level.block.Block block) {
        this.block = block;
    }

    public static BlockWrapper of(net.minecraft.world.level.block.Block block) {
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

    @Nullable
    public net.minecraft.world.level.block.Block get() {
        return block;
    }

    public net.minecraft.world.level.block.Block gerOrDefault(net.minecraft.world.level.block.Block defaultItem) {
        return getOrDefault(defaultItem);
    }

    public net.minecraft.world.level.block.Block getOrDefault(net.minecraft.world.level.block.Block defaultItem) {
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

    public boolean rawEquals(BlockWrapper block) {
        return get() == block.get();
    }

    @Override
    public int hashCode() {
        return get() != null ? get().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlockWrapper other = (BlockWrapper) obj;
        return rawEquals(other);
    }

    public static BlockWrapper of(String id) {
        return of(CompatIdentifier.of(id));
    }

    public static BlockWrapper of(String namespace, String path) {
        return of(CompatIdentifier.of(namespace, path));
    }

    public static BlockWrapper of(CompatBlock block) {
        return of((net.minecraft.world.level.block.Block) block);
    }

    public Optional<CompatBlock> toCompatBlock() {
        if (get() instanceof CompatBlock) {
            return Optional.of((CompatBlock) get());
        }
        return Optional.empty();
    }

    public Optional<BuiltBlock> toBuiltBlock() {
        if (get() instanceof BuiltBlock) {
            return Optional.of((BuiltBlock) get());
        }
        return Optional.empty();
    }

    public float getHardness() {
        if (isEmpty()) return 0;
        return BlockUtil.getHardness(block);
    }

    public BlockStateM getDefaultStateM() {
        return BlockStateM.of(get());
    }
}
