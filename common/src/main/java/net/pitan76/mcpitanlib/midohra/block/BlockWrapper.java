package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.block.ICompatBlock;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.block.BlockUtil;
import net.pitan76.mcpitanlib.midohra.easybuilder.built.BuiltBlock;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

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
    public net.minecraft.block.Block get() {
        return block;
    }

    public net.minecraft.block.Block gerOrDefault(net.minecraft.block.Block defaultItem) {
        return getOrDefault(defaultItem);
    }

    public net.minecraft.block.Block getOrDefault(net.minecraft.block.Block defaultItem) {
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

    /**
     * Check if the block of this wrapper is a fluid block (e.g. water, lava).
     * @return true if the block is a fluid block
     */
    public boolean isFluidBlock() {
        return instanceOf(net.minecraft.block.FluidBlock.class);
    }

    public boolean rawEquals(BlockWrapper block) {
        return get() == block.get();
    }

    @Override
    public int hashCode() {
        return get() != null ? get().hashCode() : 0;
    }

    /**
     * 同じブロックを指しているかどうかを比較する。サブクラス同士でも成立する。
     * <p>
     * registry2.registerBlock(...)が返すSupplierITypedBlockWrapperと、world.getBlockState(pos).getBlock()が返す素のBlockWrapperのように、
     * 取得経路によって実際の型が違っても、同じブロックを指していればtrueになる。
     * @param obj 比較対象
     * @return 同じものを指していればtrue
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        // サブクラス（Supplier版・Typed版など）同士でも成立させるため、getClass()ではなくinstanceofで判定する
        if (!(obj instanceof BlockWrapper)) return false;
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
        return of((net.minecraft.block.Block) block);
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

    /**
     * instanceof check for the block of this wrapper.
     * @param clazz the class of the block to check
     * @return true if the block of this wrapper is an instance of the given class, false otherwise
     */
    public boolean instanceOf(Class<?> clazz) {
        if (isEmpty()) return false;

        return clazz.isInstance(get());
    }

    /**
     * instanceof check for the block of this wrapper.
     * @param wrapper the block to check
     * @return true if the block of this wrapper is an instance of the given block, false otherwise
     */
    public boolean instanceOf(BlockWrapper wrapper) {
        if (isEmpty()) return false;

        Block block = wrapper.get();
        if (block == null) return false;

        Class<?> clazz = block.getClass();
        return clazz.isInstance(get());
    }

    public <T extends CompatBlock> T getCompatBlock(Class<T> clazz) {
        if (isEmpty()) return null;
        if (get() instanceof CompatBlock) {
            CompatBlock compatBlock = (CompatBlock) get();
            if (clazz.isInstance(compatBlock))
                return clazz.cast(compatBlock);
        }
        return null;
    }

    public <T extends CompatBlock> Optional<T> toCompatBlock(Class<T> clazz) {
        return Optional.ofNullable(getCompatBlock(clazz));
    }

    public <T extends ICompatBlock> T getICompatBlock(Class<T> clazz) {
        if (isEmpty()) return null;
        if (get() instanceof ICompatBlock) {
            ICompatBlock compatBlock = (ICompatBlock) get();
            if (clazz.isInstance(compatBlock))
                return clazz.cast(compatBlock);
        }
        return null;
    }

    public <T extends ICompatBlock> Optional<T> toICompatBlock(Class<T> clazz) {
        return Optional.ofNullable(getICompatBlock(clazz));
    }
}
