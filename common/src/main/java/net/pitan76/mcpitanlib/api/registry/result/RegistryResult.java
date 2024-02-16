package net.pitan76.mcpitanlib.api.registry.result;

import dev.architectury.registry.registries.RegistrySupplier;
import org.jetbrains.annotations.Nullable;

/**
 * The result of a registry
 * @param <T> The type of the registry
 *
 * <pre>{@code
 * RegistryResult<Item> ITEM = registry.registerItem(new Identifier("mcpitanlib", "example"), ExampleItem::new);
 * ITEM.getOrNull(); => The item (Item.class) or null
 * ITEM.get(); => The item (Item.class)
 * }</pre>
 */
public class RegistryResult<T> {
    public RegistrySupplier<T> supplier;

    public RegistryResult(RegistrySupplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        return supplier.get();
    }

    @Nullable
    public T getOrNull() {
        return supplier.getOrNull();
    }
}
