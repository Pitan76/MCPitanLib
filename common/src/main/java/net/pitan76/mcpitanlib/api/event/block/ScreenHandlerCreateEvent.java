package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class ScreenHandlerCreateEvent extends BaseEvent {

    public BlockState state;
    public World world;
    public BlockPos pos;
    public int syncId;
    public PlayerInventory inventory;
    public Player player;

    public ScreenHandlerCreateEvent(BlockState state, World world, BlockPos pos, int syncId, PlayerInventory inventory, PlayerEntity player) {
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

    public World getWorld() {
        return world;
    }

    public int getSyncId() {
        return syncId;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isClient() {
        return world.isClient;
    }
}
