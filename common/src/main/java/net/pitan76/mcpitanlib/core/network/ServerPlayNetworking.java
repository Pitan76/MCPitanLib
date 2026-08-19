package net.pitan76.mcpitanlib.core.network;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

public class ServerPlayNetworking {
    @ExpectPlatform
    public static void send(ServerPlayerEntity player, CustomPayload payload) {

    }

    @ExpectPlatform
    public static void registerGlobalReceiver(CustomPayload.Id<BufPayload> id, ServerNetworking.ServerNetworkHandler handler) {

    }

    @ExpectPlatform
    public static void registerS2CPayloadType(Identifier identifier) {

    }
}
