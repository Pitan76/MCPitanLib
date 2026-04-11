package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import org.jetbrains.annotations.Nullable;

public class PickStackEvent {

    @Deprecated
    public LevelReader worldView;

    @Deprecated
    public BlockGetter blockView;

    public BlockPos pos;
    public BlockState state;

    public boolean includeData = true;

    public PickStackEvent(LevelReader world, BlockPos pos, BlockState state) {
        this.worldView = world;
        this.pos = pos;
        this.state = state;
    }

    public PickStackEvent(BlockGetter world, BlockPos pos, BlockState state) {
        this.blockView = world;
        this.pos = pos;
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    @Nullable
    public BlockGetter getBlockView() {
        return blockView;
    }

    @Nullable
    public LevelReader getWorldView() {
        return worldView;
    }

    /**
     * check if the block has a block entity
     * @return boolean
     */
    public boolean hasBlockEntity() {
        return getBlockEntity() != null;
    }

    /**
     * @return BlockEntity
     */
    public BlockEntity getBlockEntity() {
        if (blockView != null)
            return blockView.getBlockEntity(pos);
        if (worldView != null)
            return worldView.getBlockEntity(pos);
        return null;
    }

    public boolean isClient() {
        if (blockView != null)
            return getBlockEntity().getLevel().isClientSide();
        if (worldView != null)
            return worldView.isClientSide();

        try {
            net.minecraft.client.Minecraft.getInstance();
            return true;
        } catch (Error e) {
            return false;
        }
    }

    public void setIncludeData(boolean includeData) {
        this.includeData = includeData;
    }

    public boolean isIncludeData() {
        return includeData;
    }
}
