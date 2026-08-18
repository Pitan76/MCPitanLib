package net.pitan76.mcpitanlib.midohra.entity;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.entity.Entity;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierEntityWrapper extends EntityWrapper implements INonTypedSupplier<SupplierEntityWrapper> {
    private final Supplier<Entity> supplier;

    protected SupplierEntityWrapper(Supplier<Entity> supplier) {
        this.supplier = supplier;
    }

    public static SupplierEntityWrapper of(Supplier<Entity> supplier) {
        return new SupplierEntityWrapper(supplier);
    }

    public static SupplierEntityWrapper of(RegistryResult<Entity> result) {
        return new SupplierEntityWrapper(result::get);
    }

    public static SupplierEntityWrapper of(RegistrySupplier<Entity> result) {
        return new SupplierEntityWrapper(result::get);
    }

    @Override
    public SupplierEntityWrapper asNonTyped() {
        return this;
    }

    @Override
    public Entity get() {
        return supplier.get();
    }
}
