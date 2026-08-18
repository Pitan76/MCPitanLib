package net.pitan76.mcpitanlib.core.mc261.neoforge;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = "mcpitanlib")
public class ServerPlayNetworkingImpl {

    public static void send(ServerPlayer player, CustomPacketPayload payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    private static final Map<BufPayload.Type<BufPayload>, IPayloadHandler<BufPayload>> handlerMap = new ConcurrentHashMap<>();
    private static final Map<Identifier, BufPayload.Type<BufPayload>> payloadTypeMap = new ConcurrentHashMap<>();

    public static void registerGlobalReceiver(CustomPacketPayload.Type<BufPayload> id, ServerNetworking.ServerNetworkHandler handler) {
        handlerMap.put(id, (payload, context) -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));

            ServerPlayer player = null;
            MinecraftServer server = null;
            if (context.player() instanceof ServerPlayer serverPlayer) {
                player = serverPlayer;
                server = serverPlayer.level().getServer();
            }

            handler.receive(server, player, buf);

            buf.release();
        });
    }

    public static void registerS2CPayloadType(Identifier identifier) {
        if (payloadTypeMap.containsKey(identifier)) return;
        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);
        payloadTypeMap.put(identifier, id);
    }

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        handlerMap.forEach((type, handler) -> {
            registrar.playToServer(type, BufPayload.getCodec(type), handler);
        });

        // ダミー
//        payloadTypeMap.forEach((_, type) -> {
//            if (!handlerMap.containsKey(type)) {
//                registrar.playToClient(type, BufPayload.getCodec(type), (_, _) -> {});
//            }
//        });
    }
}