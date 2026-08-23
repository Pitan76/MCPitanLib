package net.pitan76.mcpitanlib.core.mc261.neoforge;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = "mcpitanlib", value = Dist.CLIENT)
public class ClientPlayNetworkingImpl {
    public static void send(CustomPacketPayload payload) {
        ClientPacketDistributor.sendToServer(payload);
    }

    private static final Map<BufPayload.Type<BufPayload>, IPayloadHandler<BufPayload>> handlerMap = new ConcurrentHashMap<>();

    public static void registerGlobalReceiver(BufPayload.Type<BufPayload> type, ClientNetworking.ClientNetworkHandler handler) {
        handlerMap.put(type, (payload, context) -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));

            LocalPlayer player = null;
            if (context.player() instanceof LocalPlayer)
                player = (LocalPlayer) context.player();

            handler.receive(Minecraft.getInstance(), player, buf);
            buf.release();
        });
    }

    private static final Map<Identifier, BufPayload.Type<BufPayload>> payloadTypeMap = new ConcurrentHashMap<>();


    public static void registerC2SPayloadType(Identifier identifier) {
        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);
        if (payloadTypeMap.containsKey(identifier)) return;
        payloadTypeMap.put(identifier, id);
    }

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1").optional();

        Map<Identifier, BufPayload.Type<BufPayload>> types = new HashMap<>(payloadTypeMap);
        handlerMap.keySet().forEach(type -> types.putIfAbsent(type.id(), type));

        types.forEach((id, type) -> {
            IPayloadHandler<BufPayload> handler = handlerMap.get(type);
            if (handler == null) handler = (payload, context) -> {};

            registrar.playBidirectional(type, BufPayload.getCodec(type), (payload, context) -> {}, handler);
        });
    }
}
