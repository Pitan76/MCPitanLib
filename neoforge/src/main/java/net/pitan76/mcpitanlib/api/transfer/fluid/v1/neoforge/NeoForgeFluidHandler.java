package net.pitan76.mcpitanlib.api.transfer.fluid.v1.neoforge;

import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidHandler;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

public class NeoForgeFluidHandler implements IFluidHandler {

    public final ResourceHandler<FluidResource> handler;

    public NeoForgeFluidHandler(ResourceHandler<FluidResource> handler) {
        this.handler = handler;
    }

    public ResourceHandler<FluidResource> getRaw() {
        return handler;
    }

    @Override
    public long insert(IFluidVariant variant, long maxAmount, boolean simulate) {
        try (Transaction transaction = Transaction.open(null)) {
            int inserted = handler.insert(toResource(variant), toInt(maxAmount), transaction);
            if (!simulate) transaction.commit();

            return inserted;
        }
    }

    @Override
    public long extract(IFluidVariant variant, long maxAmount, boolean simulate) {
        try (Transaction transaction = Transaction.open(null)) {
            int extracted = handler.extract(toResource(variant), toInt(maxAmount), transaction);
            if (!simulate) transaction.commit();

            return extracted;
        }
    }

    private static FluidResource toResource(IFluidVariant variant) {
        return FluidResource.of(variant.getFluid());
    }

    private static int toInt(long amount) {
        if (amount > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (amount < 0) return 0;

        return (int) amount;
    }
}
