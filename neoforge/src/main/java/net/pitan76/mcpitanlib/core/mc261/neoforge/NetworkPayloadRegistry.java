package net.pitan76.mcpitanlib.core.mc261.neoforge;

import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registers every payload type exactly once.
 * Server-side and client-side handlers are collected here beforehand, because
 * registering the same payload id twice (once per side) throws in NeoForge.
 */
@EventBusSubscriber(modid = "mcpitanlib")
public class NetworkPayloadRegistry {

    static final Map<Identifier, BufPayload.Type<BufPayload>> payloadTypeMap = new ConcurrentHashMap<>();
    static final Map<Identifier, IPayloadHandler<BufPayload>> serverHandlerMap = new ConcurrentHashMap<>();
    static final Map<Identifier, IPayloadHandler<BufPayload>> clientHandlerMap = new ConcurrentHashMap<>();

    static BufPayload.Type<BufPayload> registerType(Identifier identifier) {
        return payloadTypeMap.computeIfAbsent(identifier, BufPayload::id);
    }

    private static final IPayloadHandler<BufPayload> NOOP = (payload, context) -> {};

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1").optional();

        payloadTypeMap.forEach((id, type) -> {
            IPayloadHandler<BufPayload> clientHandler = clientHandlerMap.getOrDefault(id, NOOP);
            IPayloadHandler<BufPayload> serverHandler = serverHandlerMap.getOrDefault(id, NOOP);

            registrar.playBidirectional(type, BufPayload.getCodec(type), clientHandler, serverHandler);
        });
    }
}
