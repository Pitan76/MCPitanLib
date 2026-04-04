package net.pitan76.mcpitanlib.midohra.block.entity;

import net.minecraft.world.level.block.entity.BlockEntity;

public class TypedBlockEntityWrapper<T extends BlockEntity> extends BlockEntityWrapper {
    protected TypedBlockEntityWrapper(T item) {
        super(item);
    }

    public static <T extends BlockEntity> TypedBlockEntityWrapper<T> ofRaw(T item) {
        return new TypedBlockEntityWrapper<>(item);
    }

    public static <T extends BlockEntity> TypedBlockEntityWrapper<T> of(BlockEntityWrapper wrapper) {
        return new TypedBlockEntityWrapper<>((T) wrapper.get());
    }

    @Override
    public T get() {
        return (T) super.get();
    }
}
