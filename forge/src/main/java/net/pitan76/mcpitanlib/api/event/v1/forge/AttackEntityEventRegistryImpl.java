package net.pitan76.mcpitanlib.api.event.v1.forge;

import net.minecraft.util.ActionResult;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.pitan76.mcpitanlib.api.event.v1.AttackEntityEventRegistry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AttackEntityEventRegistryImpl {
    private static final List<AttackEntityEventRegistry.AttackEntity> attackEntities = new CopyOnWriteArrayList<AttackEntityEventRegistry.AttackEntity>();

    public static void register(AttackEntityEventRegistry.AttackEntity attackEntity) {
        attackEntities.add(attackEntity);
    }

    @SubscribeEvent
    public static void event(AttackEntityEvent event) {
        for (AttackEntityEventRegistry.AttackEntity attackEntity : attackEntities) {
            ActionResult result = attackEntity.attack(event.getPlayer(), event.getPlayer().world, event.getTarget(), event.getPlayer().getActiveHand(), null);
            if (result == ActionResult.FAIL) {
                event.setCanceled(true);
            }
        }
    }
}
