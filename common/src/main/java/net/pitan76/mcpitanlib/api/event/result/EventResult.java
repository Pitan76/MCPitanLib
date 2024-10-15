package net.pitan76.mcpitanlib.api.event.result;

import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class EventResult {

    protected final dev.architectury.event.EventResult result;

    private static final EventResult TRUE = new EventResult(dev.architectury.event.EventResult.interruptTrue());
    private static final EventResult STOP = new EventResult(dev.architectury.event.EventResult.interruptDefault());
    private static final EventResult PASS = new EventResult(dev.architectury.event.EventResult.pass());
    private static final EventResult FALSE = new EventResult(dev.architectury.event.EventResult.interruptFalse());


    protected EventResult(dev.architectury.event.EventResult result) {
        this.result = result;
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

    @Deprecated
    public dev.architectury.event.EventResult getResult() {
        return result;
    }

    public ActionResult toActionResult() {
        return result.asMinecraft();
    }

    public CompatActionResult toCompatActionResult() {
        return CompatActionResult.create(toActionResult());
    }
}
