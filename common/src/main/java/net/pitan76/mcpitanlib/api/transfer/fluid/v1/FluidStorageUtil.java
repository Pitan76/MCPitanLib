package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluid;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class FluidStorageUtil {
    @ExpectPlatform
    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static IFluidVariant getVariant(Fluid fluid) {
        throw new AssertionError();
    }

    /**
     * バケツ1杯分の量。
     * Fabricはdroplet (81000)、NeoForgeはmB (1000) で単位が異なるため、
     * 流体量はこの値を基準に計算すること。
     * @return バケツ1杯分の量
     */
    @ExpectPlatform
    public static long bucketAmount() {
        throw new AssertionError();
    }

    /**
     * バケツn杯分の量。
     * @param buckets バケツの数
     * @return 量
     */
    public static long buckets(double buckets) {
        return (long) (bucketAmount() * buckets);
    }

    /**
     * 指定した位置の流体タンクを取得する。他MODの機械も対象になる。
     * @param side 面 (nullで面を指定しない)
     * @return 流体タンク (存在しない場合はnull)
     */
    @ExpectPlatform
    @Nullable
    public static IFluidHandler getFluidHandler(World world, BlockPos pos, @Nullable Direction side) {
        throw new AssertionError();
    }

    /**
     * BlockEntityTypeに流体タンクを紐付ける。
     * 渡せるのは {@link #withFixedCapacity} で作ったタンクのみ。
     */
    @ExpectPlatform
    public static void registerFluidStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        throw new AssertionError();
    }

    @Nullable
    public static IFluidHandler getFluidHandler(World world, BlockPos pos) {
        return getFluidHandler(world, pos, null);
    }

    @Nullable
    public static IFluidHandler getFluidHandler(BlockEntity blockEntity, @Nullable Direction side) {
        if (blockEntity == null || blockEntity.getWorld() == null) return null;
        return getFluidHandler(blockEntity.getWorld(), blockEntity.getPos(), side);
    }
}
