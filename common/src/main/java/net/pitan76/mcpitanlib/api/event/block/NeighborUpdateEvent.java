package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;

public class NeighborUpdateEvent extends BaseEvent {
    public BlockState state;
    public World world;
    public BlockPos pos;
    public Block sourceBlock;
    public BlockPos sourcePos;
    public boolean notify;

    public NeighborUpdateEvent(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.sourceBlock = sourceBlock;
        this.sourcePos = sourcePos;
        this.notify = notify;
    }

    public BlockState getState() {
        return state;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Block getSourceBlock() {
        return sourceBlock;
    }

    public BlockPos getSourcePos() {
        return sourcePos;
    }

    public boolean isNotify() {
        return notify;
    }

    public boolean isReceivingRedstonePower() {
        return WorldUtil.isReceivingRedstonePower(world, pos);
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(world, pos);
    }

    public boolean hasBlockEntity() {
        return WorldUtil.hasBlockEntity(world, pos);
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(pos);
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(state);
    }

    public BlockEntityWrapper getBlockEntityWrapper() {
        return BlockEntityWrapper.of(getBlockEntity());
    }
}
