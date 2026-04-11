package net.pitan76.mcpitanlib.api.gui.args;

import net.minecraft.world.inventory.ContainerInput;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.slot.CompatSlotActionType;

public class SlotClickEvent {
    public int slot;
    public int button;
    public ContainerInput actionType;
    public Player player;

    public SlotClickEvent(int slot, int button, ContainerInput actionType, Player player) {
        this.slot = slot;
        this.button = button;
        this.actionType = actionType;
        this.player = player;
    }

    public int getSlot() {
        return slot;
    }

    public int getButton() {
        return button;
    }

    public ContainerInput getRawActionType() {
        return actionType;
    }

    public CompatSlotActionType getActionType() {
        return CompatSlotActionType.of(actionType);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isClient() {
        return player.isClient();
    }

    public boolean isServer() {
        return player.isServer();
    }

    public boolean isSwapAction() {
        return actionType == ContainerInput.SWAP;
    }

    public boolean isPickupAction() {
        return actionType == ContainerInput.PICKUP;
    }

    public boolean isQuickCraftAction() {
        return actionType == ContainerInput.QUICK_CRAFT;
    }

    public boolean isQuickMoveAction() {
        return actionType == ContainerInput.QUICK_MOVE;
    }

    public boolean isThrowAction() {
        return actionType == ContainerInput.THROW;
    }
}
