package net.pitan76.mcpitanlib.midohra.block.entity;

import net.minecraft.block.entity.BlockEntity;

public class TypedBlockEntityWrapper<T extends BlockEntity> extends BlockEntityWrapper {
    protected TypedBlockEntityWrapper(T blockEntity) {
        super(blockEntity);
    }

    public static <T extends BlockEntity> TypedBlockEntityWrapper<T> ofRaw(T blockEntity) {
        return new TypedBlockEntityWrapper<>(blockEntity);
    }

    public static <T extends BlockEntity> TypedBlockEntityWrapper<T> of(BlockEntityWrapper wrapper) {
        return new TypedBlockEntityWrapper<>((T) wrapper.get());
    }

    @Override
    public T get() {
        return (T) super.get();
    }
}
