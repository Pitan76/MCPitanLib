package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.item.Item;

public class TypedItemWrapper<T extends Item> extends ItemWrapper {
    protected TypedItemWrapper(T item) {
        super(item);
    }

    public static <T extends Item> TypedItemWrapper<T> ofRaw(T item) {
        return new TypedItemWrapper<>(item);
    }

    public static <T extends Item> TypedItemWrapper<T> of(ItemWrapper wrapper) {
        return new TypedItemWrapper<>((T) wrapper.get());
    }

    @Override
    public T get() {
        return (T) super.get();
    }
}
