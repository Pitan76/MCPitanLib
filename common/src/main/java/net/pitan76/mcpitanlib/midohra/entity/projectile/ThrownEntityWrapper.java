package net.pitan76.mcpitanlib.midohra.entity.projectile;

import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public class ThrownEntityWrapper<T extends ThrowableProjectile> extends ProjectileEntityWrapper<T> {
    protected ThrownEntityWrapper(T entity) {
        super(entity);
    }

    public static <T extends ThrowableProjectile> ThrownEntityWrapper<T> ofRaw(T entity) {
        return new ThrownEntityWrapper<>(entity);
    }

    public static <T extends ThrowableProjectile> ThrownEntityWrapper<T> ofThrown(EntityWrapper wrapper) {
        return new ThrownEntityWrapper<>((T) wrapper.get());
    }

    public static boolean isThrown(EntityWrapper wrapper) {
        return wrapper.get() instanceof ThrowableProjectile;
    }
}
