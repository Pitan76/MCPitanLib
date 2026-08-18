package net.pitan76.mcpitanlib.core.network.neoforge;

import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * NeoForgeではペイロードの登録をRegisterPayloadHandlersEventでまとめて行う必要があるため、
 * クライアント/サーバー両方の登録をここへ集約して、同じIDを二重登録しないようにする。
 */
@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class PayloadRegistry {

    private static final Map<Identifier, CustomPayload.Id<BufPayload>> TYPES = new LinkedHashMap<>();
    private static final Map<Identifier, IPayloadHandler<BufPayload>> CLIENT_HANDLERS = new LinkedHashMap<>();
    private static final Map<Identifier, IPayloadHandler<BufPayload>> SERVER_HANDLERS = new LinkedHashMap<>();

    private static final IPayloadHandler<BufPayload> NOOP = (payload, context) -> {};

    public static void registerType(Identifier identifier) {
        TYPES.computeIfAbsent(identifier, BufPayload::id);
    }

    public static void registerClientHandler(Identifier identifier, IPayloadHandler<BufPayload> handler) {
        registerType(identifier);
        CLIENT_HANDLERS.put(identifier, handler);
    }

    public static void registerServerHandler(Identifier identifier, IPayloadHandler<BufPayload> handler) {
        registerType(identifier);
        SERVER_HANDLERS.put(identifier, handler);
    }

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        for (Map.Entry<Identifier, CustomPayload.Id<BufPayload>> entry : TYPES.entrySet()) {
            CustomPayload.Id<BufPayload> id = entry.getValue();

            registrar.playBidirectional(id, BufPayload.getCodec(id),
                    CLIENT_HANDLERS.getOrDefault(entry.getKey(), NOOP),
                    SERVER_HANDLERS.getOrDefault(entry.getKey(), NOOP));
        }
    }
}
