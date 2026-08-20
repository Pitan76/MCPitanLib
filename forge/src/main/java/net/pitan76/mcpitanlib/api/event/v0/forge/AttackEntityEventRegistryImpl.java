package net.pitan76.mcpitanlib.api.event.v0.forge;

import net.minecraft.util.ActionResult;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v0.AttackEntityEventRegistry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class AttackEntityEventRegistryImpl {
    private static final List<AttackEntityEventRegistry.AttackEntity> handlers = new CopyOnWriteArrayList<AttackEntityEventRegistry.AttackEntity>();

    public static void register(AttackEntityEventRegistry.AttackEntity attackEntity) {
        handlers.add(attackEntity);
    }

    @SubscribeEvent
    public static void event(AttackEntityEvent event) {
        for (AttackEntityEventRegistry.AttackEntity handler : handlers) {
            ActionResult result = handler.attack(event.getPlayer(), event.getPlayer().world, event.getTarget(), event.getPlayer().getActiveHand(), null);
            if (result == ActionResult.FAIL) {
                event.setCanceled(true);
                return;
            }
        }
    }
}
