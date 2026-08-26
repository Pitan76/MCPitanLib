package net.pitan76.mcpitanlib.midohra.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.pitan76.mcpitanlib.api.entity.CompatEntity;
import net.pitan76.mcpitanlib.api.entity.ICompatEntity;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.EntityUtil;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.ChunkPos;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;
import net.pitan76.mcpitanlib.midohra.world.World;

import java.util.Optional;
import java.util.UUID;

public class EntityWrapper {
    private final net.minecraft.entity.Entity entity;

    public static final EntityWrapper EMPTY = new EntityWrapper(null);

    protected EntityWrapper() {
        this.entity = null;
    }

    protected EntityWrapper(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    public static EntityWrapper of(net.minecraft.entity.Entity entity) {
        return new EntityWrapper(entity);
    }

    public static EntityWrapper of() {
        return EMPTY;
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public net.minecraft.entity.Entity get() {
        return entity;
    }

    public EntityTypeWrapper getType() {
        return EntityTypeWrapper.of(get().getType());
    }

    public Vector3d getPos() {
        return EntityUtil.getPosM(get());
    }

    public void setPos(double x, double y, double z) {
        EntityUtil.setPos(get(), x, y, z);
    }

    public void setPos(Vector3d pos) {
        EntityUtil.setPos(get(), pos);
    }

    public void setPos(BlockPos pos) {
        EntityUtil.setPos(get(), pos);
    }

    public void teleport(double x, double y, double z) {
        EntityUtil.teleport(get(), x, y, z);
    }

    public void teleport(Vector3d pos) {
        EntityUtil.teleport(get(), pos);
    }

    public void teleport(BlockPos pos) {
        EntityUtil.teleport(get(), pos);
    }

    public void teleport(ServerWorld world, double x, double y, double z, float yaw, float pitch, boolean resetCamera) {
        EntityUtil.teleport(get(), world.getRaw(), x, y, z, yaw, pitch, resetCamera);
    }

    public void teleport(ServerWorld world, Vector3d pos, float yaw, float pitch, boolean resetCamera) {
        EntityUtil.teleport(get(), world.getRaw(), pos, yaw, pitch, resetCamera);
    }

    public void teleport(ServerWorld world, BlockPos pos, float yaw, float pitch, boolean resetCamera) {
        EntityUtil.teleport(get(), world.getRaw(), pos, yaw, pitch, resetCamera);
    }

    public void teleport(ServerWorld world, double x, double y, double z) {
        EntityUtil.teleport(get(), world.getRaw(), x, y, z);
    }

    public void teleport(ServerWorld world, Vector3d pos) {
        EntityUtil.teleport(get(), world.getRaw(), pos);
    }

    public void teleport(ServerWorld world, BlockPos pos) {
        EntityUtil.teleport(get(), world.getRaw(), pos);
    }

    public BlockPos getBlockPos() {
        return BlockPos.of(get().getBlockPos());
    }

    public World getWorld() {
        return World.of(EntityUtil.getWorld(get()));
    }

    public boolean isRemoved() {
        return get().isRemoved();
    }

    public void discard() {
        EntityUtil.discard(get());
    }

    public void kill() {
        EntityUtil.kill(get());
    }

    public float getPitch() {
        return EntityUtil.getPitch(get());
    }

    public float getYaw() {
        return EntityUtil.getYaw(get());
    }

    public float getSpeed() {
        return EntityUtil.getSpeed(get());
    }

    public TextComponent getCustomName() {
        return new TextComponent(EntityUtil.getCustomName(get()));
    }

    public TextComponent getName() {
        return new TextComponent(EntityUtil.getName(get()));
    }

    public String getNameAsString() {
        return EntityUtil.getNameAsString(get());
    }

    public String getCustomNameAsString() {
        return EntityUtil.getCustomNameAsString(get());
    }

    public UUID getUuid() {
        return EntityUtil.getUuid(get());
    }

    public String getUuidString() {
        return EntityUtil.getUuidString(get());
    }

    public boolean isSneaking() {
        return EntityUtil.isSneaking(get());
    }

     public boolean isSprinting() {
        return EntityUtil.isSprinting(get());
    }

    public boolean isSwimming() {
        return EntityUtil.isSwimming(get());
    }

     public boolean isAlive() {
        return EntityUtil.isAlive(get());
    }

    public boolean isOnGround() {
        return EntityUtil.isOnGround(get());
    }

    public boolean isGlowing() {
        return EntityUtil.isGlowing(get());
    }

     public boolean isInvisible() {
        return EntityUtil.isInvisible(get());
    }

     public boolean isInvulnerable() {
        return EntityUtil.isInvulnerable(get());
    }

    public boolean isSilent() {
        return EntityUtil.isSilent(get());
    }

    public boolean isOnFire() {
        return EntityUtil.isOnFire(get());
    }

    public boolean isRiding() {
        return EntityUtil.isRiding(get());
    }

    public boolean isVelocityModified() {
        return EntityUtil.isVelocityModified(get());
    }

    public boolean isCustomNameVisible() {
        return EntityUtil.isCustomNameVisible(get());
    }

    public TextComponent getDisplayName() {
        return new TextComponent(EntityUtil.getDisplayName(get()));
    }

    public double getFallDistance() {
        return EntityUtil.getFallDistance(get());
    }

    public boolean isLivingEntity() {
        return get() instanceof LivingEntity;
    }

    public boolean damageByMobAttack(float damageAmount, EntityWrapper source, EntityWrapper attacker) {
        return EntityUtil.damageWithMobAttack(get(), damageAmount, source.get(), (LivingEntity) attacker.get());
    }

    public boolean damageByPlayerAttack(float damageAmount, EntityWrapper source, Player attacker) {
        return EntityUtil.damageWithPlayerAttack(get(), damageAmount, source.get(), attacker);
    }

    public boolean damageWithThrownProjectile(float damageAmount, EntityWrapper source, EntityWrapper projectile) {
        return EntityUtil.damageWithThrownProjectile(get(), damageAmount, source.get(), projectile.get());
    }

    public boolean damageWithMobProjectile(float damageAmount, EntityWrapper projectile, EntityWrapper attacker) {
        return EntityUtil.damageWithMobProjectile(get(), damageAmount, projectile.get(), (LivingEntity) attacker.get());
    }

    public boolean setVelocity(double x, double y, double z) {
        EntityUtil.setVelocity(get(), x, y, z);
        return true;
    }

    public void setVelocity(Vector3d velocity) {
        EntityUtil.setVelocity(get(), velocity);
    }

    public void addVelocity(double x, double y, double z) {
        EntityUtil.addVelocity(get(), x, y, z);
    }

    public void addVelocity(Vector3d velocity) {
        EntityUtil.addVelocity(get(), velocity);
    }

    public Vector3d getVelocity() {
        return Vector3d.of(EntityUtil.getVelocity(get()));
    }

    public boolean setCustomName(TextComponent name) {
        EntityUtil.setCustomName(get(), name.getText());
        return true;
    }

    public boolean setInvulnerable(boolean invulnerable) {
        EntityUtil.setInvulnerable(get(), invulnerable);
        return true;
    }

    public boolean setNoGravity(boolean noGravity) {
        EntityUtil.setNoGravity(get(), noGravity);
        return true;
    }

    public boolean setGlowing(boolean glowing) {
        EntityUtil.setGlowing(get(), glowing);
        return true;
    }

    public boolean setInvisible(boolean invisible) {
        EntityUtil.setInvisible(get(), invisible);
        return true;
    }

    public boolean setSilent(boolean silent) {
        EntityUtil.setSilent(get(), silent);
        return true;
    }

    public boolean setFire(int seconds) {
        EntityUtil.setFire(get(), seconds);
        return true;
    }

    /**
     * 凍結しうるエンティティかどうか。ストレイなど寒さに強いMobはfalse。
     */
    public boolean canFreeze() {
        return EntityUtil.canFreeze(get());
    }

    /**
     * 凍結ダメージが入る状態かどうか。
     */
    public boolean isFrozen() {
        return EntityUtil.isFrozen(get());
    }

    public int getFrozenTicks() {
        return EntityUtil.getFrozenTicks(get());
    }

    public boolean setFrozenTicks(int ticks) {
        EntityUtil.setFrozenTicks(get(), ticks);
        return true;
    }

    /**
     * 現在の凍結時間に加算する。凍結しないエンティティには何もしない。
     * @return 実際に加算した場合はtrue
     */
    public boolean addFrozenTicks(int ticks) {
        return EntityUtil.addFrozenTicks(get(), ticks);
    }

    /**
     * 凍結ダメージが入り始めるまでの時間 (tick)。
     */
    public int getMinFreezeDamageTicks() {
        return EntityUtil.getMinFreezeDamageTicks(get());
    }

    public boolean setCustomNameVisible(boolean visible) {
        EntityUtil.setCustomNameVisible(get(), visible);
        return true;
    }

    public EntityWrapper getVehicle() {
        return EntityWrapper.of(get().getVehicle());
    }

    public EntityWrapper getRootVehicle() {
        return EntityWrapper.of(get().getRootVehicle());
    }

    public EntityWrapper getPassenger(int index) {
        return EntityWrapper.of(get().getPassengerList().get(index));
    }

    public int getPassengerCount() {
        return get().getPassengerList().size();
    }

    public Class<? extends Entity> getEntityClass() {
        return get().getClass();
    }

    public Optional<CompatEntity> toCompatEntity() {
        if (get() instanceof CompatEntity) {
            return Optional.of((CompatEntity) get());
        }
        return Optional.empty();
    }

    public void spawn(World world) {
        world.spawnEntity(this);
    }

    public ChunkPos getChunkPos() {
        return ChunkPos.of(get().getChunkPos());
    }

    @Override
    public int hashCode() {
        return get() != null ? get().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EntityWrapper)) return false;
        EntityWrapper other = (EntityWrapper) obj;
        return get() != null && get().equals(other.get());
    }

