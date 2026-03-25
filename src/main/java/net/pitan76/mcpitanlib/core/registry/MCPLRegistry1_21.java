package net.pitan76.mcpitanlib.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.midohra.world.chunk.ChunkTicketType;

import java.util.function.Supplier;

@Deprecated
public class MCPLRegistry1_21 {

    public final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPE;
    public final DeferredRegister<net.minecraft.server.level.TicketType> TICKET_TYPE;

    private final MCPLRegistry mcplr;

    public MCPLRegistry1_21(MCPLRegistry mcplr, String MOD_ID) {
        this.mcplr = mcplr;
        DATA_COMPONENT_TYPE = DeferredRegister.create(MOD_ID, Registries.DATA_COMPONENT_TYPE);
        TICKET_TYPE = DeferredRegister.create(MOD_ID, Registries.TICKET_TYPE);
    }

    public void register() {
        DATA_COMPONENT_TYPE.register();
        TICKET_TYPE.register();
    }

    public RegistrySupplier<DataComponentType<?>> registryDataComponentType(Identifier id, Supplier<DataComponentType<?>> supplier) {
        return DATA_COMPONENT_TYPE.register(id, supplier);
    }

    public Supplier<ChunkTicketType<?>> registryChunkTicketType(Identifier id, Supplier<ChunkTicketType<?>> supplier) {
        RegistrySupplier<net.minecraft.server.level.TicketType> ticketType = TICKET_TYPE.register(id, () -> supplier.get().getRaw());
        return () -> ChunkTicketType.of(ticketType.get());
    }
}
