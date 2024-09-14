package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.event.EventResult;
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
            return EventResult.toActionResult(hurt(new LivingHurtEvent(var1, var2, var3)));
        }

        me.shedaniel.architectury.event.EventResult hurt(LivingHurtEvent event);
    }
}
