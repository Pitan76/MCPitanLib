package net.pitan76.mcpitanlib.midohra.entity.projectile;

import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public class ThrownEntityWrapper<T extends ThrownEntity> extends ProjectileEntityWrapper<T> {
    protected ThrownEntityWrapper(T entity) {
        super(entity);
    }

    public static <T extends ThrownEntity> ThrownEntityWrapper<T> ofRaw(T entity) {
        return new ThrownEntityWrapper<>(entity);
    }

    public static <T extends ThrownEntity> ThrownEntityWrapper<T> ofThrown(EntityWrapper wrapper) {
        return new ThrownEntityWrapper<>((T) wrapper.get());
    }

    public static boolean isThrown(EntityWrapper wrapper) {
        return wrapper.get() instanceof ThrownEntity;
    }
}
