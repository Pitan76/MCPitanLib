package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

public class NeighborUpdateEvent extends BaseEvent {
    public BlockState state;
    public World world;
    public BlockPos pos;
    public Block sourceBlock;

    @Nullable
    public BlockPos sourcePos;

    @Nullable
    public WireOrientation wireOrientation;

    public boolean notify;

    public NeighborUpdateEvent(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable BlockPos sourcePos, boolean notify) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.sourceBlock = sourceBlock;
        this.sourcePos = sourcePos;
        this.notify = notify;
    }

    public NeighborUpdateEvent(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        this(state, world, pos, sourceBlock, (BlockPos) null, notify);
        this.wireOrientation = wireOrientation;
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

    public @Nullable BlockPos getSourcePos() {
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

    @Deprecated
    public @Nullable WireOrientation getWireOrientation() {
        return wireOrientation;
    }
}
