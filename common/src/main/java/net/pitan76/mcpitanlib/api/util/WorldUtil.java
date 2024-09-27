package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
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

    public static void scheduleBlockTick(World world, BlockPos pos, Block block, int delay) {
        world.getBlockTickScheduler().schedule(pos, block, delay);
    }

    public static void scheduleFluidTick(World world, BlockPos pos, Fluid fluid, int delay) {
        world.getFluidTickScheduler().schedule(pos, fluid, delay);
    }

    public static boolean isServer(World world) {
        return !isClient(world);
    }

    public static void spawnStack(World world, BlockPos pos, ItemStack stack) {
        spawnEntity(world, new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack));
    }

    public static void spawnExperienceOrb(World world, Vec3d pos, int amount) {
        if (world instanceof ServerWorld)
            spawnEntity(world, new ExperienceOrbEntity(world, pos.getX(), pos.getY(), pos.getZ(), amount));
    }

    public static void spawnExperienceOrb(World world, BlockPos pos, int amount) {
        spawnExperienceOrb(world, Vec3d.ofCenter(pos), amount);
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

    public static void playSound(World world, double x, double y, double z, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch, boolean useDistance) {
        world.playSound(x, y, z, sound.get(), category.get(), volume, pitch, useDistance);
    }

    public static void sendEntityStatus(World world, Entity entity, byte status) {
        world.sendEntityStatus(entity, status);
    }

    public static BlockPos getSpawnPos(World world) {
        return new BlockPos(world.getLevelProperties().getSpawnX(), world.getLevelProperties().getSpawnY(), world.getLevelProperties().getSpawnZ());
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
        return server.getWorld(RegistryKey.of(Registry.WORLD_KEY, worldId));
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

    public static <T> void addTicket(ServerWorld world, ChunkTicketType<T> type, ChunkPos pos, int radius, T argument) {
        world.getChunkManager().addTicket(type, pos, radius, argument);
    }

    public static <T> void removeTicket(ServerWorld world, ChunkTicketType<T> type, ChunkPos pos, int radius, T argument) {
        world.getChunkManager().removeTicket(type, pos, radius, argument);
    }

    public static boolean isReceivingRedstonePower(World world, BlockPos pos) {
        return world.isReceivingRedstonePower(pos);
    }

    public static int getBottomY(World world) {
        return 0;
    }

    public static int getTopY(World world) {
        return world.getTopY(Heightmap.Type.MOTION_BLOCKING, 0, 0);
    }

    public static int getDimensionHeight(World world) {
        return world.getDimension().getLogicalHeight();
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
        return world.canSetBlock(pos);
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

    public static <T extends Entity> List<T> getEntitiesByType(World world, EntityType<T> type, Box box, Predicate<? super T> predicate) {
        return world.getEntitiesByType(type, box, predicate);
    }

    public static <T extends Entity> List<T> getEntitiesByClass(World world, Class<T> entityClass, Box box, Predicate<? super T> predicate) {
        return world.getEntitiesByClass(entityClass, box, predicate);
    }
}
