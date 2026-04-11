package net.pitan76.mcpitanlib.midohra.entity.projectile;

import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.pitan76.mcpitanlib.api.util.entity.SnowballEntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;
import net.pitan76.mcpitanlib.midohra.world.World;

public class SnowballEntityWrapper extends ThrownItemEntityWrapper<Snowball> {
    protected SnowballEntityWrapper(Snowball entity) {
        super(entity);
    }

    public static SnowballEntityWrapper ofRaw(Snowball entity) {
        return new SnowballEntityWrapper(entity);
    }

    public static SnowballEntityWrapper ofSnowball(EntityWrapper wrapper) {
        return new SnowballEntityWrapper((Snowball) wrapper.get());
    }

    public static boolean isSnowball(EntityWrapper wrapper) {
        return wrapper.get() instanceof Snowball;
    }

    public static SnowballEntityWrapper create(World world, double x, double y, double z) {
        return new SnowballEntityWrapper(SnowballEntityUtil.create(world.toMinecraft(), x, y, z));
    }

    public static SnowballEntityWrapper create(World world, Vector3d pos) {
        return create(world, pos.x, pos.y, pos.z);
    }

    public static SnowballEntityWrapper create(World world, double x, double y, double z, ItemStack stack) {
        return new SnowballEntityWrapper(SnowballEntityUtil.create(world.toMinecraft(), x, y, z, stack.toMinecraft()));
    }

    public static SnowballEntityWrapper create(World world, Vector3d pos, ItemStack stack) {
        return create(world, pos.x, pos.y, pos.z, stack);
    }

    public void setStack(ItemStack stack) {
        SnowballEntityUtil.setItem(get(), stack.toMinecraft());
    }

    public ItemStack getStack() {
        return ItemStack.of(SnowballEntityUtil.getItem(get()));
    }

    public void setVelocity(double x, double y, double z, float velocity, float divergence) {
        SnowballEntityUtil.setVelocity(get(), x, y, z, velocity, divergence);
    }

    public void setVelocity(Vector3d velocity, float speed, float uncertainty) {
        setVelocity(velocity.x, velocity.y, velocity.z, speed, uncertainty);
    }
}
