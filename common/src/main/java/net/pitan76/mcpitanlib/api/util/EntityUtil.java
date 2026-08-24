package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

import java.util.UUID;

public class EntityUtil {
    public static World getWorld(Entity entity) {
        return entity.getEntityWorld();
    }

    public static boolean damage(Entity target, DamageSource damageSource, float amount) {
        return target.damage((ServerWorld) target.getEntityWorld(), damageSource, amount);
    }

    public static boolean damageWithThrownProjectile(Entity target, float damageAmount, Entity projectile, Entity attacker) {
        return target.damage((ServerWorld) target.getEntityWorld(), DamageSourceUtil.thrownProjectile(projectile, attacker), damageAmount);
    }

    public static boolean damageWithMobProjectile(Entity target, float damageAmount, Entity projectile, LivingEntity attacker) {
        return target.damage((ServerWorld) target.getEntityWorld(), DamageSourceUtil.mobProjectile(projectile, attacker), damageAmount);
    }

    public static boolean damageWithMobAttack(Entity target, float damageAmount, Entity source, LivingEntity attacker) {
        return target.damage((ServerWorld) target.getEntityWorld(), DamageSourceUtil.mobAttack(attacker, source), damageAmount);
    }

    public static boolean damageWithPlayerAttack(Entity target, float damageAmount, Entity source, Player attacker) {
        return target.damage((ServerWorld) target.getEntityWorld(), DamageSourceUtil.playerAttack(attacker, source), damageAmount);
    }

    public static void discard(Entity entity) {
        entity.discard();
    }

    public static void kill(Entity entity) {
        if (entity.getEntityWorld() instanceof ServerWorld)
            return;

        entity.kill((ServerWorld) entity.getEntityWorld());
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
        entity.startRiding(vehicle, true, true);
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
        entity.startRiding(vehicle, true, true);
    }

    public static void applyRotation(Entity entity, BlockRotation rotation) {
        entity.applyRotation(rotation);
    }

    public static void setVelocity(Entity entity, Vec3d velocity) {
        entity.setVelocity(velocity);
    }

    public static void setFallDistance(Entity entity, double fallDistance) {
        entity.fallDistance = fallDistance;
    }

    public static double getFallDistance(Entity entity) {
        return entity.fallDistance;
    }

    public static void setVelocityModified(Entity entity, boolean velocityModified) {
        entity.velocityModified = velocityModified;
    }

    public static boolean isVelocityModified(Entity entity) {
        return entity.velocityModified;
    }

    public static float getYaw(Entity entity) {
        return entity.getYaw();
    }

    public static float getPitch(Entity entity) {
        return entity.getPitch();
    }

    public static void setYaw(Entity entity, float yaw) {
        entity.setYaw(yaw);
    }

    public static void setPitch(Entity entity, float pitch) {
        entity.setPitch(pitch);
    }

    public static float getSpeed(Entity entity) {
        return entity.speed;
    }

    public static void setSpeed(Entity entity, float speed) {
        entity.speed = speed;
    }

    public static boolean isOnGround(Entity entity) {
        return entity.isOnGround();
    }

    public static void setOnGround(Entity entity, boolean onGround) {
        entity.setOnGround(onGround);
    }

    public static boolean isAlive(Entity entity) {
        return entity.isAlive();
    }

    public static UUID getUuid(Entity entity) {
        return entity.getUuid();
    }

    public static String getUuidString(Entity entity) {
        return entity.getUuidAsString();
    }

    public static void setUuid(Entity entity, UUID uuid) {
        entity.setUuid(uuid);
    }

    public static Text getName(Entity entity) {
        return entity.getName();
    }

    public static Text getDisplayName(Entity entity) {
        return entity.getDisplayName();
    }

    public static void setCustomName(Entity entity, Text customName) {
        entity.setCustomName(customName);
    }

    public static Text getCustomName(Entity entity) {
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

    public static Vec3d getRotationVector(Entity entity) {
        return entity.getRotationVector();
    }

    public static Vec3d getPos(Entity entity) {
        return entity.getEntityPos();
    }

    public static Vector3d getPosM(Entity entity) {
        return Vector3d.of(getPos(entity));
    }

    public static void setPos(Entity entity, double x, double y, double z) {
        entity.setPos(x, y, z);
    }

    public static void addVelocity(Entity entity, double x, double y, double z) {
        entity.addVelocity(x, y, z);
    }

    public static void addVelocity(Entity entity, Vec3d velocity) {
        entity.addVelocity(velocity);
    }

    public static void addVelocity(Entity entity, Vector3d velocity) {
        addVelocity(entity, velocity.toMinecraft());
    }

    public static void setVelocity(Entity entity, Vector3d velocity) {
        entity.setVelocity(velocity.toMinecraft());
    }

    public static void setPos(Entity entity, BlockPos pos) {
        setPos(entity, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void setPos(Entity entity, Vector3d pos) {
        setPos(entity, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, ServerWorld world, double x, double y, double z, float yaw, float pitch, boolean resetCamera) {
        entity.teleport(world, x, y, z, PositionFlag.VALUES, yaw, pitch, resetCamera);
    }

    public static void teleport(Entity entity, ServerWorld world, double x, double y, double z, float yaw, float pitch) {
        teleport(entity, world, x, y, z, yaw, pitch, true);
    }

    public static void teleport(Entity entity, ServerWorld world, double x, double y, double z) {
        teleport(entity, world, x, y, z, entity.getYaw(), entity.getPitch(), true);
    }

    public static void teleport(Entity entity, ServerWorld world, Vector3d pos) {
        teleport(entity, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, ServerWorld world, BlockPos pos) {
        teleport(entity, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, double x, double y, double z) {
        if (!(entity.getEntityWorld() instanceof ServerWorld))
            return;

        teleport(entity, (ServerWorld) entity.getEntityWorld(), x, y, z, entity.getYaw(), entity.getPitch());
    }

    public static void teleport(Entity entity, Vector3d pos) {
        teleport(entity, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, BlockPos pos) {
        teleport(entity, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void teleport(Entity entity, ServerWorld world, Vector3d pos, float yaw, float pitch, boolean resetCamera) {
        teleport(entity, world, pos.getX(), pos.getY(), pos.getZ(), yaw, pitch, resetCamera);
    }

    public static void teleport(Entity entity, ServerWorld raw, BlockPos pos, float yaw, float pitch, boolean resetCamera) {
        teleport(entity, raw, pos.getX(), pos.getY(), pos.getZ(), yaw, pitch, resetCamera);
    }

    public static EntityType<?> getType(Entity entity) {
        return entity.getType();
    }

    public static void setStepHeight(Entity entity, float stepHeight) {
        entity.stepHeight = stepHeight;
    }

    public static float getStepHeight(Entity entity) {
        return entity.stepHeight;
    }

    public static float getDefaultStepHeight(Entity entity) {
        return entity instanceof net.minecraft.entity.player.PlayerEntity ? 0.6F : 0.0F;
    }
}
