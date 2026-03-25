package net.pitan76.mcpitanlib.api.gui.slot;

import net.minecraft.world.inventory.ClickType;

public class CompatSlotActionType {
    public static final CompatSlotActionType PICKUP = new CompatSlotActionType(ClickType.PICKUP);
    public static final CompatSlotActionType QUICK_MOVE = new CompatSlotActionType(ClickType.QUICK_MOVE);
    public static final CompatSlotActionType SWAP = new CompatSlotActionType(ClickType.SWAP);
    public static final CompatSlotActionType THROW = new CompatSlotActionType(ClickType.THROW);
    public static final CompatSlotActionType CLONE = new CompatSlotActionType(ClickType.CLONE);
    public static final CompatSlotActionType QUICK_CRAFT = new CompatSlotActionType(ClickType.QUICK_CRAFT);
    public static final CompatSlotActionType PICKUP_ALL = new CompatSlotActionType(ClickType.PICKUP_ALL);
    
    public final ClickType raw;
    
    protected CompatSlotActionType(ClickType raw) {
        this.raw = raw;
    }

    public static CompatSlotActionType of(ClickType raw) {
        if (raw == ClickType.PICKUP) return PICKUP;
        if (raw == ClickType.QUICK_MOVE) return QUICK_MOVE;
        if (raw == ClickType.SWAP) return SWAP;
        if (raw == ClickType.THROW) return THROW;
        if (raw == ClickType.CLONE) return CLONE;
        if (raw == ClickType.QUICK_CRAFT) return QUICK_CRAFT;
        if (raw == ClickType.PICKUP_ALL) return PICKUP_ALL;
        return new CompatSlotActionType(raw);
    }

    public boolean isSwapOrPickupOrQuickMoveOrThrow() {
        return raw == ClickType.SWAP || raw == ClickType.PICKUP || raw == ClickType.QUICK_MOVE || raw == ClickType.THROW;
    }

    public ClickType getRaw() {
        return raw;
    }
    
    public int getIndex() {
        return raw.id();
    }

    public String getName() {
        return raw.name();
    }

    public boolean isOf(CompatSlotActionType type) {
        return raw == type.raw;
    }
}
