package net.pitan76.mcpitanlib.api.gui.slot;

import net.minecraft.world.inventory.ContainerInput;

public class CompatSlotActionType {
    public static final CompatSlotActionType PICKUP = new CompatSlotActionType(ContainerInput.PICKUP);
    public static final CompatSlotActionType QUICK_MOVE = new CompatSlotActionType(ContainerInput.QUICK_MOVE);
    public static final CompatSlotActionType SWAP = new CompatSlotActionType(ContainerInput.SWAP);
    public static final CompatSlotActionType THROW = new CompatSlotActionType(ContainerInput.THROW);
    public static final CompatSlotActionType CLONE = new CompatSlotActionType(ContainerInput.CLONE);
    public static final CompatSlotActionType QUICK_CRAFT = new CompatSlotActionType(ContainerInput.QUICK_CRAFT);
    public static final CompatSlotActionType PICKUP_ALL = new CompatSlotActionType(ContainerInput.PICKUP_ALL);
    
    public final ContainerInput raw;
    
    protected CompatSlotActionType(ContainerInput raw) {
        this.raw = raw;
    }

    public static CompatSlotActionType of(ContainerInput raw) {
        if (raw == ContainerInput.PICKUP) return PICKUP;
        if (raw == ContainerInput.QUICK_MOVE) return QUICK_MOVE;
        if (raw == ContainerInput.SWAP) return SWAP;
        if (raw == ContainerInput.THROW) return THROW;
        if (raw == ContainerInput.CLONE) return CLONE;
        if (raw == ContainerInput.QUICK_CRAFT) return QUICK_CRAFT;
        if (raw == ContainerInput.PICKUP_ALL) return PICKUP_ALL;
        return new CompatSlotActionType(raw);
    }

    public boolean isSwapOrPickupOrQuickMoveOrThrow() {
        return raw == ContainerInput.SWAP || raw == ContainerInput.PICKUP || raw == ContainerInput.QUICK_MOVE || raw == ContainerInput.THROW;
    }

    public ContainerInput getRaw() {
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