    /**
     * instanceof check for the entity of this wrapper.
     * @param clazz the class of the entity to check
     * @return true if the raw item of this stack is an instance of the given class, false otherwise
     */
    public boolean instanceOf(Class<?> clazz) {
        if (isEmpty()) return false;

        return clazz.isInstance(get());
    }

    /**
     * instanceof check for the entity of this wrapper.
     * @param wrapper the entity to check
     * @return true if the entity of this wrapper is an instance of the given entity, false otherwise
     */
    public boolean instanceOf(EntityWrapper wrapper) {
        if (isEmpty()) return false;

        Entity entity = wrapper.get();
        if (entity == null) return false;

        Class<?> clazz = entity.getClass();
        return clazz.isInstance(get());
    }

    public SpawnGroup getSpawnGroup() {
        return SpawnGroup.of(get().getType().getSpawnGroup());
    }

    public <T extends CompatEntity> T getCompatEntity(Class<T> clazz) {
        if (isEmpty()) return null;
        if (get() instanceof CompatEntity) {
            CompatEntity compatEntity = (CompatEntity) get();
            if (clazz.isInstance(compatEntity))
                return clazz.cast(compatEntity);
        }
        return null;
    }

    public <T extends CompatEntity> Optional<T> toCompatEntity(Class<T> clazz) {
        return Optional.ofNullable(getCompatEntity(clazz));
    }

