package net.pitan76.mcpitanlib.api.event.v1.neoforge;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.pitan76.mcpitanlib.api.event.v1.AttackEntityEventRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = "mcpitanlib")
public class AttackEntityEventRegistryImpl {

    private static final List<AttackEntityEventRegistry.AttackEntity> listeners = new CopyOnWriteArrayList<>();

    public static void register(AttackEntityEventRegistry.AttackEntity attackEntity) {
        listeners.add(attackEntity);
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        for (AttackEntityEventRegistry.AttackEntity listener : listeners) {
            InteractionResult result = listener.attack(
                    event.getEntity(),
                    event.getEntity().level(),
                    InteractionHand.MAIN_HAND,
                    event.getTarget(),
                    null
            );

            if (result != InteractionResult.PASS) {
                event.setCanceled(true);
                return;
            }
        }
    }
}