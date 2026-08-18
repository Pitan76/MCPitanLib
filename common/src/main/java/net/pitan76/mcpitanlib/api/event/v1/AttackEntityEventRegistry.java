package net.pitan76.mcpitanlib.api.event.v1;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.v1.event.AttackEntityEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import org.jetbrains.annotations.Nullable;

public class AttackEntityEventRegistry {
    @ExpectPlatform
    public static void register(AttackEntity attackEntity) {

    }

    public interface AttackEntity {
        default ActionResult attack(PlayerEntity player, World level, Hand hand, Entity target, @Nullable EntityHitResult result) {
            return attack(new AttackEntityEvent(player, level, target, hand, result)).toEventResult().toActionResult();
        }

        CompatActionResult attack(AttackEntityEvent event);
    }
}
