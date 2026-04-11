package net.pitan76.mcpitanlib.midohra.entity.projectile;

import net.minecraft.world.entity.projectile.Projectile;
import net.pitan76.mcpitanlib.api.util.entity.ProjectileEntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.entity.TypedEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

public class ProjectileEntityWrapper<T extends Projectile> extends TypedEntityWrapper<T> {
    protected ProjectileEntityWrapper(T entity) {
        super(entity);
    }

    public static <T extends Projectile> ProjectileEntityWrapper<T> ofRaw(T entity) {
        return new ProjectileEntityWrapper<>(entity);
    }

    public static <T extends Projectile> ProjectileEntityWrapper<T> ofProjectile(EntityWrapper wrapper) {
        return new ProjectileEntityWrapper<>((T) wrapper.get());
    }

    public static boolean isProjectile(EntityWrapper wrapper) {
        return wrapper.get() instanceof Projectile;
    }

    public void setVelocity(EntityWrapper shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        ProjectileEntityUtil.setVelocity(get(), shooter.get(), pitch, yaw, roll, speed, divergence);
    }

    public void setVelocity(double x, double y, double z, float power, float uncertainty) {
        ProjectileEntityUtil.setVelocity(get(), x, y, z, power, uncertainty);
    }

    public void setVelocity(Vector3d velocity, float power, float uncertainty) {
        setVelocity(velocity.x, velocity.y, velocity.z, power, uncertainty);
    }

    public void setOwner(EntityWrapper owner) {
        get().setOwner(owner.get());
    }

    public boolean hasOwner() {
        return get().getOwner() != null;
    }

    public EntityWrapper getOwner() {
        return EntityWrapper.of(get().getOwner());
    }
}
