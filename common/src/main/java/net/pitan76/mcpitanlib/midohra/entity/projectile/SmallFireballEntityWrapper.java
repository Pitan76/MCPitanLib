package net.pitan76.mcpitanlib.midohra.entity.projectile;

import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.pitan76.mcpitanlib.api.util.entity.SmallFireballEntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;
import net.pitan76.mcpitanlib.midohra.world.World;

public class SmallFireballEntityWrapper extends ProjectileEntityWrapper<SmallFireball> {
    protected SmallFireballEntityWrapper(SmallFireball entity) {
        super(entity);
    }

    public static SmallFireballEntityWrapper ofRaw(SmallFireball entity) {
        return new SmallFireballEntityWrapper(entity);
    }

    public static SmallFireballEntityWrapper ofSmallFireball(EntityWrapper wrapper) {
        return new SmallFireballEntityWrapper((SmallFireball) wrapper.get());
    }

    public static boolean isSmallFireball(EntityWrapper wrapper) {
        return wrapper.get() instanceof SmallFireball;
    }

    public static SmallFireballEntityWrapper create(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        return new SmallFireballEntityWrapper(SmallFireballEntityUtil.create(world.toMinecraft(), x, y, z, velocityX, velocityY, velocityZ));
    }

    public static SmallFireballEntityWrapper create(World world, Vector3d pos, Vector3d velocity) {
        return create(world, pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z);
    }

    public void setStack(ItemStack stack) {
        SmallFireballEntityUtil.setItem(get(), stack.toMinecraft());
    }

    public ItemStack getStack() {
        return ItemStack.of(SmallFireballEntityUtil.getItem(get()));
    }

    public void setVelocity(double x, double y, double z, float velocity, float divergence) {
        SmallFireballEntityUtil.setVelocity(get(), x, y, z, velocity, divergence);
    }

    public void setVelocity(Vector3d velocity, float speed, float uncertainty) {
        setVelocity(velocity.x, velocity.y, velocity.z, speed, uncertainty);
    }
}
