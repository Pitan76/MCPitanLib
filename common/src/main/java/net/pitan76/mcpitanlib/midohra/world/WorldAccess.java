package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import net.pitan76.mcpitanlib.api.util.world.WorldAccessUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

import java.util.Optional;

public class WorldAccess {
    private final net.minecraft.world.WorldAccess world;

    protected WorldAccess(net.minecraft.world.WorldAccess world) {
        this.world = world;
    }

    public static WorldAccess of(net.minecraft.world.WorldAccess world) {
        return new WorldAccess(world);
    }

    protected net.minecraft.world.WorldAccess getWorld() {
        return world;
    }

    public net.minecraft.world.WorldAccess toMinecraft() {
        return getWorld();
    }

    public boolean isClient() {
        return WorldAccessUtil.isClient(getWorld());
    }

    public boolean isServer() {
        return !isClient();
    }

    public CompatRandom getRandom() {
        return new CompatRandom(getWorld().getRandom());
    }

    public MinecraftServer getServer() {
        return WorldAccessUtil.getServer(getWorld());
    }

    public BlockEntityWrapper getBlockEntity(BlockPos pos) {
        return BlockEntityWrapper.of(WorldAccessUtil.getBlockEntity(getWorld(), pos.toMinecraft()));
    }

    public <T extends BlockEntity> Optional<T> getRawBlockEntity(BlockPos pos, BlockEntityType<T> type) {
        return WorldAccessUtil.getBlockEntity(getWorld(), pos.toMinecraft(), type);
    }

    public <T extends BlockEntity> BlockEntityWrapper getBlockEntity(BlockPos pos, BlockEntityType<T> type) {
        Optional<T> blockEntity = WorldAccessUtil.getBlockEntity(getWorld(), pos.toMinecraft(), type);
        return blockEntity.map(BlockEntityWrapper::of).orElse(BlockEntityWrapper.of());
    }

    public boolean removeBlock(BlockPos pos, boolean move) {
        return WorldAccessUtil.removeBlock(getWorld(), pos.toMinecraft(), move);
    }

    public boolean breakBlock(BlockPos pos, boolean drop) {
        return WorldAccessUtil.breakBlock(getWorld(), pos.toMinecraft(), drop);
    }

    public boolean breakBlock(BlockPos pos, boolean drop, Entity entity) {
        return WorldAccessUtil.breakBlock(getWorld(), pos.toMinecraft(), drop, entity);
    }

    public BlockState getBlockState(BlockPos pos) {
        return BlockState.of(WorldAccessUtil.getBlockState(getWorld(), pos.toMinecraft()));
    }

    public boolean setBlockState(BlockPos pos, BlockState state, int flags) {
        return WorldAccessUtil.setBlockState(getWorld(), pos.toMinecraft(), state.toMinecraft(), flags);
    }

    public boolean setBlockState(BlockPos pos, BlockState state, int flags, int maxUpdateDepth) {
        return WorldAccessUtil.setBlockState(getWorld(), pos.toMinecraft(), state.toMinecraft(), flags, maxUpdateDepth);
    }

    public boolean setBlockState(BlockPos pos, BlockState state) {
        return WorldAccessUtil.setBlockState(getWorld(), pos.toMinecraft(), state.toMinecraft());
    }

    @Deprecated
    public void playSound(PlayerEntity playerEntity, net.minecraft.util.math.BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch) {
        getWorld().playSound(playerEntity, pos, sound, category, volume, pitch);
    }

    @Deprecated
    public void playSound(PlayerEntity playerEntity, net.minecraft.util.math.BlockPos pos, SoundEvent sound, SoundCategory category) {
        getWorld().playSound(playerEntity, pos, sound, category);
    }

    public void playSound(Player player, BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        playSound(player.getEntity(), pos.toMinecraft(), soundEvent.get(), category.get(), volume, pitch);
    }

    public void playSound(Player player, BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category) {
        playSound(player.getEntity(), pos.toMinecraft(), soundEvent.get(), category.get());
    }

    public void playSound(BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        playSound(null, pos.toMinecraft(), soundEvent.get(), category.get(), volume, pitch);
    }

    public void playSound(BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category) {
        playSound(null, pos.toMinecraft(), soundEvent.get(), category.get());
    }
}
