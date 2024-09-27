package net.pitan76.mcpitanlib.api.event.v1;

import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.event.v0.event.LivingHurtEvent;

public class LivingHurtEventRegistry {
    public static void register(LivingHurt livingHurt) {
        EntityEvent.LIVING_ATTACK.register(livingHurt::hurt);
    }

    public interface LivingHurt {

        default ActionResult hurt(LivingEntity var1, DamageSource var2, float var3) {
            return hurt(new LivingHurtEvent(var1, var2, var3)).toActionResult();
        }

        net.pitan76.mcpitanlib.api.event.result.EventResult hurt(LivingHurtEvent event);
    }
}
