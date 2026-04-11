package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.pitan76.mcpitanlib.api.entity.Player;

public class DamageSourceUtil {
    public static DamageSource thrownProjectile(Entity projectile, Entity attacker, Entity source) {
        return source.damageSources().thrown(projectile, attacker);
    }

    public static DamageSource thrownProjectile(Entity projectile, Entity attacker) {
        return thrownProjectile(projectile, attacker, projectile);
    }

    public static DamageSource playerAttack(Player attacker, Entity source) {
        return source.damageSources().playerAttack(attacker.getPlayerEntity());
    }

    public static DamageSource playerAttack(Player attacker) {
        return playerAttack(attacker, attacker.getPlayerEntity());
    }

    public static DamageSource mobAttack(LivingEntity attacker, Entity source) {
        return source.damageSources().mobAttack(attacker);
    }

    public static DamageSource mobAttack(LivingEntity attacker) {
        return mobAttack(attacker, attacker);
    }

    public static DamageSource mobProjectile(Entity projectile, LivingEntity attacker, Entity source) {
        return source.damageSources().mobProjectile(projectile, attacker);
    }

    public static DamageSource mobProjectile(Entity projectile, LivingEntity attacker) {
        return mobProjectile(projectile, attacker, projectile);
    }

    public static DamageSource fall(Entity source) {
        return source.damageSources().fall();
    }
}
