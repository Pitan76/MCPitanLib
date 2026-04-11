package net.pitan76.mcpitanlib.midohra.entity.projectile;

import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.pitan76.mcpitanlib.api.entity.CompatThrownItemEntity;
import net.pitan76.mcpitanlib.api.util.entity.ThrownItemEntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

import java.util.Optional;

public class ThrownItemEntityWrapper<T extends ThrowableItemProjectile> extends ThrownEntityWrapper<T> {
    protected ThrownItemEntityWrapper(T entity) {
        super(entity);
    }

    public static <T extends ThrowableItemProjectile> ThrownItemEntityWrapper<T> ofRaw(T entity) {
        return new ThrownItemEntityWrapper<>(entity);
    }

    public static <T extends ThrowableItemProjectile> ThrownItemEntityWrapper<T> ofThrownItem(EntityWrapper wrapper) {
        return new ThrownItemEntityWrapper<>((T) wrapper.get());
    }

    public static boolean isThrownItem(EntityWrapper wrapper) {
        return wrapper.get() instanceof ThrowableItemProjectile;
    }

    public void setStack(ItemStack stack) {
        ThrownItemEntityUtil.setItem(get(), stack.toMinecraft());
    }

    public ItemStack getStack() {
        return ItemStack.of(ThrownItemEntityUtil.getItem(get()));
    }

     public void setVelocity(double x, double y, double z, float velocity, float divergence) {
         ThrownItemEntityUtil.setVelocity(get(), x, y, z, velocity, divergence);
     }

     public void setVelocity(EntityWrapper shooter, float pitch, float yaw, float roll, float speed, float divergence) {
         ThrownItemEntityUtil.setVelocity(get(), shooter.get(), pitch, yaw, roll, speed, divergence);
     }

     public void setVelocity(Vector3d velocity, float speed, float uncertainty) {
         setVelocity(velocity.x, velocity.y, velocity.z, speed, uncertainty);
     }

     public boolean isCompatThrownItemEntity() {
         return get() instanceof CompatThrownItemEntity;
     }

     public Optional<CompatThrownItemEntity> asCompatThrownItemEntity() {
         if (isCompatThrownItemEntity()) {
             return Optional.of((CompatThrownItemEntity) get());
         } else {
             return Optional.empty();
         }
     }
}
