package net.pitan76.mcpitanlib.api.event.v1;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.v1.event.AttackEntityEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import org.jetbrains.annotations.Nullable;

public class AttackEntityEventRegistry {
    @ExpectPlatform
    public static void register(AttackEntity attackEntity) {

    }

    public interface AttackEntity {
        default InteractionResult attack(net.minecraft.world.entity.player.Player player, Level level, InteractionHand hand, Entity target, @Nullable EntityHitResult result) {
            return attack(new AttackEntityEvent(player, level, target, hand, result)).toActionResult();
        }

        CompatActionResult attack(AttackEntityEvent event);
    }
}
