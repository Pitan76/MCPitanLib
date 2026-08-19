package net.pitan76.mcpitanlib.api.event.v1.fabric;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.pitan76.mcpitanlib.api.event.v1.LivingHurtEventRegistry;

public class LivingHurtEventRegistryImpl {
    public static void register(LivingHurtEventRegistry.LivingHurt livingHurt) {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(livingHurt::hurt);
    }
}
