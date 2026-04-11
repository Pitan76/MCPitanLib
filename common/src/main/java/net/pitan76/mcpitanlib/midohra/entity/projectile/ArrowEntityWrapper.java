package net.pitan76.mcpitanlib.midohra.entity.projectile;

import net.minecraft.entity.projectile.ArrowEntity;
import net.pitan76.mcpitanlib.api.util.entity.ArrowEntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;
import net.pitan76.mcpitanlib.midohra.world.World;

public class ArrowEntityWrapper extends PersistentProjectileEntityWrapper<ArrowEntity> {
    protected ArrowEntityWrapper(ArrowEntity entity) {
        super(entity);
    }

    public static ArrowEntityWrapper ofRaw(ArrowEntity entity) {
        return new ArrowEntityWrapper(entity);
    }

    public static ArrowEntityWrapper ofArrow(EntityWrapper wrapper) {
        return new ArrowEntityWrapper((ArrowEntity) wrapper.get());
    }

    public static boolean isArrow(EntityWrapper wrapper) {
        return wrapper.get() instanceof ArrowEntity;
    }

    public static ArrowEntityWrapper create(World world, double x, double y, double z) {
        return new ArrowEntityWrapper(ArrowEntityUtil.create(world.toMinecraft(), x, y, z));
    }

    public static ArrowEntityWrapper create(World world, Vector3d pos) {
        return create(world, pos.x, pos.y, pos.z);
    }

    public static ArrowEntityWrapper create(World world, double x, double y, double z, ItemStack stack) {
        return new ArrowEntityWrapper(ArrowEntityUtil.create(world.toMinecraft(), x, y, z, stack.toMinecraft()));
    }

    public static ArrowEntityWrapper create(World world, Vector3d pos, ItemStack stack) {
        return create(world, pos.x, pos.y, pos.z, stack);
    }

    public void setVelocity(double x, double y, double z, float velocity, float divergence) {
        ArrowEntityUtil.setVelocity(get(), x, y, z, velocity, divergence);
    }

    public void setVelocity(Vector3d velocity, float speed, float uncertainty) {
        setVelocity(velocity.x, velocity.y, velocity.z, speed, uncertainty);
    }
}
