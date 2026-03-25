package net.pitan76.mcpitanlib.api.gui.args;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;

public class CreateMenuEvent {
    public int syncId;
    public Inventory playerInventory;
    public Player player;

    public CreateMenuEvent(int syncId, Inventory playerInventory, Player player) {
        this.syncId = syncId;
        this.playerInventory = playerInventory;
        this.player = player;
    }

    public CreateMenuEvent(int syncId, Inventory playerInventory) {
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

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public Player getPlayerEntity() {
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

    public Level getWorld() {
        return getPlayer().getWorld();
    }
}
