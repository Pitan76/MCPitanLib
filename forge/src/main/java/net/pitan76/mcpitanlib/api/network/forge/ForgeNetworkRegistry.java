package net.pitan76.mcpitanlib.api.network.forge;

import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ForgeNetworkRegistry {
    private static final int PROTOCOL_VERSION = 1;
    public static final SimpleChannel CHANNEL;

    public static final Map<Identifier, ClientNetworking.ClientNetworkHandler> clientHandlers = new ConcurrentHashMap<>();
    public static final Map<Identifier, ServerNetworking.ServerNetworkHandler> serverHandlers = new ConcurrentHashMap<>();

    static {
        CHANNEL = ChannelBuilder.named(MCPitanLib.id("network"))
                .networkProtocolVersion(PROTOCOL_VERSION)
                .acceptedVersions((status, version) -> true)
                .simpleChannel();

        CHANNEL.messageBuilder(RawPacket.class, 0)
                .encoder(RawPacket::encode)
                .decoder(RawPacket::decode)
                .consumerMainThread((packet, context) -> {
                    Identifier id = packet.getId();
                    PacketByteBuf buf = new PacketByteBuf(Unpooled.wrappedBuffer(packet.getBytes()));
                    if (context.isClientSide()) {
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
                    context.setPacketHandled(true);
                })
                .add();
    }

    public static void init() {
        // Class loading initializes static block
    }
}
