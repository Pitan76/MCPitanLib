package net.pitan76.mcpitanlib.api.transfer.fluid.v1.neoforge;

import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class NeoForgeFluidHandler implements net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidHandler {

    public final IFluidHandler handler;

    public NeoForgeFluidHandler(IFluidHandler handler) {
        this.handler = handler;
    }

    public IFluidHandler getRaw() {
        return handler;
    }

    @Override
    public long insert(IFluidVariant variant, long maxAmount, boolean simulate) {
        return handler.fill(toStack(variant, maxAmount), toAction(simulate));
    }

    @Override
    public long extract(IFluidVariant variant, long maxAmount, boolean simulate) {
        return handler.drain(toStack(variant, maxAmount), toAction(simulate)).getAmount();
    }

    private static FluidStack toStack(IFluidVariant variant, long amount) {
        return new FluidStack(variant.getFluid(), toInt(amount));
    }

    private static IFluidHandler.FluidAction toAction(boolean simulate) {
        return simulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE;
    }

    private static int toInt(long amount) {
        if (amount > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (amount < 0) return 0;

        return (int) amount;
    }
}
