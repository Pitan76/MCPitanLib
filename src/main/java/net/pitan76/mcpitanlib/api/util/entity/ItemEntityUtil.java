package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.entity.ItemEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3i;

import java.util.List;
import java.util.stream.Collectors;

public class ItemEntityUtil {
    public static ItemEntity create(Level world, double x, double y, double z, ItemStack stack) {
        return new ItemEntity(world, x, y, z, stack);
    }

    public static ItemEntity create(Level world, BlockPos pos, ItemStack stack) {
        return create(world, pos.getX(), pos.getY(), pos.getZ(), stack);
    }

    public static ItemEntity create(Level world, Vec3 pos, ItemStack stack) {
        return create(world, pos.x, pos.y, pos.z, stack);
    }

    public static ItemEntity create(Level world, double x, double y, double z, ItemStack stack, double velocityX, double velocityY, double velocityZ) {
        ItemEntity itemEntity = create(world, x, y, z, stack);
        setVelocity(itemEntity, velocityX, velocityY, velocityZ);
        return itemEntity;
    }

    public static void setVelocity(ItemEntity itemEntity, double velocityX, double velocityY, double velocityZ) {
        itemEntity.setDeltaMovement(velocityX, velocityY, velocityZ);
    }

    public static void setVelocity(ItemEntity itemEntity, Vec3 vec3d) {
        itemEntity.setDeltaMovement(vec3d);
    }

    public static void setPickupDelay(ItemEntity itemEntity, int pickupDelay) {
        itemEntity.setPickUpDelay(pickupDelay);
    }

    public static void setToDefaultPickupDelay(ItemEntity itemEntity) {
        itemEntity.setDefaultPickUpDelay();
    }

    public static ItemStack getStack(ItemEntity entity) {
        return entity.getItem();
    }

    public static List<ItemEntity> getEntities(Level world, AABB box) {
        return WorldUtil.getEntitiesByType(world, EntityType.ITEM, box);
    }

    public static ItemEntity createWithSpawn(Level world, ItemStack stack, double x, double y, double z) {
        ItemEntity itemEntity = create(world, x, y, z, stack);
        setToDefaultPickupDelay(itemEntity);
        setVelocity(itemEntity, 0.0D, 0.0D, 0.0D);
        WorldUtil.spawnEntity(world, itemEntity);
        return itemEntity;
    }

    public static ItemEntity createWithSpawn(Level world, ItemStack stack, BlockPos pos) {
        return createWithSpawn(world, stack, pos.getX(), pos.getY(), pos.getZ());
    }

    public static ItemEntity createWithSpawn(Level world, ItemStack stack, Vector3d pos) {
        return createWithSpawn(world, stack, pos.x, pos.y, pos.z);
    }

    public static ItemEntity createWithSpawn(Level world, ItemStack stack, Vector3i pos) {
        return createWithSpawn(world, stack, pos.toCenter());
    }

    public static ItemEntity createWithSpawn(net.pitan76.mcpitanlib.midohra.world.World world, ItemStack stack, double x, double y, double z) {
        return createWithSpawn(world.getRaw(), stack, x, y, z);
    }

    public static ItemEntity createWithSpawn(net.pitan76.mcpitanlib.midohra.world.World world, ItemStack stack, BlockPos pos) {
        return createWithSpawn(world, stack, pos.getX(), pos.getY(), pos.getZ());
    }

    public static ItemEntity createWithSpawn(net.pitan76.mcpitanlib.midohra.world.World world, ItemStack stack, Vector3i pos) {
        return createWithSpawn(world, stack, pos.toCenter());
    }

    public static ItemEntity createWithSpawn(net.pitan76.mcpitanlib.midohra.world.World world, ItemStack stack, net.pitan76.mcpitanlib.midohra.util.math.Vector3d pos) {
        return createWithSpawn(world.getRaw(), stack, pos.x, pos.y, pos.z);
    }

    public static ItemEntity createWithSpawnAtCenter(net.pitan76.mcpitanlib.midohra.world.World world, ItemStack stack, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return createWithSpawn(world, stack, pos.toCenterVector3d());
    }

    public static ItemEntity createWithSpawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.item.ItemStack stack, double x, double y, double z) {
        return createWithSpawn(world.getRaw(), stack.toMinecraft(), x, y, z);
    }

    public static ItemEntity createWithSpawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.item.ItemStack stack, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return createWithSpawn(world.getRaw(), stack.toMinecraft(), pos.toRaw());
    }

    public static ItemEntity createWithSpawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.item.ItemStack stack, net.pitan76.mcpitanlib.midohra.util.math.Vector3d pos) {
        return createWithSpawn(world.getRaw(), stack.toMinecraft(), pos.x, pos.y, pos.z);
    }

    public static ItemEntity createWithSpawnAtCenter(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.item.ItemStack stack, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return createWithSpawn(world, stack, pos.toCenterVector3d());
    }

    public static ItemEntity createWithSpawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.item.ItemStack stack, net.pitan76.mcpitanlib.midohra.util.math.Vector3i pos) {
        return createWithSpawn(world, stack, pos.toCenter());
    }

    public static List<ItemEntity> getEntities(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntities(world.getRaw(), box.toMinecraft());
    }

    public static List<ItemEntityWrapper> getEntityWrappers(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntities(world, box).stream().map(ItemEntityWrapper::of).collect(Collectors.toList());
    }

    public static void onPlayerCollision(ItemEntity itemEntity, Player player) {
        itemEntity.playerTouch(player.getEntity());
    }

    public static void onPlayerCollision(ItemEntityWrapper itemEntity, Player player) {
        onPlayerCollision(itemEntity.get(), player);
    }
}
