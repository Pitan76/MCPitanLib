package net.pitan76.mcpitanlib.api.network.neoforge;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.neoforged.neoforge.network.PacketDistributor;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;

public class ClientNetworkingImpl {
    public static void send(Identifier identifier, PacketByteBuf buf) {
        PacketDistributor.SERVER.noArg().send(new RawPayload(identifier, buf));
    }

    public static void registerReceiver(Identifier identifier, ClientNetworking.ClientNetworkHandler handler) {
        NeoForgeNetworkRegistry.registeredS2C.add(identifier);
        NeoForgeNetworkRegistry.clientHandlers.put(identifier, handler);
    }
}
