package net.pitan76.mcpitanlib.api.network.forge;

import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ForgeNetworkRegistry {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL;

    public static final Map<Identifier, ClientNetworking.ClientNetworkHandler> clientHandlers = new ConcurrentHashMap<Identifier, ClientNetworking.ClientNetworkHandler>();
    public static final Map<Identifier, ServerNetworking.ServerNetworkHandler> serverHandlers = new ConcurrentHashMap<Identifier, ServerNetworking.ServerNetworkHandler>();

    static {
        CHANNEL = NetworkRegistry.newSimpleChannel(
                MCPitanLib.id("network"),
                new Supplier<String>() {
                    @Override
                    public String get() {
                        return PROTOCOL_VERSION;
                    }
                },
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        CHANNEL.messageBuilder(RawPacket.class, 0)
                .encoder(new BiConsumer<RawPacket, PacketByteBuf>() {
                    @Override
                    public void accept(RawPacket packet, PacketByteBuf buf) {
                        packet.encode(buf);
                    }
                })
                .decoder(RawPacket::decode)
                .consumer(new BiConsumer<RawPacket, Supplier<NetworkEvent.Context>>() {
                    @Override
                    public void accept(final RawPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
                        final NetworkEvent.Context context = contextSupplier.get();

                        context.enqueueWork(new Runnable() {
                            @Override
                            public void run() {
                                Identifier id = packet.getId();
                                PacketByteBuf buf = new PacketByteBuf(Unpooled.wrappedBuffer(packet.getBytes()));

                                if (context.getDirection().getReceptionSide().isClient()) {
                                    MinecraftClient client = MinecraftClient.getInstance();
                                    ClientNetworking.ClientNetworkHandler handler = clientHandlers.get(id);
                                    if (handler != null) handler.receive(client, client.player, buf);
                                } else {
                                    ServerPlayerEntity player = context.getSender();
                                    if (player == null) return;

                                    ServerNetworking.ServerNetworkHandler handler = serverHandlers.get(id);
                                    if (handler != null) handler.receive(player.server, player, buf);
                                }
                            }
                        });

                        context.setPacketHandled(true);
                    }
                })
                .add();
    }

    public static void init() {
        // 静的初期化子でチャンネルを登録する
    }
}
