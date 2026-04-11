package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class ScreenHandlerCreateEvent extends BaseEvent {

    public BlockState state;
    public Level world;
    public BlockPos pos;
    public int syncId;
    public Inventory inventory;
    public Player player;

    public ScreenHandlerCreateEvent(BlockState state, Level world, BlockPos pos, int syncId, Inventory inventory, net.minecraft.world.entity.player.Player player) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.syncId = syncId;
        this.inventory = inventory;
        this.player = new Player(player);
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Level getWorld() {
        return world;
    }

    public int getSyncId() {
        return syncId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isClient() {
        return world.isClientSide();
    }
}
