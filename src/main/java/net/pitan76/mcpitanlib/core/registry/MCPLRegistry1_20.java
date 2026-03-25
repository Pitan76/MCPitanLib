package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry1_20 {
    @Deprecated
    public static final Map<Identifier, RegistrySupplier<CreativeModeTab>> REGISTRY_SUPPLIER_ITEM_GROUP_CACHE = new HashMap<>();

    private final MCPLRegistry mcplr;

    protected DeferredRegister<CreativeModeTab> ITEM_GROUP;

    public MCPLRegistry1_20(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
        ITEM_GROUP = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);
    }

    public void register() {
        ITEM_GROUP.register();
    }

    public RegistrySupplier<CreativeModeTab> registryItemGroup(Identifier id, Supplier<CreativeModeTab> supplier) {
        RegistrySupplier<CreativeModeTab> itemGroup = ITEM_GROUP.register(id, supplier);
        REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.put(id, itemGroup);
        return itemGroup;
    }

    public RegistrySupplier<CreativeModeTab> registryItemGroup(Identifier id, CreativeTabBuilder builder) {
        RegistrySupplier<CreativeModeTab> itemGroup = ITEM_GROUP.register(id, builder::build);
        REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.put(id, itemGroup);
        return itemGroup;
    }
}
