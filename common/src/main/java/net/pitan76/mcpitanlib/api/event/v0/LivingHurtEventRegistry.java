package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.pitan76.mcpitanlib.api.event.v0.event.LivingHurtEvent;

public class LivingHurtEventRegistry {
    public static void register(LivingHurt livingHurt) {
        EntityEvent.LIVING_HURT.register(livingHurt::hurt);
    }

    public interface LivingHurt {
        default EventResult hurt(LivingEntity var1, DamageSource var2, float var3) {
            return hurt(new LivingHurtEvent(var1, var2, var3));
        }

        EventResult hurt(LivingHurtEvent event);
    }
}
