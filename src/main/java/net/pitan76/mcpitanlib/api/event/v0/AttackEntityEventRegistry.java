package net.pitan76.mcpitanlib.api.event.v0;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class AttackEntityEventRegistry {
    public static void register(AttackEntity attackEntity) {
        AttackEntityCallback.EVENT.register(attackEntity::attack);
    }

    public interface AttackEntity {
        default InteractionResult attack(net.minecraft.world.entity.player.Player player, Level level, InteractionHand hand, Entity target, @Nullable EntityHitResult result) {
            return attack(new Player(player), level, target, hand, result).toActionResult();
        }

        CompatActionResult attack(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result);
    }
}
