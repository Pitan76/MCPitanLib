package net.pitan76.mcpitanlib.midohra.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class TypedEntityTypeWrapper<T extends Entity> extends EntityTypeWrapper {
    protected TypedEntityTypeWrapper(EntityType<T> type) {
        super(type);
    }

    public static <T extends Entity> TypedEntityTypeWrapper<T> ofRaw(EntityType<T> type) {
        return new TypedEntityTypeWrapper<>(type);
    }

    public static <T extends Entity> TypedEntityTypeWrapper<T> of(EntityTypeWrapper wrapper) {
        return new TypedEntityTypeWrapper<>((EntityType<T>) wrapper.get());
    }

    @Override
    public EntityType<T> get() {
        return (EntityType<T>) super.get();
    }
}
