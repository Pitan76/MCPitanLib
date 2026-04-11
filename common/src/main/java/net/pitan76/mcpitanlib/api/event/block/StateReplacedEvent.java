package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.Container;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;

public class StateReplacedEvent extends BaseEvent {

    public BlockState state;
    public Level world;
    public BlockPos pos;
    public BlockState newState;
    public boolean moved;

    // Captured at construction time so getBlockEntity() works even after the world (1.21.x)
    private final BlockEntity cachedBlockEntity;

    public StateReplacedEvent(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.newState = newState;
        this.moved = moved;

        this.cachedBlockEntity = WorldUtil.hasBlockEntity(world, pos) ? WorldUtil.getBlockEntity(world, pos) : null;
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

    public BlockState getNewState() {
        return newState;
    }

    public boolean isMoved() {
        return moved;
    }

    public boolean isClient() {
        return world.isClientSide();
    }

    /**
     * check if the block is the same state
     * @return boolean
     */
    public boolean isSameState() {
        return state.is(newState.getBlock());
    }

    /**
     * check if the block has a block entity
     * @return boolean
     */
    public boolean hasBlockEntity() {
        return getBlockEntity() != null;
    }

    /**
     * get the block entity
     * <p>
     * The block entity is captured at event creation time, so this returns a valid
     * reference even in MC 1.21.x where the world removes the BE before
     * onStateReplaced is invoked.
     * @return BlockEntity
     */
    public BlockEntity getBlockEntity() {
        if (cachedBlockEntity != null) return cachedBlockEntity;
        return WorldUtil.getBlockEntity(world, pos);
    }

    /**
     * spawn the drops in the container
     */
    public void spawnDropsInContainer() {
        if (isSameState() || !hasInventory()) return;

        ItemScattererUtil.spawn(getWorld(), getPos(), getBlockEntity());
        updateComparators();
    }

    public boolean hasInventory() {
        return getBlockEntity() instanceof Container;
    }

    /**
     * update the comparators
     */
    public void updateComparators() {
        WorldUtil.updateComparators(getWorld(), getPos(), getState().getBlock());
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public IWorldView getWorldView() {
        return getMidohraWorld();
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(state);
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(pos);
    }

    public BlockWrapper getBlockWrapper() {
        return BlockWrapper.of(state.getBlock());
    }

    public BlockEntityWrapper getBlockEntityWrapper() {
        return BlockEntityWrapper.of(getBlockEntity());
    }
}
