package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.dimension.DimensionType;
import net.pitan76.mcpitanlib.midohra.entity.EntityTypeWrapper;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class WorldViewUtil {

    public static boolean isClient(WorldView world) {
        return world.isClient();
    }

    public static BlockState getBlockState(WorldView world, BlockPos pos) {
        return world.getBlockState(pos);
    }

    public static Block getBlock(WorldView world, BlockPos pos) {
        return getBlockState(world, pos).getBlock();
    }

    public static BlockEntity getBlockEntity(WorldView world, BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    public static <T extends BlockEntity> Optional<T> getBlockEntity(WorldView world, BlockPos pos, BlockEntityType<T> type) {
        return Optional.ofNullable(type.get(world, pos));
    }

    public static FluidState getFluidState(WorldView world, BlockPos pos) {
        return getBlockState(world, pos).getFluidState();
    }

    public static Fluid getFluid(WorldView world, BlockPos pos) {
        return getFluidState(world, pos).getFluid();
    }

    public static int getBottomY(WorldView world) {
        return 0;
    }

    public static int getTopY(WorldView world) {
        return world.getHeight();
    }

    public static boolean isChunkLoaded(WorldView world, BlockPos pos) {
        return world.isChunkLoaded(pos);
    }

    public static boolean isRegionLoaded(WorldView world, BlockPos min, BlockPos max) {
        return world.isRegionLoaded(min, max);
    }

    public static DimensionType getDimensionType(WorldView world) {
        return world.getDimension();
    }

    public static boolean isAirBlock(WorldView world, BlockPos pos) {
        return getBlockState(world, pos).isAir();
    }

    public static boolean isOpaqueBlock(WorldView world, BlockPos pos) {
        return getBlockState(world, pos).isOpaque();
    }

    public static boolean isWater(WorldView world, BlockPos pos) {
        return getFluidState(world, pos).isIn(FluidTags.WATER);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(WorldAccess world, Class<T> entityClass, Box box, Predicate<? super T> predicate) {
        return world.getEntitiesByClass(entityClass, box, predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(WorldAccess world, Class<T> entityClass, net.pitan76.mcpitanlib.midohra.util.math.Box box, Predicate<? super T> predicate) {
        return getEntitiesByClass(world, entityClass, box.toMinecraft(), predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByType(WorldAccess world, EntityType<T> entityType, Box box, Predicate<? super Entity> predicate) {
        if (world instanceof World) {
            return ((World) world).getEntitiesByType(entityType, box, predicate);
        }

        // EntityType<T> のTを
        return (List<T>) world.getEntitiesByClass(Entity.class, box, predicate.and(entity -> ((Entity) entity).getType() == entityType));
    }

    public static <T extends Entity> List<T> getEntitiesByType(WorldAccess world, EntityType<T> entityType, net.pitan76.mcpitanlib.midohra.util.math.Box box, Predicate<? super Entity> predicate) {
        return getEntitiesByType(world, entityType, box.toMinecraft(), predicate);
    }

    public static List<?> getEntitiesByType(WorldAccess world, EntityTypeWrapper entityType, Box box, Predicate<? super Entity> predicate) {
        return getEntitiesByType(world, entityType.get(), box, predicate);
    }
}
