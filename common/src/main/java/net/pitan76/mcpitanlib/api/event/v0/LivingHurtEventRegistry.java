package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.pitan76.mcpitanlib.api.event.result.EventResult;
import net.pitan76.mcpitanlib.api.event.v0.event.LivingHurtEvent;

@Deprecated
public class LivingHurtEventRegistry {
    @ExpectPlatform
    public static void register(LivingHurt livingHurt) {

    }

    public interface LivingHurt {
        default boolean hurt(LivingEntity var1, DamageSource var2, float var3) {
            return hurt(new LivingHurtEvent(var1, var2, var3)).toActionResult() != InteractionResult.FAIL;
        }

        EventResult hurt(LivingHurtEvent event);
    }
}
