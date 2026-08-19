package net.pitan76.mcpitanlib.api.network.neoforge;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.neoforged.neoforge.network.PacketDistributor;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

public class ServerNetworkingImpl {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        PacketDistributor.PLAYER.with(player).send(new RawPayload(identifier, buf));
    }

    public static void registerReceiver(Identifier identifier, ServerNetworking.ServerNetworkHandler handler) {
        NeoForgeNetworkRegistry.registeredC2S.add(identifier);
        NeoForgeNetworkRegistry.serverHandlers.put(identifier, handler);
    }
}
