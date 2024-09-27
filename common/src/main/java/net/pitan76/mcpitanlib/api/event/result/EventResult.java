package net.pitan76.mcpitanlib.api.event.result;

import net.minecraft.util.ActionResult;

public class EventResult {

    protected final me.shedaniel.architectury.event.EventResult result;

    private static final EventResult TRUE = new EventResult(me.shedaniel.architectury.event.EventResult.interruptTrue());
    private static final EventResult STOP = new EventResult(me.shedaniel.architectury.event.EventResult.interruptDefault());
    private static final EventResult PASS = new EventResult(me.shedaniel.architectury.event.EventResult.pass());
    private static final EventResult FALSE = new EventResult(me.shedaniel.architectury.event.EventResult.interruptFalse());


    protected EventResult(me.shedaniel.architectury.event.EventResult result) {
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
    public me.shedaniel.architectury.event.EventResult getResult() {
        return result;
    }

    public ActionResult toActionResult() {
        if (this.equals(TRUE)) {
            return ActionResult.SUCCESS;
        }
        if (this.equals(FALSE)) {
            return ActionResult.FAIL;
        }
        if (this.equals(PASS)) {
            return ActionResult.PASS;
        }
        if (this.equals(STOP)) {
            return ActionResult.PASS;
        }
        return ActionResult.CONSUME;
    }
}
