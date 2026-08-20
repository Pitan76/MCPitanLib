package net.pitan76.mcpitanlib.api.event.v1.forge;

import net.minecraft.util.ActionResult;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v1.LivingHurtEventRegistry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class LivingHurtEventRegistryImpl {
    private static final List<LivingHurtEventRegistry.LivingHurt> handlers = new CopyOnWriteArrayList<LivingHurtEventRegistry.LivingHurt>();

    public static void register(LivingHurtEventRegistry.LivingHurt livingHurt) {
        handlers.add(livingHurt);
    }

    @SubscribeEvent
    public static void event(LivingAttackEvent event) {
        for (LivingHurtEventRegistry.LivingHurt handler : handlers) {
            ActionResult result = handler.hurt(event.getEntityLiving(), event.getSource(), event.getAmount());
            if (result == ActionResult.FAIL) {
                event.setCanceled(true);
                return;
            }
        }
    }
}
