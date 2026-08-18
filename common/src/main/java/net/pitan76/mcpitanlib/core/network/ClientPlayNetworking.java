package net.pitan76.mcpitanlib.core.network;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;

public class ClientPlayNetworking {
    @ExpectPlatform
    public static void send(CustomPayload payload) {

    }

    @ExpectPlatform
    public static void registerGlobalReceiver(CustomPayload.Id<BufPayload> id, ClientNetworking.ClientNetworkHandler handler) {

    }

    @ExpectPlatform
    public static void registerC2SPayloadType(Identifier identifier) {

    }
}
