package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.world.ServerWorldUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.ServerRecipeManager;
import net.pitan76.mcpitanlib.midohra.server.MCServer;
import net.pitan76.mcpitanlib.midohra.server.PlayerManager;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.ChunkPos;
import net.pitan76.mcpitanlib.midohra.world.chunk.ChunkTicketType;
import net.pitan76.mcpitanlib.midohra.world.chunk.ServerChunkManager;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class ServerWorld extends World {
    private final net.minecraft.server.level.ServerLevel world;

    protected ServerWorld(net.minecraft.server.level.ServerLevel world) {
        super(null);
        this.world = world;
    }

    public static ServerWorld of(net.minecraft.server.level.ServerLevel world) {
        return new ServerWorld(world);
    }

    @Override
    public net.minecraft.server.level.ServerLevel getRaw() {
        return world;
    }

    @Override
    public net.minecraft.server.level.ServerLevel toMinecraft() {
        return getRaw();
    }

    public void playSound(Player player, BlockPos pos, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch, long seed) {
        getRaw().playSeededSound(player.getEntity(), pos.getX(), pos.getY(), pos.getZ(), sound.getEntry(), category.get(), volume, pitch, seed);
    }

    public void playSoundFromEntity(Player player, Player target, CompatSoundEvent sound, CompatSoundCategory category, float volume, float pitch, long seed) {
        getRaw().playSeededSound(player.getEntity(), target.getEntity(), sound.getEntry(), category.get(), volume, pitch, seed);
    }

    public MinecraftServer getServer() {
        return getRaw().getServer();
    }

    public MCServer getMCServer() {
        return MCServer.of(getServer());
    }

    public PlayerManager getPlayerManager() {
        return PlayerManager.of(this);
    }

    public ServerChunkManager getChunkManager() {
        return ServerChunkManager.of(this);
    }

    @Override
    public ServerRecipeManager getRecipeManager() {
        return ServerRecipeManager.of(this);
    }

    public void addTicket(ChunkTicketType<?> type, ChunkPos pos, int radius) {
        WorldUtil.addTicket(getRaw(), type, pos.getRaw(), radius);
    }

    public void removeTicket(ChunkTicketType<?> type, ChunkPos pos, int radius) {
        WorldUtil.removeTicket(getRaw(), type, pos.getRaw(), radius);
    }

    public PersistentStateManager getPersistentStateManager() {
        return getChunkManager().getPersistentStateManager();
    }

    public List<ItemStack> getDroppedStacksOnBlock(BlockState state, BlockPos pos, @Nullable BlockEntityWrapper blockEntity) {
        return ServerWorldUtil.getDroppedStacksOnBlock(state.toMinecraft(), getRaw(), pos.toMinecraft(), blockEntity)
                .stream().map(ItemStack::of).collect(Collectors.toList());
    }

    public List<ItemStack> getDroppedStacksOnBlock(BlockState state, BlockPos pos) {
        return getDroppedStacksOnBlock(state, pos, null);
    }

    public List<ItemStack> getDroppedStacksOnBlock(BlockState state, BlockPos pos, @Nullable BlockEntityWrapper blockEntity, @Nullable EntityWrapper entity, ItemStack stack) {
        return ServerWorldUtil.getDroppedStacksOnBlock(state.toMinecraft(), getRaw(), pos.toMinecraft(), blockEntity == null ? null : blockEntity.get(), entity == null ? null : entity.get(), stack.toMinecraft())
                .stream().map(ItemStack::of).collect(Collectors.toList());
    }
}
