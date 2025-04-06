package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemEntityUtil {
    public static ItemEntity create(World world, double x, double y, double z, ItemStack stack) {
        return new ItemEntity(world, x, y, z, stack);
    }

    public static ItemEntity create(World world, BlockPos pos, ItemStack stack) {
        return create(world, pos.getX(), pos.getY(), pos.getZ(), stack);
    }

    public static ItemEntity create(World world, Vec3d pos, ItemStack stack) {
        return create(world, pos.x, pos.y, pos.z, stack);
    }

    public static ItemEntity create(World world, double x, double y, double z, ItemStack stack, double velocityX, double velocityY, double velocityZ) {
        ItemEntity itemEntity = create(world, x, y, z, stack);
        setVelocity(itemEntity, velocityX, velocityY, velocityZ);
        return itemEntity;
    }

    public static void setVelocity(ItemEntity itemEntity, double velocityX, double velocityY, double velocityZ) {
        itemEntity.setVelocity(velocityX, velocityY, velocityZ);
    }

    public static void setVelocity(ItemEntity itemEntity, Vec3d vec3d) {
        itemEntity.setVelocity(vec3d);
    }

    public static void setPickupDelay(ItemEntity itemEntity, int pickupDelay) {
        itemEntity.setPickupDelay(pickupDelay);
    }

    public static void setToDefaultPickupDelay(ItemEntity itemEntity) {
        itemEntity.setToDefaultPickupDelay();
    }
}
