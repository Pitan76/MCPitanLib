package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Relative;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

import java.util.UUID;

public class EntityUtil {
    public static Level getWorld(Entity entity) {
        return entity.level();
    }

    public static boolean damage(Entity target, DamageSource damageSource, float amount) {
        return target.hurtServer((ServerLevel) target.level(), damageSource, amount);
    }

    public static boolean damageWithThrownProjectile(Entity target, float damageAmount, Entity projectile, Entity attacker) {
        return target.hurtServer((ServerLevel) target.level(), DamageSourceUtil.thrownProjectile(projectile, attacker), damageAmount);
    }

    public static boolean damageWithMobProjectile(Entity target, float damageAmount, Entity projectile, LivingEntity attacker) {
        return target.hurtServer((ServerLevel) target.level(), DamageSourceUtil.mobProjectile(projectile, attacker), damageAmount);
    }

    public static boolean damageWithMobAttack(Entity target, float damageAmount, Entity source, LivingEntity attacker) {
        return target.hurtServer((ServerLevel) target.level(), DamageSourceUtil.mobAttack(attacker, source), damageAmount);
    }

    public static boolean damageWithPlayerAttack(Entity target, float damageAmount, Entity source, Player attacker) {
        return target.hurtServer((ServerLevel) target.level(), DamageSourceUtil.playerAttack(attacker, source), damageAmount);
    }

    public static void discard(Entity entity) {
        entity.discard();
    }

    public static void kill(Entity entity) {
        if (entity.level() instanceof ServerLevel)
            return;

        entity.kill((ServerLevel) entity.level());
    }

    public static void setVelocity(Entity entity, double x, double y, double z) {
        entity.setDeltaMovement(x, y, z);
    }

    public static Vec3 getVelocity(Entity entity) {
        return entity.getDeltaMovement();
    }

    public static void setNoGravity(Entity entity, boolean noGravity) {
        entity.setNoGravity(noGravity);
    }

    public static boolean hasNoGravity(Entity entity) {
        return entity.isNoGravity();
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
        entity.setGlowingTag(glowing);
    }

    public static boolean isGlowing(Entity entity) {
        return entity.isCurrentlyGlowing();
    }

    public static void setFire(Entity entity, int seconds) {
        entity.igniteForSeconds(seconds);
    }

    public static void extinguish(Entity entity) {
        entity.clearFire();
    }

    public static boolean isOnFire(Entity entity) {
        return entity.isOnFire();
    }

    /**
     * 凍結しうるエンティティかどうか。ストレイなど寒さに強いMobはfalse。
     */
    public static boolean canFreeze(Entity entity) {
        return entity.canFreeze();
    }

    /**
     * 凍結ダメージが入る状態かどうか。
     */
    public static boolean isFrozen(Entity entity) {
        return entity.isFullyFrozen();
    }

    public static int getFrozenTicks(Entity entity) {
        return entity.getTicksFrozen();
    }

    public static void setFrozenTicks(Entity entity, int ticks) {
        entity.setTicksFrozen(ticks);
    }

    /**
     * 現在の凍結時間に加算する。凍結しないエンティティには何もしない。
     * @return 実際に加算した場合はtrue
     */
    public static boolean addFrozenTicks(Entity entity, int ticks) {
        if (!canFreeze(entity)) return false;

        setFrozenTicks(entity, getFrozenTicks(entity) + ticks);
        return true;
    }

    /**
     * 凍結ダメージが入り始めるまでの時間 (tick)。
     */
    public static int getMinFreezeDamageTicks(Entity entity) {
        return entity.getTicksRequiredToFreeze();
    }

    public static void setInvisible(Entity entity, boolean invisible) {
        entity.setInvisible(invisible);
    }

    public static boolean isInvisible(Entity entity) {
        return entity.isInvisible();
    }

    public static void setSneaking(Entity entity, boolean sneaking) {
        entity.setShiftKeyDown(sneaking);
    }

    public static boolean isSneaking(Entity entity) {
        return entity.isShiftKeyDown();
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
        entity.unRide();
    }

    public static void attach(Entity entity, Entity vehicle) {
        entity.startRiding(vehicle, true, true);
    }

    public static void detachFromVehicle(Entity entity) {
        entity.stopRiding();
    }

    public static boolean isRiding(Entity entity) {
        return entity.isPassenger();
    }

    public static Entity getVehicle(Entity entity) {
        return entity.getVehicle();
    }

    public static void setVehicle(Entity entity, Entity vehicle) {
        entity.startRiding(vehicle, true, true);
    }

    public static void applyRotation(Entity entity, Rotation rotation) {
        entity.rotate(rotation);
    }

    public static void setVelocity(Entity entity, Vec3 velocity) {
        entity.setDeltaMovement(velocity);
    }

    public static void setFallDistance(Entity entity, double fallDistance) {
        entity.fallDistance = fallDistance;
    }

    public static double getFallDistance(Entity entity) {
        return entity.fallDistance;
    }

    public static void setVelocityModified(Entity entity, boolean velocityModified) {
        entity.needsSync = velocityModified;
    }

    public static boolean isVelocityModified(Entity entity) {
        return entity.needsSync;
    }

    public static float getYaw(Entity entity) {
        return entity.getYRot();
    }

    public static float getPitch(Entity entity) {
        return entity.getXRot();
    }

    public static void setYaw(Entity entity, float yaw) {
        entity.setYRot(yaw);
    }

    public static void setPitch(Entity entity, float pitch) {
        entity.setXRot(pitch);
    }

