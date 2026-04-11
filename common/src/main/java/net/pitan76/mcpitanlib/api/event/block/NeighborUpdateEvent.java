package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.redstone.Orientation;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import org.jetbrains.annotations.Nullable;

public class NeighborUpdateEvent extends BaseEvent {
    public BlockState state;
    public Level world;
    public BlockPos pos;
    public Block sourceBlock;

    @Nullable
    public BlockPos sourcePos;

    @Nullable
    public Orientation wireOrientation;

    public boolean notify;

    public NeighborUpdateEvent(BlockState state, Level world, BlockPos pos, Block sourceBlock, @Nullable BlockPos sourcePos, boolean notify) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.sourceBlock = sourceBlock;
        this.sourcePos = sourcePos;
        this.notify = notify;
    }

    public NeighborUpdateEvent(BlockState state, Level world, BlockPos pos, Block sourceBlock, @Nullable Orientation wireOrientation, boolean notify) {
        this(state, world, pos, sourceBlock, (BlockPos) null, notify);
        this.wireOrientation = wireOrientation;
    }

    public BlockState getState() {
        return state;
    }

    public Level getWorld() {
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
    public @Nullable Orientation getWireOrientation() {
        return wireOrientation;
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
