package net.pitan76.mcpitanlib.api.gui.args;

import net.minecraft.world.inventory.ClickType;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.slot.CompatSlotActionType;

public class SlotClickEvent {
    public int slot;
    public int button;
    public ClickType actionType;
    public Player player;

    public SlotClickEvent(int slot, int button, ClickType actionType, Player player) {
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

    public ClickType getRawActionType() {
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
        return actionType == ClickType.SWAP;
    }

    public boolean isPickupAction() {
        return actionType == ClickType.PICKUP;
    }

    public boolean isQuickCraftAction() {
        return actionType == ClickType.QUICK_CRAFT;
    }

    public boolean isQuickMoveAction() {
        return actionType == ClickType.QUICK_MOVE;
    }

    public boolean isThrowAction() {
        return actionType == ClickType.THROW;
    }
}
