package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry1_20 {
    @Deprecated
    public static final Map<Identifier, RegistrySupplier<ItemGroup>> REGISTRY_SUPPLIER_ITEM_GROUP_CACHE = new HashMap<>();

    private final MCPLRegistry mcplr;

    public MCPLRegistry1_20(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
    }

    public void register() {

    }

    public RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        RegistrySupplier<ItemGroup> itemGroup = Registry.registryItemGroup(id, supplier);
        REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.put(id, itemGroup);
        return itemGroup;
    }

    public RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, CreativeTabBuilder builder) {
        return registryItemGroup(id, builder::build);
    }
}
