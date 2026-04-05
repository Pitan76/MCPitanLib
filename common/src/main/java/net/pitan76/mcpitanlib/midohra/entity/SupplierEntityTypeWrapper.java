package net.pitan76.mcpitanlib.midohra.entity;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.EntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import java.util.function.Supplier;

public class SupplierEntityTypeWrapper extends EntityTypeWrapper {
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
    public EntityType<?> get() {
        return supplier.get();
    }
}
