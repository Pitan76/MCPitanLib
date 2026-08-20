package net.pitan76.mcpitanlib.midohra.entity;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.entity.EntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierEntityTypeWrapper extends EntityTypeWrapper implements INonTypedSupplier<SupplierEntityTypeWrapper> {
    private final Supplier<EntityType<?>> supplier;

    protected SupplierEntityTypeWrapper(Supplier<EntityType<?>> supplier) {
        this.supplier = supplier;
    }

    public static SupplierEntityTypeWrapper of(Supplier<EntityType<?>> supplier) {
        return new SupplierEntityTypeWrapper(supplier);
    }

    public static SupplierEntityTypeWrapper of(RegistryResult<EntityType<?>> result) {
        return new SupplierEntityTypeWrapper(result::get);
    }

    public static SupplierEntityTypeWrapper of(RegistrySupplier<EntityType<?>> result) {
        return new SupplierEntityTypeWrapper(result::get);
    }

    @Override
    public SupplierEntityTypeWrapper asNonTyped() {
        return this;
    }

    @Override
    public EntityType<?> get() {
        return supplier.get();
    }
}
