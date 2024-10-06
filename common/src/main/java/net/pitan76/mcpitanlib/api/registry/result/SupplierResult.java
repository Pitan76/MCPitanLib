package net.pitan76.mcpitanlib.api.registry.result;

import me.shedaniel.architectury.registry.RegistrySupplier;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class SupplierResult<T> extends RegistryResult<T> {
    protected Supplier<T> supplier;

    protected SupplierResult(Supplier<T> supplier) {
        super(null);
        this.supplier = supplier;
    }

    protected SupplierResult(RegistrySupplier<T> supplier) {
        this(supplier::getOrNull);
    }

    protected SupplierResult(RegistryResult<T> result) {
        this(result.supplier);
    }

    public static <T> SupplierResult<T> of(Supplier<T> supplier) {
        return new SupplierResult<>(supplier);
    }

    public static <T> SupplierResult<T> of(RegistrySupplier<T> supplier) {
        return new SupplierResult<>(supplier);
    }

    public static <T> SupplierResult<T> of(RegistryResult<T> result) {
        return new SupplierResult<>(result);
    }

    @Override
    public T get() {
        return supplier.get();
    }

    @Override
    public @Nullable T getOrNull() {
        if (supplier == null)
            return null;
        return supplier.get();
    }

    public Optional<T> getOptional() {
        return Optional.ofNullable(getOrNull());
    }
}
