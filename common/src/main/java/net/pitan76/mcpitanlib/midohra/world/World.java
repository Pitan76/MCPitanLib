package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.recipe.RecipeManager;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class World extends WorldAccess {

    private final net.minecraft.world.World world;

    protected World(net.minecraft.world.World world) {
        super(null);
        this.world = world;
    }

    public static World of(net.minecraft.world.World world) {
        return new World(world);
    }

    @Override
    public net.minecraft.world.World getRaw() {
        return world;
    }

    public net.minecraft.world.World toMinecraft() {
        return getRaw();
    }

    public void addBlockEntity(BlockEntityWrapper blockEntity) {
        addBlockEntity(blockEntity.get());
    }

    public void addBlockEntity(BlockEntity blockEntity) {
        getRaw().addBlockEntity(blockEntity);
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
        Optional<ServerWorld> optional = WorldUtil.getWorld(getRaw(), id);
        return optional.map(World::of);
    }

    public void spawnEntity(Entity entity) {
        WorldUtil.spawnEntity(getRaw(), entity);
    }

    public void spawnStack(net.minecraft.item.ItemStack stack, BlockPos pos) {
        WorldUtil.spawnStack(getRaw(), pos.toMinecraft(), stack);
    }

    public RecipeManager getRecipeManager() {
        return RecipeManager.of(getRaw().getRecipeManager());
    }

    @Deprecated
    @Override
    public void playSound(PlayerEntity playerEntity, net.minecraft.util.math.BlockPos pos, SoundEvent sound, SoundCategory category) {
        getRaw().playSound(playerEntity, pos, sound, category);
    }

    @Deprecated
    @Override
    public void playSound(PlayerEntity playerEntity, net.minecraft.util.math.BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch) {
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
}
