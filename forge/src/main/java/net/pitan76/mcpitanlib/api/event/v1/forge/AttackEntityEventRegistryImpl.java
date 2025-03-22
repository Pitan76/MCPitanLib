package net.pitan76.mcpitanlib.api.event.v1.forge;

import me.shedaniel.architectury.event.EventResult;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.pitan76.mcpitanlib.api.event.v1.AttackEntityEventRegistry;

import java.util.ArrayList;
import java.util.List;

public class AttackEntityEventRegistryImpl {
    private static final List<AttackEntityEventRegistry.AttackEntity> attackEntities = new ArrayList<>();

    public static void register(AttackEntityEventRegistry.AttackEntity attackEntity) {
        attackEntities.add(attackEntity);
    }

    @SubscribeEvent
    public static void event(AttackEntityEvent event) {
        for (AttackEntityEventRegistry.AttackEntity attackEntity : attackEntities) {
            EventResult result = attackEntity.attack(event.getPlayer(), event.getPlayer().world, event.getTarget(), event.getPlayer().getActiveHand(), null);
            if (result == EventResult.interruptFalse()) {
                event.setCanceled(true);
            }
        }
    }
}
