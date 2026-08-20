package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.pitan76.mcpitanlib.api.event.v0.LivingHurtEventRegistry;
import net.pitan76.mcpitanlib.fabric.event.LivingHurtCallbacks;

public class LivingHurtEventRegistryImpl {
    public static void register(LivingHurtEventRegistry.LivingHurt livingHurt) {
        LivingHurtCallbacks.register(livingHurt::hurt);
    }
}
