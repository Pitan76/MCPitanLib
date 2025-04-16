package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

import java.util.List;

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

    public static ItemStack getStack(ItemEntity entity) {
        return entity.getStack();
    }

    public static List<ItemEntity> getEntities(World world, Box box) {
        return WorldUtil.getEntitiesByType(world, EntityType.ITEM, box);
    }

    public static ItemEntity createWithSpawn(World world, ItemStack stack, double x, double y, double z) {
        ItemEntity itemEntity = create(world, x, y, z, stack);
        setToDefaultPickupDelay(itemEntity);
        setVelocity(itemEntity, 0.0D, 0.0D, 0.0D);
        WorldUtil.spawnEntity(world, itemEntity);
        return itemEntity;
    }

    public static ItemEntity createWithSpawn(World world, ItemStack stack, BlockPos pos) {
        return createWithSpawn(world, stack, pos.getX(), pos.getY(), pos.getZ());
    }
}
