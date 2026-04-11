package net.pitan76.mcpitanlib.midohra.entity;

import net.minecraft.entity.Entity;

public class TypedEntityWrapper<T extends Entity> extends EntityWrapper {
    protected TypedEntityWrapper(T entity) {
        super(entity);
    }

    public static <T extends Entity> TypedEntityWrapper<T> ofRaw(T entity) {
        return new TypedEntityWrapper<>(entity);
    }

    public static <T extends Entity> TypedEntityWrapper<T> of(EntityWrapper wrapper) {
        return new TypedEntityWrapper<>((T) wrapper.get());
    }

    @Override
    public T get() {
        return (T) super.get();
    }
}
