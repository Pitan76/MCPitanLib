package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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
    public static boolean hasSkyLight(World world) {
        return world.getDimension().hasSkyLight();
    }

    public static boolean isThundering(World world) {
        return world.isThundering();
    }

    public static boolean isRaining(World world) {
        return world.isRaining();
    }

    public static boolean isNight(World world) {
        return world.isNight();
    }

    public static boolean isDay(World world) {
        return world.isDay();
    }

    public static boolean isSkyVisible(World world, BlockPos pos) {
        return world.isSkyVisible(pos);
    }

    public static boolean isClient(World world) {
        return world.isClient();
    }

    /**
     * ディメンションID (例: minecraft:overworld) を返す。
     */
    public static CompatIdentifier getDimensionId(World world) {
        return CompatIdentifier.fromMinecraft(world.getRegistryKey().getValue());
    }

    public static void scheduleBlockTick(World world, BlockPos pos, Block block, int delay) {
        world.scheduleBlockTick(pos, block, delay);
    }

    public static void scheduleFluidTick(World world, BlockPos pos, Fluid fluid, int delay) {
        world.scheduleFluidTick(pos, fluid, delay);
    }

    public static boolean isServer(World world) {
        return !isClient(world);
    }

    public static void spawnStack(World world, BlockPos pos, ItemStack stack) {
        spawnEntity(world, new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack));
    }

    public static void spawnExperienceOrb(World world, Vec3d pos, int amount) {
        if (world instanceof ServerWorld)
            ExperienceOrbEntity.spawn((ServerWorld) world, pos, amount);
    }

    public static void spawnExperienceOrb(World world, BlockPos pos, int amount) {
        spawnExperienceOrb(world, pos.toCenterPos(), amount);
    }

    public static void spawnEntity(World world, Entity entity) {
        world.spawnEntity(entity);
    }

    public static void playSound(World world, @Nullable Player player, BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch) {
        PlayerEntity entity = player == null ? null : player.getEntity();
        world.playSound(entity, pos, sound, category, volume, pitch);
    }

    public static void playSound(World world, @Nullable Player player, BlockPos pos, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch) {
        playSound(world, player, pos, sound.get(), category.get(), volume, pitch);
    }

    public static void playSound(World world, BlockPos pos, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch) {
        playSound(world, null, pos, sound.get(), category.get(), volume, pitch);
    }

    public static void playSound(World world, double x, double y, double z, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch, boolean useDistance) {
        world.playSound(x, y, z, sound.get(), category.get(), volume, pitch, useDistance);
    }

    public static void sendEntityStatus(World world, Entity entity, byte status) {
        world.sendEntityStatus(entity, status);
    }

    public static BlockPos getSpawnPos(World world) {
        return world.getSpawnPos();
    }

    public static Optional<MinecraftServer> getServer(World world) {
        if (isClient(world)) return Optional.empty();
        return Optional.ofNullable(world.getServer());
    }

    public static ServerWorld getWorld(World world, Identifier worldId) {
        Optional<MinecraftServer> server = getServer(world);
        if (!server.isPresent()) return null;

        return getWorld(server.get(), worldId);
    }

    public static Optional<ServerWorld> getWorld(World world, CompatIdentifier worldId) {
        return Optional.ofNullable(getWorld(world, worldId.toMinecraft()));
    }

    public static ServerWorld getOverworld(MinecraftServer server) {
        return server.getWorld(World.OVERWORLD);
    }

    public static ServerWorld getNether(MinecraftServer server) {
        return server.getWorld(World.NETHER);
    }

    public static ServerWorld getEnd(MinecraftServer server) {
        return server.getWorld(World.END);
    }

    public static ServerWorld getWorld(MinecraftServer server, Identifier worldId) {
        return server.getWorld(RegistryKey.of(RegistryKeys.WORLD, worldId));
    }

    public static ServerWorld getWorld(MinecraftServer server, CompatIdentifier worldId) {
        return getWorld(server, worldId.toMinecraft());
    }

    public static Identifier getWorldId(World world) {
        return world.getRegistryKey().getValue();
    }

    public static CompatIdentifier getCompatWorldId(World world) {
        return CompatIdentifier.fromMinecraft(getWorldId(world));
    }

    public static boolean equals(World world, World world2) {
        return Objects.equals(getWorldId(world), getWorldId(world2));
    }

    @Deprecated
    public static <T> void addTicket(ServerWorld world, net.minecraft.server.world.ChunkTicketType<T> type, ChunkPos pos, int radius, T argument) {
        world.getChunkManager().addTicket(type, pos, radius, argument);
    }

    @Deprecated
    public static <T> void removeTicket(ServerWorld world, net.minecraft.server.world.ChunkTicketType<T> type, ChunkPos pos, int radius, T argument) {
        world.getChunkManager().removeTicket(type, pos, radius, argument);
    }

    public static <T> void addTicket(ServerWorld world, ChunkTicketType<T> type, ChunkPos pos, int radius, T argument) {
        addTicket(world, type.getRaw(), pos, radius, argument);
    }

    public static <T> void removeTicket(ServerWorld world, ChunkTicketType<T> type, ChunkPos pos, int radius, T argument) {
        removeTicket(world, type.getRaw(), pos, radius, argument);
    }

    public static void addTicket(ServerWorld world, ChunkTicketType<?> type, ChunkPos pos, int radius) {
        net.minecraft.server.world.ChunkTicketType rawType = type.getRaw();
        world.getChunkManager().addTicket( rawType, pos, radius, pos);
    }

    public static void removeTicket(ServerWorld world, ChunkTicketType<?> type, ChunkPos pos, int radius) {
        net.minecraft.server.world.ChunkTicketType rawType = type.getRaw();
        world.getChunkManager().removeTicket(rawType, pos, radius, pos);
    }

    public static boolean isReceivingRedstonePower(World world, BlockPos pos) {
        return world.isReceivingRedstonePower(pos);
    }

    public static int getBottomY(World world) {
        return world.getBottomY();
    }

    public static int getTopY(World world) {
        return world.getTopY();
    }

    public static int getDimensionHeight(World world) {
        return world.getDimension().height();
    }

    public static BlockEntity getBlockEntity(World world, BlockPos pos) {
        return world.getBlockEntity(pos);
    }

    public static boolean hasBlockEntity(World world, BlockPos pos) {
        return getBlockEntity(world, pos) != null;
    }

    public static BlockState getBlockState(World world, BlockPos pos) {
        return world.getBlockState(pos);
    }

    public static FluidState getFluidState(World world, BlockPos pos) {
        return world.getFluidState(pos);
    }

    public static boolean hasFluidState(World world, BlockPos pos) {
        return ! getFluidState(world, pos).isEmpty();
    }

    public static boolean isAir(World world, BlockPos pos) {
        return getBlockState(world, pos).isAir();
    }

    public static boolean setBlockState(World world, BlockPos pos, BlockState state, int flags) {
        return world.setBlockState(pos, state, flags);
    }

    public static boolean setBlockState(World world, BlockPos pos, BlockState state) {
        return setBlockState(world, pos, state, 3);
    }

    public static boolean setBlockState(World world, BlockPos pos, Block block, int flags) {
        return setBlockState(world, pos, block.getDefaultState(), flags);
    }

    public static boolean setBlockState(World world, BlockPos pos, Block block) {
        return setBlockState(world, pos, block, 3);
    }

    public static boolean breakBlock(World world, BlockPos pos, boolean drop) {
        return world.breakBlock(pos, drop);
    }

    public static boolean breakBlock(World world, BlockPos pos) {
        return breakBlock(world, pos, true);
    }

    public static boolean breakBlock(World world, BlockPos pos, boolean drop, @Nullable Player player) {
        if (player == null)
            return world.breakBlock(pos, drop, null);

        return world.breakBlock(pos, drop, player.getPlayerEntity());
    }

    public static boolean breakBlock(World world, BlockPos pos, @Nullable Player player) {
        return breakBlock(world, pos, true, player);
    }

    public static void removeBlockEntity(World world, BlockPos pos) {
        world.removeBlockEntity(pos);
    }

    public static boolean removeBlock(World world, BlockPos pos, boolean move) {
        return world.removeBlock(pos, move);
    }

    public static void addParticle(World world, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        world.addParticle(parameters, x, y, z, velocityX, velocityY, velocityZ);
    }

    public static boolean canSetBlock(World world, BlockPos pos) {
        return world.canPlace(Blocks.STONE.getDefaultState(), pos, ShapeContext.absent());
    }

    public static void updateComparators(World world, BlockPos pos, Block block) {
        world.updateComparators(pos, block);
    }

    public static List<Player> getPlayers(World world) {
        List<Player> players = new ArrayList<>();
        for (PlayerEntity player : world.getPlayers()) {
            players.add(new Player(player));
        }

        return players;
    }

    public static Player getPlayer(World world, UUID uuid) {
        return new Player(world.getPlayerByUuid(uuid));
    }

    public static CompatRandom getRandom(World world) {
        return new CompatRandom(world.getRandom());
    }

    public static long getTime(World world) {
        return world.getTime();
    }

    public static <T extends Entity> List<T> getEntitiesByType(World world, EntityType<T> filter, Box box, Predicate<? super T> predicate) {
        return world.getEntitiesByType(filter, box, predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(World world, Class<T> entityClass, Box box, Predicate<? super T> predicate) {
        return world.getEntitiesByClass(entityClass, box, predicate);
    }

    public static void spawnParticles(World world, ParticleEffect parameters, double x, double y, double z, int count, double velocityX, double velocityY, double velocityZ, double speed) {
        if (!isServer(world)) return;

        ((ServerWorld) world).spawnParticles(parameters, x, y, z, count, velocityX, velocityY, velocityZ, speed);
    }

    public static void updateListeners(World world, BlockPos pos, BlockState oldState, BlockState newState, int flags) {
        world.updateListeners(pos, oldState, newState, flags);
    }

    public static net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraBlockState(World world, BlockPos pos) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getBlockState(world, pos));
    }

    public static boolean setBlockState(World world, BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state, int flags) {
        return setBlockState(world, pos, state.toMinecraft(), flags);
    }

    public static boolean setBlockState(World world, BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return setBlockState(world, pos, state, 3);
    }

    public static boolean setBlockState(World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state, int flags) {
        return setBlockState(world, pos.toMinecraft(), state.toMinecraft(), flags);
    }

    public static boolean setBlockState(World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        return setBlockState(world, pos, state, 3);
    }

    public static boolean breakBlock(World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, boolean drop) {
        return breakBlock(world, pos.toMinecraft(), drop);
    }

    public static boolean breakBlock(World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return breakBlock(world, pos, true);
    }

    public static boolean breakBlock(World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, boolean drop, @Nullable Player player) {
        return breakBlock(world, pos.toMinecraft(), drop, player);
    }

    public static boolean breakBlock(World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, @Nullable Player player) {
        return breakBlock(world, pos, true, player);
    }

    public static void removeBlockEntity(World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        removeBlockEntity(world, pos.toMinecraft());
    }

    public static boolean removeBlock(World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, boolean move) {
        return removeBlock(world, pos.toMinecraft(), move);
    }

    public static void playSound(net.pitan76.mcpitanlib.midohra.world.World world, @Nullable Player player, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch) {
        playSound(world.getRaw(), player, pos.toMinecraft(), sound, category, volume, pitch);
    }

    public static void dropStackOnBlock(World world, BlockPos pos, ItemStack stack) {
        Block.dropStack(world, pos, stack);
    }

    public static float getSkyAngle(World world, float tickDelta) {
        return world.getSkyAngle(tickDelta);
    }

    public static Block getBlock(World world, BlockPos pos) {
        return getBlockState(world, pos).getBlock();
    }

    public static BlockWrapper getBlockWrapper(World world, BlockPos pos) {
        return BlockWrapper.of(getBlock(world, pos));
    }

    public static <T extends Entity> List<T> getEntitiesByType(World world, EntityType<T> filter, Box box) {
        return getEntitiesByType(world, filter, box, EntityPredicates.VALID_ENTITY);
    }

    public static <T extends Entity> List<T> getEntitiesByType(World world, EntityTypeWrapper filter, Box box, Predicate<? super T> predicate) {
        return getEntitiesByType(world, (EntityType<T>) filter.get(), box, predicate);
    }

    public static List<?> getEntitiesByType(World world, EntityTypeWrapper filter, Box box) {
        return getEntitiesByType(world, filter.get(), box);
    }

    public static CompatIdentifier getOverworldId() {
        return CompatIdentifier.fromMinecraft(World.OVERWORLD.getValue());
    }

    public static CompatIdentifier getNetherId() {
        return CompatIdentifier.fromMinecraft(World.NETHER.getValue());
    }

    public static CompatIdentifier getEndId() {
        return CompatIdentifier.fromMinecraft(World.END.getValue());
    }

    public static long getTimeOfDay(World world) {
        return world.getTimeOfDay();
    }

    public static <T extends Entity> List<T> getEntitiesByClass(World world, Class<T> entityClass, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntitiesByClass(world, entityClass, box, EntityPredicates.VALID_ENTITY);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(World world, Class<T> entityClass, Box box) {
        return getEntitiesByClass(world, entityClass, box, EntityPredicates.VALID_ENTITY);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(World world, Class<T> entityClass, net.pitan76.mcpitanlib.midohra.util.math.Box box, Predicate<? super T> predicate) {
        return getEntitiesByClass(world, entityClass, box.toMinecraft(), predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(World world, Class<T> entityClass, Vector3d center, double radius, Predicate<? super T> predicate) {
        Box box = BoxUtil.createBox(center.x - radius, center.y - radius, center.z - radius, center.x + radius, center.y + radius, center.z + radius);
        return getEntitiesByClass(world, entityClass, box, predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(World world, Class<T> entityClass, Vector3d center, double radius) {
        return getEntitiesByClass(world, entityClass, center, radius, EntityPredicates.VALID_ENTITY);
    }

    public static List<LivingEntity> getMonsters(World world, Box box) {
        return world.getEntitiesByClass(LivingEntity.class, box, entity -> entity instanceof Monster);
    }

    public static List<LivingEntity> getMonsters(World world, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getMonsters(world, box.toMinecraft());
    }

    public static List<LivingEntity> getMonsters(World world, Vector3d center, double radius) {
        Box box = BoxUtil.createBox(center.x - radius, center.y - radius, center.z - radius, center.x + radius, center.y + radius, center.z + radius);
        return getMonsters(world, box);
    }

    public static List<?> getEntitiesByType(World world, EntityTypeWrapper filter, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntitiesByType(world, filter, box.toMinecraft());
    }

    public static List<?> getEntitiesByType(World world, EntityTypeWrapper filter, net.pitan76.mcpitanlib.midohra.util.math.Box box, Predicate<? super Entity> predicate) {
        return getEntitiesByType(world, filter, box.toMinecraft(), predicate);
    }

    /**
     * ParticleType is a ParticleEffect only.
     */
    public static void addParticle(World world, CompatParticleType parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        if (parameters.getRaw() instanceof ParticleEffect)
            addParticle(world, (ParticleEffect) parameters.getRaw(), x, y, z, velocityX, velocityY, velocityZ);
    }
}
