package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;

import java.util.Random;

public class BlockScheduledTickEvent extends BaseEvent {
    public BlockState state;
    public ServerWorld world;
    public BlockPos pos;
    public CompatRandom random;

    public BlockScheduledTickEvent(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.random = new CompatRandom(random);
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public ServerWorld getWorld() {
        return world;
    }

    public CompatRandom getRandom() {
        return random;
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(getWorld(), getPos());
    }
}
