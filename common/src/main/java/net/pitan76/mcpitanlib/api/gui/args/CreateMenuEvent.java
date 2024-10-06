package net.pitan76.mcpitanlib.api.gui.args;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;

public class CreateMenuEvent {
    public int syncId;
    public PlayerInventory playerInventory;
    public PlayerEntity player;

    public CreateMenuEvent(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        this.syncId = syncId;
        this.playerInventory = playerInventory;
        this.player = player;
    }

    public CreateMenuEvent(int syncId, PlayerInventory playerInventory) {
        this.syncId = syncId;
        this.playerInventory = playerInventory;
        this.player = playerInventory.player;
    }

    public CreateMenuEvent(int syncId, Player player) {
        this.syncId = syncId;
        this.playerInventory = player.getInv();
        this.player = player.getEntity();
    }

    public int getSyncId() {
        return syncId;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public PlayerEntity getPlayerEntity() {
        return player;
    }

    public Player getPlayer() {
        return new Player(player);
    }

    public boolean isExistPlayer() {
        return player != null;
    }

    public boolean isClient() {
        return getPlayer().isClient();
    }

    public World getWorld() {
        return getPlayer().getWorld();
    }
}
