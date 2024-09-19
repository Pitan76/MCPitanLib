package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;

public class EntityUtil {
    public static World getWorld(Entity entity) {
        return entity.getWorld();
    }

    public static boolean damage(Entity target, DamageSource damageSource, float amount) {
        return target.damage(damageSource, amount);
    }

    public static boolean damageWithThrownProjectile(Entity target, float damageAmount, Entity projectile, Entity attacker) {
        return target.damage(DamageSourceUtil.thrownProjectile(projectile, attacker), damageAmount);
    }

    public static boolean damageWithMobProjectile(Entity target, float damageAmount, Entity projectile, LivingEntity attacker) {
        return target.damage(DamageSourceUtil.mobProjectile(projectile, attacker), damageAmount);
    }

    public static boolean damageWithMobAttack(Entity target, float damageAmount, Entity source, LivingEntity attacker) {
        return target.damage(DamageSourceUtil.mobAttack(attacker, source), damageAmount);
    }

    public static boolean damageWithPlayerAttack(Entity target, float damageAmount, Entity source, Player attacker) {
        return target.damage(DamageSourceUtil.playerAttack(attacker, source), damageAmount);
    }

    public static void discard(Entity entity) {
        entity.discard();
    }

    public static void kill(Entity entity) {
        entity.kill();
    }

    public static void setVelocity(Entity entity, double x, double y, double z) {
        entity.setVelocity(x, y, z);
    }

    public static Vec3d getVelocity(Entity entity) {
        return entity.getVelocity();
    }

    public static void setNoGravity(Entity entity, boolean noGravity) {
        entity.setNoGravity(noGravity);
    }

    public static boolean hasNoGravity(Entity entity) {
        return entity.hasNoGravity();
    }

    public static void setInvulnerable(Entity entity, boolean invulnerable) {
        entity.setInvulnerable(invulnerable);
    }

    public static boolean isInvulnerable(Entity entity) {
        return entity.isInvulnerable();
    }

    public static void setSilent(Entity entity, boolean silent) {
        entity.setSilent(silent);
    }

    public static boolean isSilent(Entity entity) {
        return entity.isSilent();
    }

    public static void setGlowing(Entity entity, boolean glowing) {
        entity.setGlowing(glowing);
    }

    public static boolean isGlowing(Entity entity) {
        return entity.isGlowing();
    }

    public static void setFire(Entity entity, int seconds) {
        entity.setOnFireFor(seconds);
    }

    public static void extinguish(Entity entity) {
        entity.extinguish();
    }

    public static boolean isOnFire(Entity entity) {
        return entity.isOnFire();
    }

    public static void setInvisible(Entity entity, boolean invisible) {
        entity.setInvisible(invisible);
    }

    public static boolean isInvisible(Entity entity) {
        return entity.isInvisible();
    }

    public static void setSneaking(Entity entity, boolean sneaking) {
        entity.setSneaking(sneaking);
    }

    public static boolean isSneaking(Entity entity) {
        return entity.isSneaking();
    }

    public static void setSprinting(Entity entity, boolean sprinting) {
        entity.setSprinting(sprinting);
    }

    public static boolean isSprinting(Entity entity) {
        return entity.isSprinting();
    }

    public static void setSwimming(Entity entity, boolean swimming) {
        entity.setSwimming(swimming);
    }

    public static boolean isSwimming(Entity entity) {
        return entity.isSwimming();
    }

    public static void detach(Entity entity) {
        entity.detach();
    }

    public static void attach(Entity entity, Entity vehicle) {
        entity.startRiding(vehicle, true);
    }

    public static void detachFromVehicle(Entity entity) {
        entity.stopRiding();
    }

    public static boolean isRiding(Entity entity) {
        return entity.hasVehicle();
    }

    public static Entity getVehicle(Entity entity) {
        return entity.getVehicle();
    }

    public static void setVehicle(Entity entity, Entity vehicle) {
        entity.startRiding(vehicle, true);
    }
}
