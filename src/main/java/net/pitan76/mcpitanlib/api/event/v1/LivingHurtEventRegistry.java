package net.pitan76.mcpitanlib.api.event.v1;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.pitan76.mcpitanlib.api.event.v0.event.LivingHurtEvent;

public class LivingHurtEventRegistry {
    public static void register(LivingHurt livingHurt) {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(livingHurt::hurt);
    }

    public interface LivingHurt {

        @SuppressWarnings("deprecation")
        default boolean hurt(LivingEntity var1, DamageSource var2, float var3) {
            return hurt(new LivingHurtEvent(var1, var2, var3)).toActionResult() == InteractionResult.SUCCESS;
        }

        net.pitan76.mcpitanlib.api.event.result.EventResult hurt(LivingHurtEvent event);
    }
}
