package net.pitan76.mcpitanlib.api.event.v1.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.pitan76.mcpitanlib.api.event.v1.LivingHurtEventRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = "mcpitanlib")
public class LivingHurtEventRegistryImpl {

    private static final List<LivingHurtEventRegistry.LivingHurt> listeners = new CopyOnWriteArrayList<>();

    public static void register(LivingHurtEventRegistry.LivingHurt livingHurt) {
        listeners.add(livingHurt);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {

        if (!event.getEntity().level().isClientSide()) {
            for (LivingHurtEventRegistry.LivingHurt listener : listeners) {
                boolean allow = listener.hurt(
                        event.getEntity(),
                        event.getSource(),
                        event.getAmount()
                );

                if (!allow) {
                    event.setCanceled(true);
                    return;
                }
            }

        }
    }
}