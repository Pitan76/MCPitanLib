package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.ItemGroup;
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

    //protected DeferredRegister<ItemGroup> ITEM_GROUP;

    public MCPLRegistry1_20(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
        //ITEM_GROUP = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM_GROUP);
    }

    public RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, Supplier<ItemGroup> supplier) {
        return null;
    }

    public RegistrySupplier<ItemGroup> registryItemGroup(Identifier id, CreativeTabBuilder builder) {
        return null;
    }
}
