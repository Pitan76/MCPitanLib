package net.pitan76.mcpitanlib.midohra.fluid;

import net.minecraft.fluid.FluidState;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.FluidStateUtil;
import net.pitan76.mcpitanlib.api.util.FluidUtil;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import org.jetbrains.annotations.Nullable;

public class FluidWrapper {
    private final net.minecraft.fluid.Fluid fluid;

    protected FluidWrapper() {
        this.fluid = null;
    }

    protected FluidWrapper(net.minecraft.fluid.Fluid fluid) {
        this.fluid = fluid;
    }

    public static FluidWrapper of(net.minecraft.fluid.Fluid fluid) {
        return new FluidWrapper(fluid);
    }

    public static FluidWrapper of() {
        return new FluidWrapper();
    }

    public static FluidWrapper of(CompatIdentifier id) {
        if (FluidUtil.isExist(id))
            return of(FluidUtil.fromId(id));

        return of();
    }

    public static FluidWrapper of(CompatIdentifier id, CompatIdentifier... ids) {
        if (of(id).isExist()) return of(id);

        for (CompatIdentifier id1 : ids) {
            if (of(id1).isExist())
                return of(id1);
        }

        return of();
    }

    public boolean isExist() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return get() == null;
    }

    @Nullable
    public net.minecraft.fluid.Fluid get() {
        return fluid;
    }

    public net.minecraft.fluid.Fluid gerOrDefault(net.minecraft.fluid.Fluid defaultFluid) {
        return isEmpty() ? defaultFluid : get();
    }

    public CompatIdentifier getId() {
        if (isEmpty())
            return CompatIdentifier.empty();

        return FluidUtil.toCompatId(get());
    }

    public String getName() {
        if (isEmpty()) return "";
        return get().toString();
    }

    public FluidState getDefaultState() {
        return FluidStateUtil.getDefaultState(get());
    }

    public ItemWrapper getBucketItem() {
        return ItemWrapper.of(FluidUtil.getBucketItem(get()));
    }

    public boolean rawEquals(FluidWrapper fluid) {
        return get() == fluid.get();
    }
}
