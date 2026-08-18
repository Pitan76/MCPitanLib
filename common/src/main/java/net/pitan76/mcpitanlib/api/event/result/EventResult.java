package net.pitan76.mcpitanlib.api.event.result;

import net.minecraft.util.ActionResult;
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

    public ActionResult toActionResult() {
        if (this == TRUE) return ActionResult.SUCCESS;
        // Architectury: interruptDefault() は asMinecraft() で PASS を返していたため合わせる
        if (this == STOP) return ActionResult.PASS;
        if (this == PASS) return ActionResult.PASS;
        if (this == FALSE) return ActionResult.FAIL;
        throw new IllegalStateException("Unknown EventResult: " + this);
    }

    public CompatActionResult toCompatActionResult() {
        return CompatActionResult.create(toActionResult());
    }
}
