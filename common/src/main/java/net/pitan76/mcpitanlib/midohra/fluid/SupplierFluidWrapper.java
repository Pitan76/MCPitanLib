package net.pitan76.mcpitanlib.midohra.fluid;

import net.minecraft.fluid.Fluid;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.function.Supplier;

/**
 * 登録が後から解決される場合に使う。Forge / NeoForge では初期化時点でまだ実体が無い。
 */
public class SupplierFluidWrapper extends FluidWrapper {

    private final Supplier<Fluid> supplier;

    protected SupplierFluidWrapper(Supplier<Fluid> supplier) {
        this.supplier = supplier;
    }

    public static SupplierFluidWrapper of(Supplier<Fluid> supplier) {
        return new SupplierFluidWrapper(supplier);
    }

    public static SupplierFluidWrapper of(RegistryResult<Fluid> result) {
        return of(result::get);
    }

    public static SupplierFluidWrapper of(RegistrySupplier<Fluid> result) {
        return of(result::get);
    }

    @Override
    public Fluid get() {
        return supplier.get();
    }
}
