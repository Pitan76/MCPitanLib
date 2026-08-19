package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.pitan76.mcpitanlib.api.event.v0.InteractionEventRegistry;

public class ClientInteractionEventRegistry {
    @Environment(EnvType.CLIENT)
    public static void registerClientLeftClickAir(InteractionEventRegistry.ClientLeftClickAir clientLeftClickAir) {
        ClientPreAttackCallback.EVENT.register((client, player, clickCount) -> {
            clientLeftClickAir.click(player, player.getActiveHand());
            return false;
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientRightClickAir(InteractionEventRegistry.ClientRightClickAir clientRightClickAir) {
        // Not supported directly via fabric-api on 1.20.1 without mixin
    }
}
