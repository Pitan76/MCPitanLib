package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class PickStackEvent {

    @Deprecated
    public WorldView worldView;

    @Deprecated
    public BlockView blockView;

    public BlockPos pos;
    public BlockState state;


    public PickStackEvent(WorldView world, BlockPos pos, BlockState state) {
    }

    public PickStackEvent(BlockView world, BlockPos pos, BlockState state) {
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    @Nullable
    public BlockView getBlockView() {
        return blockView;
    }

    @Nullable
    public WorldView getWorldView() {
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
            return getBlockEntity().getWorld().isClient();
        if (worldView != null)
            return worldView.isClient();

        try {
            net.minecraft.client.MinecraftClient.getInstance();
            return true;
        } catch (Error e) {
            return false;
        }
    }
}
