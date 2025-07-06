package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ServerWorldUtil {
    public static void spawnParticles(ServerWorld world, ParticleEffect particle, double x, double y, double z, int count, double velocityX, double velocityY, double velocityZ, double speed) {
        world.spawnParticles(particle, x, y, z, count, velocityX, velocityY, velocityZ, speed);
    }

    public static void spawnParticles(ServerWorld world, Player player, ParticleEffect particle, boolean force, double x, double y, double z, int count, double velocityX, double velocityY, double velocityZ, double speed) {
        if (player.isServer()) {
            world.spawnParticles(player.getServerPlayer().get(), particle, force, x, y, z, count, velocityX, velocityY, velocityZ, speed);
        }
    }

    public static List<ItemStack> getDroppedStacksOnBlock(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity) {
        return Block.getDroppedStacks(state, world, pos, blockEntity);
    }

    public static List<ItemStack> getDroppedStacksOnBlock(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntityWrapper blockEntity) {
        return getDroppedStacksOnBlock(state, world, pos, blockEntity.get());
    }

    public static List<ItemStack> getDroppedStacksOnBlock(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack) {
        return Block.getDroppedStacks(state, world, pos, blockEntity, entity, stack);
    }

    public static ServerChunkManager getChunkManager(ServerWorld world) {
        return world.getChunkManager();
    }
}
