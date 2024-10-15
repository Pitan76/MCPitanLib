package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.event.result.EventResult;

public class CompatActionResult {
    public static final CompatActionResult SUCCESS = new CompatActionResult(ActionResult.SUCCESS, EventResult.success());
    public static final CompatActionResult PASS = new CompatActionResult(ActionResult.PASS, EventResult.pass());
    public static final CompatActionResult FAIL = new CompatActionResult(ActionResult.FAIL, EventResult.fail());
    public static final CompatActionResult CONSUME = new CompatActionResult(ActionResult.CONSUME, EventResult.success());
    public static final CompatActionResult PASS_TO_DEFAULT_BLOCK_ACTION = new CompatActionResult(ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION, EventResult.pass());
    public static final CompatActionResult SUCCESS_SERVER = new CompatActionResult(ActionResult.SUCCESS_SERVER, EventResult.success());
    public static final CompatActionResult STOP = new CompatActionResult(ActionResult.FAIL, EventResult.stop());

    private final ActionResult actionResult;
    private final EventResult eventResult;

    protected CompatActionResult(ActionResult actionResult, EventResult eventResult) {
        this.actionResult = actionResult;
        this.eventResult = eventResult;
    }

    public ActionResult toActionResult() {
        return actionResult;
    }

    public EventResult toEventResult() {
        return eventResult;
    }

    public static CompatActionResult of(ActionResult result) {
        if (result == ActionResult.SUCCESS)
            return SUCCESS;

        if (result == ActionResult.PASS)
            return PASS;

        if (result == ActionResult.FAIL)
            return FAIL;

        if (result == ActionResult.CONSUME)
            return CONSUME;

        if (result == ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION)
            return PASS_TO_DEFAULT_BLOCK_ACTION;

        if (result == ActionResult.SUCCESS_SERVER)
            return SUCCESS_SERVER;

        return PASS;
    }

    public static CompatActionResult of(EventResult result) {
        if (result == EventResult.success())
            return SUCCESS;

        if (result == EventResult.pass())
            return PASS;

        if (result == EventResult.fail())
            return FAIL;

        if (result == EventResult.stop())
            return STOP;

        return PASS;
    }

    public static CompatActionResult create(ActionResult result, EventResult eventResult) {
        return new CompatActionResult(result, eventResult);
    }

    public static CompatActionResult create(ActionResult result) {
        return create(result, EventResult.stop());
    }
}
