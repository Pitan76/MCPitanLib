package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;

public class BlockScheduledTickEvent extends BaseEvent implements BlockStatePropertyHolder {
    public BlockState state;
    public ServerLevel world;
    public BlockPos pos;
    public CompatRandom random;

    public BlockScheduledTickEvent(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
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

    public ServerLevel getWorld() {
        return world;
    }

    public CompatRandom getRandom() {
        return random;
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(getWorld(), getPos());
    }

    @Override
    public net.pitan76.mcpitanlib.midohra.block.BlockState getBlockState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(state);
    }
}
