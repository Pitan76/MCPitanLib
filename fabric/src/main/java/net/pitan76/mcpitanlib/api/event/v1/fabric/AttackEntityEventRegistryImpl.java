package net.pitan76.mcpitanlib.api.event.v1.fabric;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.pitan76.mcpitanlib.api.event.v1.AttackEntityEventRegistry;
import net.pitan76.mcpitanlib.api.event.v1.event.AttackEntityEvent;

public class AttackEntityEventRegistryImpl {
    public static void register(AttackEntityEventRegistry.AttackEntity attackEntity) {
        AttackEntityCallback.EVENT.register((player, world, hand, target, result) ->
                attackEntity.attack(new AttackEntityEvent(player, world, target, hand, result)).toActionResult());
    }
}
