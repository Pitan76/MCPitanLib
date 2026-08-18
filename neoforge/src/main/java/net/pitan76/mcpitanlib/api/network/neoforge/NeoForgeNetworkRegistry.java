package net.pitan76.mcpitanlib.api.network.neoforge;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeoForgeNetworkRegistry {

    public static final List<Identifier> registeredS2C = new ArrayList<>();
    public static final List<Identifier> registeredC2S = new ArrayList<>();

    public static final Map<Identifier, ClientNetworking.ClientNetworkHandler> clientHandlers = new HashMap<>();
    public static final Map<Identifier, ServerNetworking.ServerNetworkHandler> serverHandlers = new HashMap<>();

    @SubscribeEvent
    public static void onRegisterPayloadHandler(RegisterPayloadHandlerEvent event) {
        // S2C (Client bound)
        for (Identifier id : registeredS2C) {
            final IPayloadRegistrar registrar = event.registrar(id.getNamespace());
            registrar.play(id, buf -> new RawPayload(id, buf), handler -> {
                handler.client((payload, context) -> {
                    PacketByteBuf buf = new PacketByteBuf(io.netty.buffer.Unpooled.wrappedBuffer(payload.bytes()));
                    MinecraftClient client = MinecraftClient.getInstance();
                    client.execute(() -> {
                        ClientNetworking.ClientNetworkHandler h = clientHandlers.get(id);
                        if (h != null) {
                            h.receive(client, client.player, buf);
                        }
                    });
                });
            });
        }

        // C2S (Server bound)
        for (Identifier id : registeredC2S) {
            final IPayloadRegistrar registrar = event.registrar(id.getNamespace());
            registrar.play(id, buf -> new RawPayload(id, buf), handler -> {
                handler.server((payload, context) -> {
                    PacketByteBuf buf = new PacketByteBuf(io.netty.buffer.Unpooled.wrappedBuffer(payload.bytes()));
                    ServerPlayerEntity player = (ServerPlayerEntity) context.player().orElse(null);
                    if (player == null) return;
                    player.server.execute(() -> {
                        ServerNetworking.ServerNetworkHandler h = serverHandlers.get(id);
                        if (h != null) {
                            h.receive(player.server, player, buf);
                        }
                    });
                });
            });
        }
    }
}
