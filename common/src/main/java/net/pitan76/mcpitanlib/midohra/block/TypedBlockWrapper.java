package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.world.level.block.Block;

public class TypedBlockWrapper<T extends Block> extends BlockWrapper {
    protected TypedBlockWrapper(T item) {
        super(item);
    }

    public static <T extends Block> TypedBlockWrapper<T> ofRaw(T item) {
        return new TypedBlockWrapper<>(item);
    }

    public static <T extends Block> TypedBlockWrapper<T> of(BlockWrapper wrapper) {
        return new TypedBlockWrapper<>((T) wrapper.get());
    }

    @Override
    public T get() {
        return (T) super.get();
    }
}
