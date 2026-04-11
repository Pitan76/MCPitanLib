package net.pitan76.mcpitanlib.core.mc261;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

public class ClientPlayNetworking {
    @ExpectPlatform
    public static void send(CustomPacketPayload payload) {
    }

    @ExpectPlatform
    public static void registerGlobalReceiver(BufPayload.Type<BufPayload> type, ClientNetworking.ClientNetworkHandler handler) {

    }

    @ExpectPlatform
    public static void registerC2SPayloadType(Identifier identifier) {

    }
}
