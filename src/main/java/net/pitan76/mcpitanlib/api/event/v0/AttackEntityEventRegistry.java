package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class AttackEntityEventRegistry {
    public static void register(AttackEntity attackEntity) {
        PlayerEvent.ATTACK_ENTITY.register(attackEntity::attack);
    }

    public interface AttackEntity {
        default EventResult attack(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result) {
            return attack(new Player(player), level, target, hand, result);
        }

        EventResult attack(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result);
    }
}
