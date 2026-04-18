package net.pitan76.mcpitanlib.core.mc261;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

public class ServerPlayNetworking {
    @ExpectPlatform
    public static void send(ServerPlayer player, CustomPacketPayload payload) {

    }

    @ExpectPlatform
    public static void registerGlobalReceiver(CustomPacketPayload.Type<BufPayload> id, ServerNetworking.ServerNetworkHandler handler) {

    }

    @ExpectPlatform
    public static void registerS2CPayloadType(Identifier identifier) {

    }
}
