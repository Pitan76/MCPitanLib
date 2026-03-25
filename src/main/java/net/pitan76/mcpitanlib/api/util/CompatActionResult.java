package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.event.result.EventResult;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;

import java.util.Optional;

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

    public Optional<ItemStack> getNewMidohraHandStack() {
        return getNewHandStack().map(ItemStack::of);
    }

    public Optional<net.minecraft.item.ItemStack> getNewHandStack() {
        if (!(actionResult instanceof ActionResult.Success)) return Optional.empty();

        ActionResult.Success success = (ActionResult.Success) actionResult;
        return Optional.ofNullable(success.getNewHandStack());
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

    @Deprecated
    public static CompatActionResult create(ActionResult result, EventResult eventResult) {
        return new CompatActionResult(result, eventResult);
    }

    /**
     * @deprecated Use {@link #of(ActionResult)} instead.
     */
    @Deprecated
    public static CompatActionResult create(ActionResult result) {
        return of(result);
    }

    @Deprecated
    public static CompatActionResult create2(ActionResult result) {
        return create(result, EventResult.stop());
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;
        if (!(obj instanceof CompatActionResult)) return false;
        if (actionResult.equals(((CompatActionResult) obj).actionResult)) {
            return eventResult.equals(((CompatActionResult) obj).eventResult);
        }

        return false;
    }

    public String getName() {
        if (this == SUCCESS) return "SUCCESS";
        if (this == PASS) return "PASS";
        if (this == FAIL) return "FAIL";
        if (this == CONSUME) return "CONSUME";
        if (this == PASS_TO_DEFAULT_BLOCK_ACTION) return "PASS_TO_DEFAULT_BLOCK_ACTION";
        if (this == SUCCESS_SERVER) return "SUCCESS_SERVER";
        if (this == STOP) return "STOP";

        return "UNKNOWN";
    }
}
