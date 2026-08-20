package net.pitan76.mcpitanlib.api.event.v1.fabric;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.event.v1.LivingHurtEventRegistry;
import net.pitan76.mcpitanlib.fabric.event.LivingHurtCallbacks;

public class LivingHurtEventRegistryImpl {
    public static void register(final LivingHurtEventRegistry.LivingHurt livingHurt) {
        LivingHurtCallbacks.register(new LivingHurtCallbacks.AllowDamage() {
            @Override
            public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
                return livingHurt.hurt(entity, source, amount) != ActionResult.FAIL;
            }
        });
    }
}
