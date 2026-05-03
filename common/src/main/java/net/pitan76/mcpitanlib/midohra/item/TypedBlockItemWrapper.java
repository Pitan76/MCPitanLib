package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.item.BlockItem;
import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.midohra.block.TypedBlockWrapper;

public class TypedBlockItemWrapper<T extends Block> extends ItemWrapper {
    protected T block;

    protected TypedBlockItemWrapper(T block) {
        super(block.asItem());
        this.block = block;
    }

    public static <T extends Block> TypedBlockItemWrapper<T> ofRaw(T block) {
        return new TypedBlockItemWrapper<>(block);
    }

    public static <T extends Block> TypedBlockItemWrapper<T> of(TypedBlockWrapper<T> wrapper) {
        return ofRaw(wrapper.get());
    }

    public static <T extends Block> TypedBlockItemWrapper<T> of(ItemWrapper wrapper) {
        if (wrapper instanceof SupplierItemWrapper) {
            SupplierItemWrapper supplierWrapper = (SupplierItemWrapper) wrapper;
            return SupplierTypedBlockItemWrapper.of(supplierWrapper);
        }

        return TypedBlockItemWrapper.ofRaw((T) wrapper.asBlock().get());
    }

    @Override
    public TypedBlockWrapper<T> asBlock() {
        return TypedBlockWrapper.ofRaw(block);
    }

    @Override
    public boolean isBlock() {
        return true;
    }

    @Override
    public BlockItem get() {
        return (BlockItem) super.get();
    }
}
