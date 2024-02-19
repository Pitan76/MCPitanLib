package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry1_20 {
    @Deprecated
    public static final Map<Identifier, RegistrySupplier<ItemGroup>> REGISTRY_SUPPLIER_ITEM_GROUP_CACHE = new HashMap<>();

    private final MCPLRegistry mcplr;

    protected DeferredRegister<ItemGroup> ITEM_GROUP;

    public MCPLRegistry1_20(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
        ITEM_GROUP = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM_GROUP);
    }

    public void register() {
        ITEM_GROUP.register();
    }

    public RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        RegistrySupplier<ItemGroup> itemGroup = ITEM_GROUP.register(id, supplier);
        REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.put(id, itemGroup);
        return itemGroup;
    }

    public RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, CreativeTabBuilder builder) {
        RegistrySupplier<ItemGroup> itemGroup = ITEM_GROUP.register(id, builder::build);
        REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.put(id, itemGroup);
        return itemGroup;
    }
}
