package net.pitan76.mcpitanlib.midohra.entity;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierTypedEntityTypeWrapper<T extends Entity> extends TypedEntityTypeWrapper<T> implements INonTypedSupplier<SupplierEntityTypeWrapper> {
    private final Supplier<EntityType<T>> supplier;

    protected SupplierTypedEntityTypeWrapper(Supplier<EntityType<T>> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends Entity> SupplierTypedEntityTypeWrapper<T> of(Supplier<EntityType<T>> supplier) {
        return new SupplierTypedEntityTypeWrapper<>(supplier);
    }

    public static <T extends Entity> SupplierTypedEntityTypeWrapper<T> of(RegistryResult<EntityType<T>> result) {
        return new SupplierTypedEntityTypeWrapper<>(result::get);
    }

    public static <T extends Entity> SupplierTypedEntityTypeWrapper<T> of(RegistrySupplier<EntityType<T>> result) {
        return new SupplierTypedEntityTypeWrapper<>(result::get);
    }

    public static <T extends Entity> SupplierTypedEntityTypeWrapper<T> of(SupplierEntityTypeWrapper result) {
        return new SupplierTypedEntityTypeWrapper<>(() -> (EntityType<T>) result.get());
    }

    @Override
    public SupplierEntityTypeWrapper asNonTyped() {
        return SupplierEntityTypeWrapper.of(this::get);
    }

    @Override
    public EntityType<T> get() {
        return supplier.get();
    }
}
