package net.pitan76.mcpitanlib.core.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.TicketType;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.pitan76.mcpitanlib.midohra.world.chunk.ChunkTicketType;

import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry1_21 {


    private final MCPLRegistry mcplr;

    public MCPLRegistry1_21(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
    }

    public void register() {

    }

    public RegistrySupplier<DataComponentType<?>> registryDataComponentType(Identifier id, Supplier<DataComponentType<?>> supplier) {
        ResourceKey<DataComponentType<?>> key = ResourceKey.create(Registries.DATA_COMPONENT_TYPE, id);
        return new RegistrySupplier<>(Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, key, supplier.get()));
    }

    public Supplier<ChunkTicketType<?>> registryChunkTicketType(Identifier id, Supplier<ChunkTicketType<?>> supplier) {
        ResourceKey<TicketType> key = ResourceKey.create(Registries.TICKET_TYPE, id);
        return () -> ChunkTicketType.of(Registry.register(BuiltInRegistries.TICKET_TYPE, key, supplier.get().getRaw()));
    }
}
