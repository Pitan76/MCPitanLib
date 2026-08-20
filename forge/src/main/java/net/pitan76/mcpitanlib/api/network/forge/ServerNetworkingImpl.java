package net.pitan76.mcpitanlib.api.network.forge;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.network.PacketDistributor;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

public class ServerNetworkingImpl {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        ForgeNetworkRegistry.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new RawPacket(identifier, buf));
    }

    public static void registerReceiver(Identifier identifier, ServerNetworking.ServerNetworkHandler handler) {
        ForgeNetworkRegistry.serverHandlers.put(identifier, handler);
    }
}
