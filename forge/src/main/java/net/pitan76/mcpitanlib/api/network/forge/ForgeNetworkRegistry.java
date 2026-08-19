package net.pitan76.mcpitanlib.api.network.forge;

import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ForgeNetworkRegistry {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL;

    public static final Map<Identifier, ClientNetworking.ClientNetworkHandler> clientHandlers = new ConcurrentHashMap<>();
    public static final Map<Identifier, ServerNetworking.ServerNetworkHandler> serverHandlers = new ConcurrentHashMap<>();

    static {
        CHANNEL = NetworkRegistry.newSimpleChannel(
                MCPitanLib.id("network"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        CHANNEL.messageBuilder(RawPacket.class, 0)
                .encoder(RawPacket::encode)
                .decoder(RawPacket::decode)
                .consumerNetworkThread((packet, contextSupplier) -> {
                    var context = contextSupplier.get();
                    context.enqueueWork(() -> {
                        Identifier id = packet.getId();
                        PacketByteBuf buf = new PacketByteBuf(Unpooled.wrappedBuffer(packet.getBytes()));
                        if (context.getDirection().getReceptionSide().isClient()) {
                            MinecraftClient client = MinecraftClient.getInstance();
                            ClientNetworking.ClientNetworkHandler handler = clientHandlers.get(id);
                            if (handler != null) {
                                handler.receive(client, client.player, buf);
                            }
                        } else {
                            ServerPlayerEntity player = context.getSender();
                            if (player != null) {
                                ServerNetworking.ServerNetworkHandler handler = serverHandlers.get(id);
                                if (handler != null) {
                                    handler.receive(player.server, player, buf);
                                }
                            }
                        }
                    });
                    context.setPacketHandled(true);
                    return true;
                })
                .add();
    }

    public static void init() {
        // Class loading initializes static block
    }
}
