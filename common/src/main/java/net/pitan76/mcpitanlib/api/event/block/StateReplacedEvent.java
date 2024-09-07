package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class StateReplacedEvent extends BaseEvent {

    public BlockState state;
    public World world;
    public BlockPos pos;
    public BlockState newState;
    public boolean moved;

    public StateReplacedEvent(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.newState = newState;
        this.moved = moved;
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

    public BlockState getNewState() {
        return newState;
    }

    public boolean isMoved() {
        return moved;
    }

    public boolean isClient() {
        return world.isClient();
    }

    /**
     * check if the block is the same state
     * @return boolean
     */
    public boolean isSameState() {
        return state.isOf(newState.getBlock());
    }

    /**
     * check if the block has a block entity
     * @return BlockEntity
     */
    public boolean hasBlockEntity() {
        return WorldUtil.hasBlockEntity(world, pos);
    }

    /**
     * get the block entity
     * @return BlockEntity
     */
    public BlockEntity getBlockEntity() {
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
        return getBlockEntity() instanceof Inventory;
    }

    /**
     * update the comparators
     */
    public void updateComparators() {
        WorldUtil.updateComparators(getWorld(), getPos(), getState().getBlock());
    }
}
