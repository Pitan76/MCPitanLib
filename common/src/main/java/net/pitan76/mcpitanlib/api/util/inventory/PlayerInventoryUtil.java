package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.entity.player.PlayerInventory;
import net.pitan76.mcpitanlib.api.entity.Player;

public class PlayerInventoryUtil {
    public static Player getPlayer(PlayerInventory playerInventory) {
        return new Player(playerInventory.player);
    }

    public static int getSelectedSlot(PlayerInventory playerInventory) {
        return playerInventory.selectedSlot;
    }

    public static void setSelectedSlot(PlayerInventory playerInventory, int slot) {
        playerInventory.selectedSlot = slot;
    }
}
