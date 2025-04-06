package net.pitan76.mcpitanlib.api.gui.slot;

import net.minecraft.screen.slot.SlotActionType;

public class CompatSlotActionType {
    public static final CompatSlotActionType PICKUP = new CompatSlotActionType(SlotActionType.PICKUP);
    public static final CompatSlotActionType QUICK_MOVE = new CompatSlotActionType(SlotActionType.QUICK_MOVE);
    public static final CompatSlotActionType SWAP = new CompatSlotActionType(SlotActionType.SWAP);
    public static final CompatSlotActionType THROW = new CompatSlotActionType(SlotActionType.THROW);
    public static final CompatSlotActionType CLONE = new CompatSlotActionType(SlotActionType.CLONE);
    public static final CompatSlotActionType QUICK_CRAFT = new CompatSlotActionType(SlotActionType.QUICK_CRAFT);
    public static final CompatSlotActionType PICKUP_ALL = new CompatSlotActionType(SlotActionType.PICKUP_ALL);
    
    public final SlotActionType raw;
    
    protected CompatSlotActionType(SlotActionType raw) {
        this.raw = raw;
    }

    public static CompatSlotActionType of(SlotActionType raw) {
        if (raw == SlotActionType.PICKUP) return PICKUP;
        if (raw == SlotActionType.QUICK_MOVE) return QUICK_MOVE;
        if (raw == SlotActionType.SWAP) return SWAP;
        if (raw == SlotActionType.THROW) return THROW;
        if (raw == SlotActionType.CLONE) return CLONE;
        if (raw == SlotActionType.QUICK_CRAFT) return QUICK_CRAFT;
        if (raw == SlotActionType.PICKUP_ALL) return PICKUP_ALL;
        return new CompatSlotActionType(raw);
    }

    public boolean isSwapOrPickupOrQuickMoveOrThrow() {
        return raw == SlotActionType.SWAP || raw == SlotActionType.PICKUP || raw == SlotActionType.QUICK_MOVE || raw == SlotActionType.THROW;
    }

    public SlotActionType getRaw() {
        return raw;
    }
    
    public int getIndex() {
        return raw.getIndex();
    }

    public String getName() {
        return raw.name();
    }

    public boolean isOf(CompatSlotActionType type) {
        return raw == type.raw;
    }
}
