package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.pitan76.mcpitanlib.api.event.v0.InteractionEventRegistry;

public class ClientInteractionEventRegistry {
    @Environment(EnvType.CLIENT)
    public static void registerClientLeftClickAir(InteractionEventRegistry.ClientLeftClickAir clientLeftClickAir) {
        net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback.EVENT.register((_, player, _) -> {
            clientLeftClickAir.click(player, player.getUsedItemHand());
            return false;
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientRightClickAir(InteractionEventRegistry.ClientRightClickAir clientRightClickAir) {
        net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents.AFTER.register((_, player, _, _)
                -> clientRightClickAir.click(player, player.getUsedItemHand()));
    }
}
