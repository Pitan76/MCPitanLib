package net.pitan76.mcpitanlib.api.event.v1;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.v1.event.AttackEntityEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import org.jetbrains.annotations.Nullable;

public class AttackEntityEventRegistry {
    public static void register(AttackEntity attackEntity) {
        PlayerEvent.ATTACK_ENTITY.register(attackEntity::attack);
    }

    public interface AttackEntity {
        default EventResult attack(PlayerEntity player, World level, Entity target, Hand hand, @Nullable EntityHitResult result) {
            return attack(new AttackEntityEvent(player, level, target, hand, result)).toEventResult().getResult();
        }

        CompatActionResult attack(AttackEntityEvent event);
    }
}
