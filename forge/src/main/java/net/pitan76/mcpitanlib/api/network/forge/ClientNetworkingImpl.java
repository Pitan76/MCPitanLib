package net.pitan76.mcpitanlib.api.network.forge;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraftforge.network.PacketDistributor;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;

public class ClientNetworkingImpl {
    public static void send(Identifier identifier, PacketByteBuf buf) {
        ForgeNetworkRegistry.CHANNEL.send(new RawPacket(identifier, buf), PacketDistributor.SERVER.noArg());
    }

    public static void registerReceiver(Identifier identifier, ClientNetworking.ClientNetworkHandler handler) {
        ForgeNetworkRegistry.clientHandlers.put(identifier, handler);
    }
}
