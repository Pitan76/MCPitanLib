package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.world.level.block.Block;

public class TypedBlockWrapper<T extends Block> extends BlockWrapper {
    protected TypedBlockWrapper(T block) {
        super(block);
    }

    public static <T extends Block> TypedBlockWrapper<T> ofRaw(T block) {
        return new TypedBlockWrapper<>(block);
    }

    public static <T extends Block> TypedBlockWrapper<T> of(BlockWrapper wrapper) {
        if (wrapper instanceof SupplierBlockWrapper) {
            SupplierBlockWrapper supplierWrapper = (SupplierBlockWrapper) wrapper;
            return SupplierTypedBlockWrapper.of(supplierWrapper);
        }

        return new TypedBlockWrapper<>((T) wrapper.get());
    }

    @Override
    public T get() {
        return (T) super.get();
    }
}
