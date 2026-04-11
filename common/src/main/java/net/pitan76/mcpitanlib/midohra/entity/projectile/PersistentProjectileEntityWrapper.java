package net.pitan76.mcpitanlib.midohra.entity.projectile;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;

public class PersistentProjectileEntityWrapper<T extends PersistentProjectileEntity> extends ProjectileEntityWrapper<T> {
    protected PersistentProjectileEntityWrapper(T entity) {
        super(entity);
    }

    public static <T extends PersistentProjectileEntity> PersistentProjectileEntityWrapper<T> ofRaw(T entity) {
        return new PersistentProjectileEntityWrapper<>(entity);
    }

    public static boolean isPersistentProjectile(EntityWrapper wrapper) {
        return wrapper.get() instanceof PersistentProjectileEntity;
    }

    public static <T extends PersistentProjectileEntity> PersistentProjectileEntityWrapper<T> ofPersistentProjectile(EntityWrapper wrapper) {
        return new PersistentProjectileEntityWrapper<>((T) wrapper.get());
    }

    public int getPierceLevel() {
        return get().getPierceLevel();
    }

    public ItemStack getStack() {
        return ItemStack.of(get().asItemStack());
    }

    public void setDamage(double damage) {
        get().setDamage(damage);
    }
}
