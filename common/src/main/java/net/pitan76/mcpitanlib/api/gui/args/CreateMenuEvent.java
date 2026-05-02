package net.pitan76.mcpitanlib.api.gui.args;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;

public class CreateMenuEvent {
    public int syncId;
    public Inventory playerInventory;
    public net.minecraft.world.entity.player.Player player;

    public CreateMenuEvent(int syncId, Inventory playerInventory, net.minecraft.world.entity.player.Player player) {
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

    public net.minecraft.world.entity.player.Player getPlayerEntity() {
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

    public CompatPlayerInventory getCompatPlayerInventory() {
        return new CompatPlayerInventory(getPlayerInventory());
    }

    public net.pitan76.mcpitanlib.midohra.world.World getWorldM() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(getWorld());
    }
}