    public boolean isPlayerEntity() {
        return get() instanceof PlayerEntity;
    }

    public Optional<Player> toPlayer() {
        if (isPlayerEntity()) {
            return Optional.of(new Player((PlayerEntity) get()));
        }
        return Optional.empty();
    }

    public void tick() {
        get().tick();
    }

    public double getX() {
        return getPos().getX();
    }

    public double getY() {
        return getPos().getY();
    }

    public double getZ() {
        return getPos().getZ();
    }

    public <T extends ICompatEntity> T getICompatEntity(Class<T> clazz) {
        if (isEmpty()) return null;
        if (get() instanceof ICompatEntity) {
            ICompatEntity compatEntity = (ICompatEntity) get();
            if (clazz.isInstance(compatEntity))
                return clazz.cast(compatEntity);
        }
        return null;
    }

    public <T extends ICompatEntity> Optional<T> toICompatEntity(Class<T> clazz) {
        return Optional.ofNullable(getICompatEntity(clazz));
    }

    public Vector3d getRotationVector() {
        return Vector3d.of(EntityUtil.getRotationVector(get()));
    }

    public void setFallDistance(float fallDistance) {
        EntityUtil.setFallDistance(get(), fallDistance);
    }

    public void setVelocityModified(boolean velocityModified) {
        EntityUtil.setVelocityModified(get(), velocityModified);
    }

    public void setSwimming(boolean swimming) {
        EntityUtil.setSwimming(get(), swimming);
    }

    public void setSprinting(boolean sprinting) {
        EntityUtil.setSprinting(get(), sprinting);
    }

    public void setSneaking(boolean sneaking) {
        EntityUtil.setSneaking(get(), sneaking);
    }

    public void setOnGround(boolean onGround) {
        EntityUtil.setOnGround(get(), onGround);
    }

    public void setUuid(UUID uuid) {
     EntityUtil.setUuid(get(), uuid);
    }

    public void setCustomName(String name) {
        EntityUtil.setCustomName(get(), name);
    }
}
