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
import java.util.Collections;

/**
 * NeoForgeではペイロードの登録をRegisterPayloadHandlersEventでまとめて行う必要があるため、
 * クライアント/サーバー両方の登録をここへ集約して、同じIDを二重登録しないようにする。
 */
@EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class PayloadRegistry {

    private static final Map<Identifier, CustomPayload.Id<BufPayload>> TYPES = Collections.synchronizedMap(new LinkedHashMap<>());
    private static final Map<Identifier, IPayloadHandler<BufPayload>> CLIENT_HANDLERS = Collections.synchronizedMap(new LinkedHashMap<>());
    private static final Map<Identifier, IPayloadHandler<BufPayload>> SERVER_HANDLERS = Collections.synchronizedMap(new LinkedHashMap<>());

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

            IPayloadHandler<BufPayload> clientHandler = CLIENT_HANDLERS.getOrDefault(entry.getKey(), NOOP);
            IPayloadHandler<BufPayload> serverHandler = SERVER_HANDLERS.getOrDefault(entry.getKey(), NOOP);

            // このバージョンのplayBidirectionalはハンドラを1つしか受け取らないため、受信方向で振り分ける
            registrar.playBidirectional(id, BufPayload.getCodec(id), (payload, context) -> {
                if (context.flow().isClientbound()) clientHandler.handle(payload, context);
                else serverHandler.handle(payload, context);
            });
        }
    }
}
