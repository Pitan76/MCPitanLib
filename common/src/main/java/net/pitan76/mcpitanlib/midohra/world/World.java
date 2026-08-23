package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.RegistryLookupUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.particle.CompatParticleType;
import net.pitan76.mcpitanlib.api.util.particle.effect.CompatParticleEffect;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.RecipeManager;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class World extends WorldAccess {

    private final net.minecraft.world.level.Level world;

    protected World(net.minecraft.world.level.Level world) {
        super(null);
        this.world = world;
    }

    public static World of(net.minecraft.world.level.Level world) {
        return new World(world);
    }

    @Override
    public net.minecraft.world.level.Level getRaw() {
        return world;
    }

    public net.minecraft.world.level.Level toMinecraft() {
        return getRaw();
    }

    public void addBlockEntity(BlockEntityWrapper blockEntity) {
        addBlockEntity(blockEntity.get());
    }

    public void addBlockEntity(BlockEntity blockEntity) {
        getRaw().setBlockEntity(blockEntity);
    }

    public void removeBlockEntity(BlockPos pos) {
        getRaw().removeBlockEntity(pos.toMinecraft());
    }

    public long getTime() {
        return WorldUtil.getTime(getRaw());
    }

    public long getTopY() {
        return WorldUtil.getTopY(getRaw());
    }

    public long getBottomY() {
        return WorldUtil.getBottomY(getRaw());
    }

    public long getDimensionHeight() {
        return WorldUtil.getDimensionHeight(getRaw());
    }

    public CompatIdentifier getId() {
        return WorldUtil.getCompatWorldId(getRaw());
    }

    public FluidState getRawFluidState(BlockPos pos) {
        return WorldUtil.getFluidState(getRaw(), pos.toMinecraft());
    }

    public Player getPlayerByUUID(UUID uuid) {
        return WorldUtil.getPlayer(getRaw(), uuid);
    }

    public List<Player> getPlayers() {
        return WorldUtil.getPlayers(getRaw());
    }

    public BlockPos getSpawnPos() {
        return BlockPos.of(WorldUtil.getSpawnPos(getRaw()));
    }

    public Optional<World> getWorld(CompatIdentifier id) {
        Optional<net.minecraft.server.level.ServerLevel> optional = WorldUtil.getWorld(getRaw(), id);
        return optional.map(World::of);
    }

    public Optional<ServerWorld> getServerWorld(CompatIdentifier id) {
        Optional<net.minecraft.server.level.ServerLevel> optional = WorldUtil.getWorld(getRaw(), id);
        return optional.map(ServerWorld::of);
    }

    public void spawnEntity(Entity entity) {
        WorldUtil.spawnEntity(getRaw(), entity);
    }

    public void spawnStack(net.minecraft.world.item.ItemStack stack, BlockPos pos) {
        WorldUtil.spawnStack(getRaw(), pos.toMinecraft(), stack);
    }

    public RecipeManager getRecipeManager() {
        return RecipeManager.of(getRaw().recipeAccess());
    }

    @Deprecated
    @Override
    public void playSound(net.minecraft.world.entity.player.Player playerEntity, net.minecraft.core.BlockPos pos, SoundEvent sound, SoundSource category) {
        getRaw().playSound(playerEntity, pos, sound, category);
    }

    @Deprecated
    @Override
    public void playSound(net.minecraft.world.entity.player.Player playerEntity, net.minecraft.core.BlockPos pos, SoundEvent sound, SoundSource category, float volume, float pitch) {
        getRaw().playSound(playerEntity, pos, sound, category, volume, pitch);
    }

    @Override
    public void playSound(Player player, BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        WorldUtil.playSound(getRaw(), player, pos.toMinecraft(), soundEvent, category, volume, pitch);
    }

    @Override
    public void playSound(BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category) {
        playSound(null, pos, soundEvent, category, 1f, 1f);
    }

    @Override
    public void playSound(Player player, BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category) {
        playSound(player, pos, soundEvent, category, 1f, 1f);
    }

    @Override
    public void playSound(BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        playSound(null, pos, soundEvent, category, volume, pitch);
    }

    public Optional<ServerWorld> toServerWorld() {
        if (getRaw() instanceof net.minecraft.server.level.ServerLevel) {
            return Optional.of(ServerWorld.of((net.minecraft.server.level.ServerLevel) getRaw()));
        }
        return Optional.empty();
    }

    public CompatRegistryLookup getRegistryLookup() {
        return RegistryLookupUtil.getRegistryLookup(getRaw());
    }

    public boolean isAir(BlockPos pos) {
        return WorldUtil.isAir(getRaw(), pos.toMinecraft());
    }

    public Block getBlock(BlockPos pos) {
        return WorldUtil.getBlock(getRaw(), pos.toMinecraft());
    }

    public void addParticle(CompatParticleEffect effect, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        WorldUtil.addParticle(getRaw(), effect.getRaw(), x, y, z, velocityX, velocityY, velocityZ);
    }

    public void addParticle(CompatParticleEffect effect, Vector3d pos, Vector3d velocity) {
        addParticle(effect, pos.getX(), pos.getY(), pos.getZ(), velocity.getX(), velocity.getY(), velocity.getZ());
    }

    public boolean isRaining() {
        return WorldUtil.isRaining(getRaw());
    }

    public boolean isThundering() {
        return WorldUtil.isThundering(getRaw());
    }

    public boolean hasSkyLight() {
        return WorldUtil.hasSkyLight(getRaw());
    }

    public boolean isSkyVisible(BlockPos pos) {
        return WorldUtil.isSkyVisible(getRaw(), pos.toMinecraft());
    }

    public boolean isDay() {
        return WorldUtil.isDay(getRaw());
    }

    public float getSkyAngle(float partialTicks) {
        return WorldUtil.getSkyAngle(getRaw(), partialTicks);
    }

    public float getSkyAngle() {
        return getSkyAngle(0);
    }

    public void dropStackOnBlock(BlockPos pos, ItemStack stack) {
        WorldUtil.dropStackOnBlock(getRaw(), pos.toMinecraft(), stack.toMinecraft());
    }

    public void spawnStack(ItemStack stack, BlockPos pos) {
        WorldUtil.spawnStack(getRaw(), pos.toMinecraft(), stack.toMinecraft());
    }

    public void addParticle(CompatParticleType type, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        WorldUtil.addParticle(getRaw(), type, x, y, z, velocityX, velocityY, velocityZ);
    }

    public void addParticle(CompatParticleType type, Vector3d pos, Vector3d velocity) {
        addParticle(type, pos.getX(), pos.getY(), pos.getZ(), velocity.getX(), velocity.getY(), velocity.getZ());
    }

    public void sendEntityStatus(EntityWrapper entity, byte status) {
        WorldUtil.sendEntityStatus(getRaw(), entity.get(), status);
    }

    public net.pitan76.mcpitanlib.midohra.world.chunk.ChunkWrapper getChunk(BlockPos pos) {
        return net.pitan76.mcpitanlib.midohra.world.chunk.ChunkWrapper.of(this, pos);
    }

    public net.pitan76.mcpitanlib.midohra.world.chunk.ChunkWrapper getChunk(int chunkX, int chunkZ) {
        return net.pitan76.mcpitanlib.midohra.world.chunk.ChunkWrapper.of(this, chunkX, chunkZ);
    }

    public net.pitan76.mcpitanlib.midohra.world.chunk.ChunkSectionWrapper getChunkSection(BlockPos pos) {
        return getChunk(pos).getSectionAt(pos);
    }
}
