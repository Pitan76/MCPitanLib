package net.pitan76.mcpitanlib.api.event.result;

import net.minecraft.world.InteractionResult;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class EventResult {
    private static final EventResult TRUE = new EventResult();
    private static final EventResult STOP = new EventResult();
    private static final EventResult PASS = new EventResult();
    private static final EventResult FALSE = new EventResult();


    protected EventResult() {

    }

    public static EventResult success() {
        return TRUE;
    }

    public static EventResult stop() {
        return STOP;
    }

    public static EventResult pass() {
        return PASS;
    }

    public static EventResult fail() {
        return FALSE;
    }

    public InteractionResult toActionResult() {
        if (this == TRUE) return InteractionResult.SUCCESS;
        if (this == STOP) return InteractionResult.FAIL;
        if (this == PASS) return InteractionResult.PASS;
        if (this == FALSE) return InteractionResult.FAIL;
        throw new IllegalStateException("Unknown EventResult: " + this);
    }

    public CompatActionResult toCompatActionResult() {
        return CompatActionResult.create(toActionResult());
    }
}
