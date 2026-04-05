package net.pitan76.mcpitanlib.midohra.entity;

import me.shedaniel.architectury.registry.RegistrySupplier;
import net.minecraft.entity.Entity;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import java.util.function.Supplier;

public class SupplierEntityWrapper extends EntityWrapper {
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
    public Entity get() {
        return supplier.get();
    }
}
