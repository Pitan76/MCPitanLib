package net.pitan76.mcpitanlib.midohra.entity.projectile;

import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.pitan76.mcpitanlib.api.util.entity.SpectralArrowEntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;
import net.pitan76.mcpitanlib.midohra.world.World;

public class SpectralArrowEntityWrapper extends PersistentProjectileEntityWrapper<SpectralArrowEntity> {
    protected SpectralArrowEntityWrapper(SpectralArrowEntity entity) {
        super(entity);
    }

    public static SpectralArrowEntityWrapper ofRaw(SpectralArrowEntity entity) {
        return new SpectralArrowEntityWrapper(entity);
    }

    public static SpectralArrowEntityWrapper ofSpectralArrow(EntityWrapper wrapper) {
        return new SpectralArrowEntityWrapper((SpectralArrowEntity) wrapper.get());
    }

    public static boolean isSpectralArrow(EntityWrapper wrapper) {
        return wrapper.get() instanceof SpectralArrowEntity;
    }

    public static SpectralArrowEntityWrapper create(World world, double x, double y, double z) {
        return new SpectralArrowEntityWrapper(SpectralArrowEntityUtil.create(world.toMinecraft(), x, y, z));
    }

    public static SpectralArrowEntityWrapper create(World world, Vector3d pos) {
        return create(world, pos.x, pos.y, pos.z);
    }

    public static SpectralArrowEntityWrapper create(World world, double x, double y, double z, ItemStack stack) {
        return new SpectralArrowEntityWrapper(SpectralArrowEntityUtil.create(world.toMinecraft(), x, y, z, stack.toMinecraft()));
    }

    public static SpectralArrowEntityWrapper create(World world, Vector3d pos, ItemStack stack) {
        return create(world, pos.x, pos.y, pos.z, stack);
    }

    public void setVelocity(double x, double y, double z, float velocity, float divergence) {
        SpectralArrowEntityUtil.setVelocity(get(), x, y, z, velocity, divergence);
    }

    public void setVelocity(Vector3d velocity, float speed, float uncertainty) {
        setVelocity(velocity.x, velocity.y, velocity.z, speed, uncertainty);
    }
}
