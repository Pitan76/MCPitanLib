package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class TileCreateEvent extends BaseEvent {

    // ～1.16.5
    private BlockGetter blockView;

    public TileCreateEvent(BlockGetter blockView) {
        this.blockView = blockView;
        this.blockPos = null;
        this.blockState = null;
    }

    public BlockGetter getBlockView() {
        return blockView;
    }

    public void setBlockView(BlockGetter blockView) {
        this.blockView = blockView;
    }

    public boolean hasBlockView() {
        return (blockView != null);
    }
    // ----

    // 1.17～
    private BlockPos blockPos;
    private BlockState blockState;

    public TileCreateEvent(BlockPos blockPos, BlockState blockState) {
        this.blockView = null;
        this.blockPos = blockPos;
        this.blockState = blockState;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public boolean hasBlockPos() {
        return (blockPos != null);
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }

    public boolean hasBlockState() {
        return (blockState != null);
    }
    // ----

    public TileCreateEvent(net.pitan76.mcpitanlib.midohra.util.math.BlockPos blockPos, net.pitan76.mcpitanlib.midohra.block.BlockState blockState) {
        this.blockView = null;
        this.blockPos = blockPos.toMinecraft();
        this.blockState = blockState.toMinecraft();
    }

    public TileCreateEvent(net.pitan76.mcpitanlib.midohra.world.BlockView blockView) {
        this(blockView.toMinecraft());
    }

    public net.pitan76.mcpitanlib.midohra.world.BlockView getBlockViewM() {
        return net.pitan76.mcpitanlib.midohra.world.BlockView.of(getBlockView());
    }

    public void setBlockView(net.pitan76.mcpitanlib.midohra.world.BlockView blockView) {
        setBlockView(blockView.toMinecraft());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getBlockPosM() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getBlockPos());
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getBlockStateM() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getBlockState());
    }

    public void setBlockPos(net.pitan76.mcpitanlib.midohra.util.math.BlockPos blockPos) {
        setBlockPos(blockPos.toMinecraft());
    }

    public void setBlockState(net.pitan76.mcpitanlib.midohra.block.BlockState blockState) {
        setBlockState(blockState.toMinecraft());
    }
}