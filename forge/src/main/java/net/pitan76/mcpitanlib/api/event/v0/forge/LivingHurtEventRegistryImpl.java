package net.pitan76.mcpitanlib.api.event.v0.forge;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v0.LivingHurtEventRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class LivingHurtEventRegistryImpl {

    private static final List<LivingHurtEventRegistry.LivingHurt> listeners = new CopyOnWriteArrayList<>();

    public static void register(LivingHurtEventRegistry.LivingHurt livingHurt) {
        listeners.add(livingHurt);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntityLiving().getWorld().isClient()) return;

        for (LivingHurtEventRegistry.LivingHurt listener : listeners) {
            boolean allow = listener.hurt(event.getEntityLiving(), event.getSource(), event.getAmount());

            if (!allow) {
                event.setCanceled(true);
                return;
            }
        }
    }
}

