package net.pitan76.mcpitanlib.api.gui.args;

import net.minecraft.screen.slot.SlotActionType;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.slot.CompatSlotActionType;

public class SlotClickEvent {
    public int slot;
    public int button;
    public SlotActionType actionType;
    public Player player;

    public SlotClickEvent(int slot, int button, SlotActionType actionType, Player player) {
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

    public SlotActionType getRawActionType() {
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
        return actionType == SlotActionType.SWAP;
    }

    public boolean isPickupAction() {
        return actionType == SlotActionType.PICKUP;
    }

    public boolean isQuickCraftAction() {
        return actionType == SlotActionType.QUICK_CRAFT;
    }

    public boolean isQuickMoveAction() {
        return actionType == SlotActionType.QUICK_MOVE;
    }

    public boolean isThrowAction() {
        return actionType == SlotActionType.THROW;
    }
}
