package net.pitan76.mcpitanlib.api.network.forge;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.network.PacketDistributor;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

import java.util.function.Supplier;

public class ServerNetworkingImpl {
    public static void send(final ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        ForgeNetworkRegistry.CHANNEL.send(PacketDistributor.PLAYER.with(new Supplier<ServerPlayerEntity>() {
            @Override
            public ServerPlayerEntity get() {
                return player;
            }
        }), new RawPacket(identifier, buf));
    }

    public static void registerReceiver(Identifier identifier, ServerNetworking.ServerNetworkHandler handler) {
        ForgeNetworkRegistry.init();
        ForgeNetworkRegistry.serverHandlers.put(identifier, handler);
    }
}
