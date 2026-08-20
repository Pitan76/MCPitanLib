package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.pitan76.mcpitanlib.api.event.v0.InteractionEventRegistry;
import net.pitan76.mcpitanlib.fabric.event.ClientPreAttackCallbacks;

public class ClientInteractionEventRegistry {
    @Environment(EnvType.CLIENT)
    public static void registerClientLeftClickAir(InteractionEventRegistry.ClientLeftClickAir clientLeftClickAir) {
        // Fabric APIの1.19.2版にはClientPreAttackCallbackが無いため、自前のミックスインで代替する
        ClientPreAttackCallbacks.register(player -> clientLeftClickAir.click(player, player.getActiveHand()));
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientRightClickAir(InteractionEventRegistry.ClientRightClickAir clientRightClickAir) {
        // Not supported directly via fabric-api on 1.19.2 without mixin
    }
}