    public static float getSpeed(Entity entity) {
        return entity.flyDist;
    }

    public static void setSpeed(Entity entity, float speed) {
        entity.flyDist = speed;
    }

    public static boolean isOnGround(Entity entity) {
        return entity.onGround();
    }

    public static void setOnGround(Entity entity, boolean onGround) {
        entity.setOnGround(onGround);
    }

    public static boolean isAlive(Entity entity) {
        return entity.isAlive();
    }

    public static UUID getUuid(Entity entity) {
        return entity.getUUID();
    }

    public static String getUuidString(Entity entity) {
        return entity.getStringUUID();
    }

    public static void setUuid(Entity entity, UUID uuid) {
        entity.setUUID(uuid);
    }

    public static Component getName(Entity entity) {
        return entity.getName();
    }

    public static Component getDisplayName(Entity entity) {
        return entity.getDisplayName();
    }

    public static void setCustomName(Entity entity, Component customName) {
        entity.setCustomName(customName);
    }

    public static Component getCustomName(Entity entity) {
        return entity.getCustomName();
    }

    public static void setCustomNameVisible(Entity entity, boolean visible) {
        entity.setCustomNameVisible(visible);
    }

    public static boolean isCustomNameVisible(Entity entity) {
        return entity.isCustomNameVisible();
    }

    public static boolean hasCustomName(Entity entity) {
        return entity.hasCustomName();
    }

    public static String getNameAsString(Entity entity) {
        return entity.getName().getString();
    }

    public static String getDisplayNameAsString(Entity entity) {
        if (entity.getDisplayName() == null)
            return null;
        return entity.getDisplayName().getString();
    }

    public static String getCustomNameAsString(Entity entity) {
        if (entity.getCustomName() == null)
            return null;
        return entity.getCustomName().getString();
    }

    public static void setCustomName(Entity entity, String customName) {
        entity.setCustomName(TextUtil.literal(customName));
    }

    public static Vec3 getRotationVector(Entity entity) {
        return entity.getLookAngle();
    }

    public static Vec3 getPos(Entity entity) {
        return entity.position();
    }

    public static Vector3d getPosM(Entity entity) {
        return Vector3d.of(getPos(entity));
    }

    public static void setPos(Entity entity, double x, double y, double z) {
        entity.setPosRaw(x, y, z);
    }

    public static void addVelocity(Entity entity, double x, double y, double z) {
        entity.push(x, y, z);
    }

    public static void addVelocity(Entity entity, Vec3 velocity) {
        entity.push(velocity);
    }

    public static void addVelocity(Entity entity, Vector3d velocity) {
        addVelocity(entity, velocity.toMinecraft());
    }

    public static void setVelocity(Entity entity, Vector3d velocity) {
        entity.setDeltaMovement(velocity.toMinecraft());
    }

    public static void setPos(Entity entity, BlockPos pos) {
        setPos(entity, pos.getX(), pos.getY(), pos.getZ());
    }

     public static void setPos(Entity entity, Vector3d pos) {
        setPos(entity, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, ServerLevel world, double x, double y, double z, float yaw, float pitch, boolean resetCamera) {
        entity.teleportTo(world, x, y, z, Relative.ALL, yaw, pitch, resetCamera);
    }

    public static void teleport(Entity entity, ServerLevel world, double x, double y, double z, float yaw, float pitch) {
        teleport(entity, world, x, y, z, yaw, pitch, true);
    }

    public static void teleport(Entity entity, ServerLevel world, double x, double y, double z) {
        teleport(entity, world, x, y, z, entity.getYRot(), entity.getXRot(), true);
    }

    public static void teleport(Entity entity, ServerLevel world, Vector3d pos) {
        teleport(entity, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, ServerLevel world, BlockPos pos) {
        teleport(entity, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, double x, double y, double z) {
        if (!(entity.level() instanceof ServerLevel))
            return;

        teleport(entity, (ServerLevel) entity.level(), x, y, z, entity.getYRot(), entity.getXRot());
    }

    public static void teleport(Entity entity, Vector3d pos) {
        teleport(entity, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, BlockPos pos) {
        teleport(entity, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, ServerLevel world, Vector3d pos, float yaw, float pitch, boolean resetCamera) {
        teleport(entity, world, pos.getX(), pos.getY(), pos.getZ(), yaw, pitch, resetCamera);
    }

    public static void teleport(Entity entity, ServerLevel raw, BlockPos pos, float yaw, float pitch, boolean resetCamera) {
        teleport(entity, raw, pos.getX(), pos.getY(), pos.getZ(), yaw, pitch, resetCamera);
    }

    public static EntityType<?> getType(Entity entity) {
        return entity.getType();
    }

    public static void setStepHeight(Entity entity, float stepHeight) {
        AttributeInstance instance = getStepHeightAttribute(entity);
        if (instance == null) return;
        instance.setBaseValue(stepHeight);
    }

    public static float getStepHeight(Entity entity) {
        return getStepHeightAttribute(entity) != null ? (float) getStepHeightAttribute(entity).getValue() : 0.0F;
    }

    public static float getDefaultStepHeight(Entity entity) {
        AttributeInstance instance = getStepHeightAttribute(entity);
        if (instance == null) return 0.0F;

        return (float) instance.getAttribute().value().getDefaultValue();
    }

    private static AttributeInstance getStepHeightAttribute(Entity entity) {
        if (!(entity instanceof LivingEntity)) return null;

        return ((LivingEntity) entity).getAttribute(Attributes.STEP_HEIGHT);
    }
}
