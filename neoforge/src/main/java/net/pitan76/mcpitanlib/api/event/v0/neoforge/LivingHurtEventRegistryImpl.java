package net.pitan76.mcpitanlib.api.event.v0.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v0.LivingHurtEventRegistry;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class LivingHurtEventRegistryImpl {

    private static final List<LivingHurtEventRegistry.LivingHurt> listeners = new ArrayList<>();

    public static void register(LivingHurtEventRegistry.LivingHurt livingHurt) {
        listeners.add(livingHurt);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        if (event.getEntity().getEntityWorld().isClient()) return;

        for (LivingHurtEventRegistry.LivingHurt listener : listeners) {
            boolean allow = listener.hurt(event.getEntity(), event.getSource(), event.getAmount());

            if (!allow) {
                event.setCanceled(true);
                return;
            }
        }
    }
}
