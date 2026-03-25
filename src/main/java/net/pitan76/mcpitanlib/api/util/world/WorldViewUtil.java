package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.dimension.DimensionType;
import net.pitan76.mcpitanlib.midohra.entity.EntityTypeWrapper;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class WorldViewUtil {

    public static boolean isClient(LevelReader world) {
        return world.isClientSide();
    }

    public static BlockState getBlockState(LevelReader world, BlockPos pos) {
        return world.getBlockState(pos);
    }

    public static Block getBlock(LevelReader world, BlockPos pos) {
        return getBlockState(world, pos).getBlock();
    }

    public static BlockEntity getBlockEntity(LevelReader world, BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    public static <T extends BlockEntity> Optional<T> getBlockEntity(LevelReader world, BlockPos pos, BlockEntityType<T> type) {
        return world.getBlockEntity(pos, type);
    }

    public static FluidState getFluidState(LevelReader world, BlockPos pos) {
        return getBlockState(world, pos).getFluidState();
    }

    public static Fluid getFluid(LevelReader world, BlockPos pos) {
        return getFluidState(world, pos).getType();
    }

    public static int getBottomY(LevelReader world) {
        return world.getMinY();
    }

    public static int getTopY(LevelReader world) {
        return world.getMaxY();
    }

    public static boolean isChunkLoaded(LevelReader world, BlockPos pos) {
        return world.hasChunkAt(pos);
    }

    public static boolean isRegionLoaded(LevelReader world, BlockPos min, BlockPos max) {
        return world.hasChunksAt(min, max);
    }

    public static DimensionType getDimensionType(LevelReader world) {
        return world.dimensionType();
    }

    public static boolean isAirBlock(LevelReader world, BlockPos pos) {
        return getBlockState(world, pos).isAir();
    }

    public static boolean isOpaqueBlock(LevelReader world, BlockPos pos) {
        return getBlockState(world, pos).canOcclude();
    }

    public static boolean isWater(LevelReader world, BlockPos pos) {
        return getFluidState(world, pos).is(FluidTags.WATER);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(LevelAccessor world, Class<T> entityClass, AABB box, Predicate<? super T> predicate) {
        return world.getEntitiesOfClass(entityClass, box, predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(LevelAccessor world, Class<T> entityClass, net.pitan76.mcpitanlib.midohra.util.math.Box box, Predicate<? super T> predicate) {
        return getEntitiesByClass(world, entityClass, box.toMinecraft(), predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByType(LevelAccessor world, EntityType<T> entityType, AABB box, Predicate<? super Entity> predicate) {
        return world.getEntities(entityType, box, predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByType(LevelAccessor world, EntityType<T> entityType, net.pitan76.mcpitanlib.midohra.util.math.Box box, Predicate<? super Entity> predicate) {
        return getEntitiesByType(world, entityType, box.toMinecraft(), predicate);
    }

    public static List<?> getEntitiesByType(LevelAccessor world, EntityTypeWrapper entityType, AABB box, Predicate<? super Entity> predicate) {
        return getEntitiesByType(world, entityType.get(), box, predicate);
    }
}
