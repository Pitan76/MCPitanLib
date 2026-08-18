package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.pitan76.mcpitanlib.api.event.v0.AttackEntityEventRegistry;

public class AttackEntityEventRegistryImpl {
    public static void register(AttackEntityEventRegistry.AttackEntity attackEntity) {
        AttackEntityCallback.EVENT.register(attackEntity::attack);
    }
}
