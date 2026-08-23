package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import net.minecraft.world.level.material.Fluid;
import net.pitan76.mcpitanlib.midohra.fluid.FluidWrapper;

/**
 * 流体の出し入れだけを行う薄いビュー。
 * <p>
 * {@link FluidLookup} が他MODのタンクを返すときの型。中身の列挙はできない。
 * 自前のタンクを持ちたい場合は {@link IFluidStorage} を使うこと。
 */
public interface IFluidHandler {
    long insert(IFluidVariant variant, long maxAmount, boolean simulate);

    long extract(IFluidVariant variant, long maxAmount, boolean simulate);

    default boolean supportsInsertion() {
        return true;
    }

    default boolean supportsExtraction() {
        return true;
    }

    default long insert(IFluidVariant variant, long maxAmount) {
        return insert(variant, maxAmount, false);
    }

    default long extract(IFluidVariant variant, long maxAmount) {
        return extract(variant, maxAmount, false);
    }

    default long insert(Fluid fluid, long maxAmount) {
        return insert(FluidStorageUtil.getVariant(fluid), maxAmount);
    }

    default long extract(Fluid fluid, long maxAmount) {
        return extract(FluidStorageUtil.getVariant(fluid), maxAmount);
    }

    default long insert(FluidWrapper wrapper, long maxAmount) {
        return insert(wrapper.get(), maxAmount);
    }

    default long extract(FluidWrapper wrapper, long maxAmount) {
        return extract(wrapper.get(), maxAmount);
    }

    default boolean canInsert(IFluidVariant variant, long amount) {
        return insert(variant, amount, true) == amount;
    }

    default boolean canExtract(IFluidVariant variant, long amount) {
        return extract(variant, amount, true) == amount;
    }

    default boolean canInsert(Fluid fluid, long amount) {
        return canInsert(FluidStorageUtil.getVariant(fluid), amount);
    }

    default boolean canExtract(Fluid fluid, long amount) {
        return canExtract(FluidStorageUtil.getVariant(fluid), amount);
    }

    default boolean canInsert(FluidWrapper wrapper, long amount) {
        return canInsert(wrapper.get(), amount);
    }

    default boolean canExtract(FluidWrapper wrapper, long amount) {
        return canExtract(wrapper.get(), amount);
    }
}
