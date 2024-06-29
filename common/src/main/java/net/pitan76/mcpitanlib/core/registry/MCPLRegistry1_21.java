package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry1_21 {

    public final DeferredRegister<ComponentType<?>> DATA_COMPONENT_TYPE;

    private final MCPLRegistry mcplr;

    public MCPLRegistry1_21(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
        DATA_COMPONENT_TYPE = DeferredRegister.create(MOD_ID, RegistryKeys.DATA_COMPONENT_TYPE);
    }

    public void register() {
        DATA_COMPONENT_TYPE.register();
    }

    public RegistrySupplier<ComponentType<?>> registryDataComponentType(Identifier id, Supplier<ComponentType<?>> supplier) {
        return DATA_COMPONENT_TYPE.register(id, supplier);
    }
}
