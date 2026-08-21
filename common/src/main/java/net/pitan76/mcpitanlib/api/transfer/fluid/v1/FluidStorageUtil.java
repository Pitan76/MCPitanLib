package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.fluid.Fluid;

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
}
