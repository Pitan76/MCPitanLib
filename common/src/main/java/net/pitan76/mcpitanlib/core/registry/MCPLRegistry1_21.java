package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.component.ComponentType;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry1_21 {

    private final MCPLRegistry mcplr;

    public MCPLRegistry1_21(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
    }

    public void register() {

    }

    public RegistrySupplier<ComponentType<?>> registryDataComponentType(Identifier id, Supplier<ComponentType<?>> supplier) {
        return Registry.registryDataComponentType(id, supplier);
    }
}
