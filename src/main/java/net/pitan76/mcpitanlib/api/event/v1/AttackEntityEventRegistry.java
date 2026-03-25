package net.pitan76.mcpitanlib.api.event.v1;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.v1.event.AttackEntityEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import org.jetbrains.annotations.Nullable;

public class AttackEntityEventRegistry {
    public static void register(AttackEntity attackEntity) {
        PlayerEvent.ATTACK_ENTITY.register(attackEntity::attack);
    }

    public interface AttackEntity {
        default EventResult attack(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result) {
            return attack(new AttackEntityEvent(player, level, target, hand, result)).toEventResult().getResult();
        }

        CompatActionResult attack(AttackEntityEvent event);
    }
}
