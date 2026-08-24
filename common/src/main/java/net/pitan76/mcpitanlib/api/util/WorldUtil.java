package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.math.BoxUtil;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import net.pitan76.mcpitanlib.api.util.particle.CompatParticleType;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.entity.EntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;
import net.pitan76.mcpitanlib.midohra.world.chunk.ChunkTicketType;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public class WorldUtil {
    public static boolean hasSkyLight(Level world) {
        return world.dimensionType().hasSkyLight();
    }

    public static boolean isThundering(Level world) {
        return world.isThundering();
    }

    public static boolean isRaining(Level world) {
        return world.isRaining();
    }

    public static boolean isNight(Level world) {
        return world.isDarkOutside();
    }

    public static boolean isDay(Level world) {
        return world.isBrightOutside();
    }

    public static boolean isSkyVisible(Level world, BlockPos pos) {
        return world.canSeeSky(pos);
    }

    public static boolean isClient(Level world) {
        return world.isClientSide();
    }

    /**
     * ディメンションID (例: minecraft:overworld) を返す。
     */
    public static CompatIdentifier getDimensionId(Level world) {
        return CompatIdentifier.fromMinecraft(world.dimension().identifier());
    }

    public static void scheduleBlockTick(Level world, BlockPos pos, Block block, int delay) {
        world.scheduleTick(pos, block, delay);
    }

    public static void scheduleFluidTick(Level world, BlockPos pos, Fluid fluid, int delay) {
        world.scheduleTick(pos, fluid, delay);
    }

    public static boolean isServer(Level world) {
        return !isClient(world);
    }

    public static void spawnStack(Level world, BlockPos pos, ItemStack stack) {
        spawnEntity(world, new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack));
    }

    public static void spawnExperienceOrb(Level world, Vec3 pos, int amount) {
        if (world instanceof ServerLevel)
            ExperienceOrb.award((ServerLevel) world, pos, amount);
    }

    public static void spawnExperienceOrb(Level world, BlockPos pos, int amount) {
        spawnExperienceOrb(world, Vec3.atCenterOf(pos), amount);
    }

    public static void spawnEntity(Level world, Entity entity) {
        world.addFreshEntity(entity);
    }

    public static void playSound(Level world, @Nullable Player player, BlockPos pos, SoundEvent sound, SoundSource category, float volume, float pitch) {
        net.minecraft.world.entity.player.Player entity = player == null ? null : player.getEntity();
        world.playSound(entity, pos, sound, category, volume, pitch);
    }

    public static void playSound(Level world, @Nullable Player player, BlockPos pos, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch) {
        playSound(world, player, pos, sound.get(), category.get(), volume, pitch);
    }

    public static void playSound(Level world, BlockPos pos, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch) {
        playSound(world, null, pos, sound.get(), category.get(), volume, pitch);
    }

    public static void playSound(Level world, double x, double y, double z, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch, boolean useDistance) {
        world.playLocalSound(x, y, z, sound.get(), category.get(), volume, pitch, useDistance);
    }

    public static void sendEntityStatus(Level world, Entity entity, byte status) {
        world.broadcastEntityEvent(entity, status);
    }

    public static BlockPos getSpawnPos(Level world) {
        return world.getRespawnData().pos();
    }

    public static Optional<MinecraftServer> getServer(Level world) {
        if (isClient(world)) return Optional.empty();
        return Optional.ofNullable(world.getServer());
    }

    public static ServerLevel getWorld(Level world, Identifier worldId) {
        Optional<MinecraftServer> server = getServer(world);
        return server.map(minecraftServer -> getWorld(minecraftServer, worldId)).orElse(null);

    }

    public static Optional<ServerLevel> getWorld(Level world, CompatIdentifier worldId) {
        return Optional.ofNullable(getWorld(world, worldId.toMinecraft()));
    }

    public static ServerLevel getOverworld(MinecraftServer server) {
        return server.getLevel(Level.OVERWORLD);
    }

    public static ServerLevel getNether(MinecraftServer server) {
        return server.getLevel(Level.NETHER);
    }

    public static ServerLevel getEnd(MinecraftServer server) {
        return server.getLevel(Level.END);
    }

    public static ServerLevel getWorld(MinecraftServer server, Identifier worldId) {
        return server.getLevel(ResourceKey.create(Registries.DIMENSION, worldId));
    }

    public static ServerLevel getWorld(MinecraftServer server, CompatIdentifier worldId) {
        return getWorld(server, worldId.toMinecraft());
    }

    public static Identifier getWorldId(Level world) {
        return world.dimension().identifier();
    }

    public static CompatIdentifier getCompatWorldId(Level world) {
        return CompatIdentifier.fromMinecraft(getWorldId(world));
    }

    public static boolean equals(Level world, Level world2) {
        return Objects.equals(getWorldId(world), getWorldId(world2));
    }

    @Deprecated
    public static <T> void addTicket(ServerLevel world, net.minecraft.server.level.TicketType type, ChunkPos pos, int radius, T argument) {
        world.getChunkSource().addTicketWithRadius(type, pos, radius);
    }

    @Deprecated
    public static <T> void removeTicket(ServerLevel world, net.minecraft.server.level.TicketType type, ChunkPos pos, int radius, T argument) {
        world.getChunkSource().removeTicketWithRadius(type, pos, radius);
    }

    public static <T> void addTicket(ServerLevel world, ChunkTicketType<T> type, ChunkPos pos, int radius, T argument) {
        addTicket(world, type.getRaw(), pos, radius, argument);
    }

    public static <T> void removeTicket(ServerLevel world, ChunkTicketType<T> type, ChunkPos pos, int radius, T argument) {
        removeTicket(world, type.getRaw(), pos, radius, argument);
    }

    public static void addTicket(ServerLevel world, ChunkTicketType<?> type, ChunkPos pos, int radius) {
        world.getChunkSource().addTicketWithRadius(type.getRaw(), pos, radius);
    }

    public static void removeTicket(ServerLevel world, ChunkTicketType<?> type, ChunkPos pos, int radius) {
        world.getChunkSource().removeTicketWithRadius(type.getRaw(), pos, radius);
    }

    public static boolean isReceivingRedstonePower(Level world, BlockPos pos) {
        return world.hasNeighborSignal(pos);
    }

    public static int getBottomY(Level world) {
        return world.getMinY();
    }

    public static int getTopY(Level world) {
        return world.getMaxY();
    }

    public static int getDimensionHeight(Level world) {
        return world.dimensionType().height();
    }

    public static BlockEntity getBlockEntity(Level world, BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    public static boolean hasBlockEntity(Level world, BlockPos pos) {
        return getBlockEntity(world, pos) != null;
    }

    public static BlockState getBlockState(Level world, BlockPos pos) {
        return world.getBlockState(pos);
    }

    public static FluidState getFluidState(Level world, BlockPos pos) {
        return world.getFluidState(pos);
    }

    public static boolean hasFluidState(Level world, BlockPos pos) {
        return ! getFluidState(world, pos).isEmpty();
    }

    public static boolean isAir(Level world, BlockPos pos) {
        return getBlockState(world, pos).isAir();
    }

    public static boolean setBlockState(Level world, BlockPos pos, BlockState state, int flags) {
        return world.setBlock(pos, state, flags);
    }

    public static boolean setBlockState(Level world, BlockPos pos, BlockState state) {
        return setBlockState(world, pos, state, 3);
    }

    public static boolean setBlockState(Level world, BlockPos pos, Block block, int flags) {
        return setBlockState(world, pos, block.defaultBlockState(), flags);
    }

    public static boolean setBlockState(Level world, BlockPos pos, Block block) {
        return setBlockState(world, pos, block, 3);
    }

    public static boolean breakBlock(Level world, BlockPos pos, boolean drop) {
        return world.destroyBlock(pos, drop);
    }

    public static boolean breakBlock(Level world, BlockPos pos) {
        return breakBlock(world, pos, true);
    }

    public static boolean breakBlock(Level world, BlockPos pos, boolean drop, @Nullable Player player) {
        if (player == null)
            return world.destroyBlock(pos, drop, null);

        return world.destroyBlock(pos, drop, player.getPlayerEntity());
    }

    public static boolean breakBlock(Level world, BlockPos pos, @Nullable Player player) {
        return breakBlock(world, pos, true, player);
    }

    public static void removeBlockEntity(Level world, BlockPos pos) {
        world.removeBlockEntity(pos);
    }

    public static boolean removeBlock(Level world, BlockPos pos, boolean move) {
        return world.removeBlock(pos, move);
    }

    public static void addParticle(Level world, ParticleOptions parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        world.addParticle(parameters, x, y, z, velocityX, velocityY, velocityZ);
    }

    public static boolean canSetBlock(Level world, BlockPos pos) {
        return world.isUnobstructed(Blocks.STONE.defaultBlockState(), pos, CollisionContext.empty());
    }

    public static void updateComparators(Level world, BlockPos pos, Block block) {
        world.updateNeighbourForOutputSignal(pos, block);
    }

    public static List<Player> getPlayers(Level world) {
        List<Player> players = new ArrayList<>();
        for (net.minecraft.world.entity.player.Player player : world.players()) {
            players.add(new Player(player));
        }

        return players;
    }

    public static Player getPlayer(Level world, UUID uuid) {
        return new Player(world.getPlayerByUUID(uuid));
    }

    public static CompatRandom getRandom(Level world) {
        return new CompatRandom(world.getRandom());
    }

    public static long getTime(Level world) {
        return world.getGameTime();
    }

    public static <T extends Entity> List<T> getEntitiesByType(Level world, EntityType<T> filter, AABB box, Predicate<? super T> predicate) {
        return world.getEntities(filter, box, predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(Level world, Class<T> entityClass, AABB box, Predicate<? super T> predicate) {
        return world.getEntitiesOfClass(entityClass, box, predicate);
    }

    public static void spawnParticles(Level world, ParticleOptions parameters, double x, double y, double z, int count, double velocityX, double velocityY, double velocityZ, double speed) {
        if (!isServer(world)) return;

        ((ServerLevel) world).sendParticles(parameters, x, y, z, count, velocityX, velocityY, velocityZ, speed);
    }

    public static void updateListeners(Level world, BlockPos pos, BlockState oldState, BlockState newState, int flags) {
        world.sendBlockUpdated(pos, oldState, newState, flags);
    }

    public static net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraBlockState(Level world, BlockPos pos) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getBlockState(world, pos));
    }

    public static boolean setBlockState(Level world, BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state, int flags) {
        return setBlockState(world, pos, state.toMinecraft(), flags);
    }

    public static boolean setBlockState(Level world, BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return setBlockState(world, pos, state, 3);
    }

    public static boolean setBlockState(Level world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state, int flags) {
        return setBlockState(world, pos.toMinecraft(), state.toMinecraft(), flags);
    }

    public static boolean setBlockState(Level world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return setBlockState(world, pos, state, 3);
    }

    public static boolean breakBlock(Level world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, boolean drop) {
        return breakBlock(world, pos.toMinecraft(), drop);
    }

    public static boolean breakBlock(Level world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return breakBlock(world, pos, true);
    }

    public static boolean breakBlock(Level world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, boolean drop, @Nullable Player player) {
        return breakBlock(world, pos.toMinecraft(), drop, player);
    }

    public static boolean breakBlock(Level world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, @Nullable Player player) {
        return breakBlock(world, pos, true, player);
    }

    public static void removeBlockEntity(Level world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        removeBlockEntity(world, pos.toMinecraft());
    }

    public static boolean removeBlock(Level world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, boolean move) {
        return removeBlock(world, pos.toMinecraft(), move);
    }

    public static void playSound(net.pitan76.mcpitanlib.midohra.world.World world, @Nullable Player player, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch) {
        playSound(world.getRaw(), player, pos.toMinecraft(), sound, category, volume, pitch);
    }

    public static void dropStackOnBlock(Level world, BlockPos pos, ItemStack stack) {
        Block.popResource(world, pos, stack);
    }

    public static float getSkyAngle(Level world, float tickDelta) {
        long timeOfDay = world.getDefaultClockTime();
        float f = ((float)(timeOfDay % 24000L) + tickDelta) / 24000.0F - 0.25F;
        if (f < 0.0F) {
            f += 1.0F;
        }
        if (f > 1.0F) {
            f -= 1.0F;
        }
        return f;
    }

    public static Block getBlock(Level world, BlockPos pos) {
        return getBlockState(world, pos).getBlock();
    }

    public static BlockWrapper getBlockWrapper(Level world, BlockPos pos) {
        return BlockWrapper.of(getBlock(world, pos));
    }

    public static <T extends Entity> List<T> getEntitiesByType(Level world, EntityType<T> filter, AABB box) {
        return getEntitiesByType(world, filter, box, EntitySelector.ENTITY_STILL_ALIVE);
    }

    public static <T extends Entity> List<T> getEntitiesByType(Level world, EntityTypeWrapper filter, AABB box, Predicate<? super T> predicate) {
        return getEntitiesByType(world, (EntityType<T>) filter.get(), box, predicate);
    }

    public static List<?> getEntitiesByType(Level world, EntityTypeWrapper filter, AABB box) {
        return getEntitiesByType(world, filter.get(), box);
    }

    public static CompatIdentifier getOverworldId() {
        return CompatIdentifier.fromMinecraft(Level.OVERWORLD.identifier());
    }

    public static CompatIdentifier getNetherId() {
        return CompatIdentifier.fromMinecraft(Level.NETHER.identifier());
    }

    public static CompatIdentifier getEndId() {
        return CompatIdentifier.fromMinecraft(Level.END.identifier());
    }

    public static long getTimeOfDay(Level world) {
        return world.getDefaultClockTime();
    }

    public static <T extends Entity> List<T> getEntitiesByClass(Level world, Class<T> entityClass, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntitiesByClass(world, entityClass, box, EntitySelector.ENTITY_STILL_ALIVE);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(Level world, Class<T> entityClass, AABB box) {
        return getEntitiesByClass(world, entityClass, box, EntitySelector.ENTITY_STILL_ALIVE);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(Level world, Class<T> entityClass, net.pitan76.mcpitanlib.midohra.util.math.Box box, Predicate<? super T> predicate) {
        return getEntitiesByClass(world, entityClass, box.toMinecraft(), predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(Level world, Class<T> entityClass, Vector3d center, double radius, Predicate<? super T> predicate) {
        AABB box = BoxUtil.createBox(center.x - radius, center.y - radius, center.z - radius, center.x + radius, center.y + radius, center.z + radius);
        return getEntitiesByClass(world, entityClass, box, predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(Level world, Class<T> entityClass, Vector3d center, double radius) {
        return getEntitiesByClass(world, entityClass, center, radius, EntitySelector.ENTITY_STILL_ALIVE);
    }

    public static List<LivingEntity> getMonsters(Level world, AABB box) {
        return world.getEntitiesOfClass(LivingEntity.class, box, entity -> entity instanceof Enemy);
    }

    public static List<LivingEntity> getMonsters(Level world, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getMonsters(world, box.toMinecraft());
    }

    public static List<LivingEntity> getMonsters(Level world, Vector3d center, double radius) {
        AABB box = BoxUtil.createBox(center.x - radius, center.y - radius, center.z - radius, center.x + radius, center.y + radius, center.z + radius);
        return getMonsters(world, box);
    }

    public static List<?> getEntitiesByType(Level world, EntityTypeWrapper filter, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntitiesByType(world, filter, box.toMinecraft());
    }

    public static List<?> getEntitiesByType(Level world, EntityTypeWrapper filter, net.pitan76.mcpitanlib.midohra.util.math.Box box, Predicate<? super Entity> predicate) {
        return getEntitiesByType(world, filter, box.toMinecraft(), predicate);
    }

    /**
     * ParticleType is a ParticleEffect only.
     */
    public static void addParticle(Level world, CompatParticleType parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        if (parameters.getRaw() instanceof ParticleOptions)
            addParticle(world, (ParticleOptions) parameters.getRaw(), x, y, z, velocityX, velocityY, velocityZ);
    }
}
