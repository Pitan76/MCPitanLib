package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ServerWorldUtil {
    public static void spawnParticles(ServerLevel world, ParticleOptions particle, double x, double y, double z, int count, double velocityX, double velocityY, double velocityZ, double speed) {
        world.sendParticles(particle, x, y, z, count, velocityX, velocityY, velocityZ, speed);
    }

    public static void spawnParticles(ServerLevel world, Player player, ParticleOptions particle, boolean force, double x, double y, double z, int count, double velocityX, double velocityY, double velocityZ, double speed) {
        if (player.isServer()) {
            world.sendParticles(player.getServerPlayer().get(), particle, force, false, x, y, z, count, velocityX, velocityY, velocityZ, speed);
        }
    }

    public static List<ItemStack> getDroppedStacksOnBlock(BlockState state, ServerLevel world, BlockPos pos, @Nullable BlockEntity blockEntity) {
        return Block.getDrops(state, world, pos, blockEntity);
    }

    public static List<ItemStack> getDroppedStacksOnBlock(BlockState state, ServerLevel world, BlockPos pos, @Nullable BlockEntityWrapper blockEntity) {
        return getDroppedStacksOnBlock(state, world, pos, blockEntity.get());
    }

    public static List<ItemStack> getDroppedStacksOnBlock(BlockState state, ServerLevel world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack) {
        return Block.getDrops(state, world, pos, blockEntity, entity, stack);
    }

    public static ServerChunkCache getChunkManager(ServerLevel world) {
        return world.getChunkSource();
    }
}
