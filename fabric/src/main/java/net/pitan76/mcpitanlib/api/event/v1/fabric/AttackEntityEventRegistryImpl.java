package net.pitan76.mcpitanlib.api.event.v1.fabric;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.pitan76.mcpitanlib.api.event.v1.AttackEntityEventRegistry;

public class AttackEntityEventRegistryImpl {
    public static void register(AttackEntityEventRegistry.AttackEntity attackEntity) {
        AttackEntityCallback.EVENT.register(attackEntity::attack);
    }
}
