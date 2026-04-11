package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.InteractionResult;
import net.pitan76.mcpitanlib.api.event.result.EventResult;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;

import java.util.Optional;

public class CompatActionResult {
    public static final CompatActionResult SUCCESS = new CompatActionResult(InteractionResult.SUCCESS, EventResult.success());
    public static final CompatActionResult PASS = new CompatActionResult(InteractionResult.PASS, EventResult.pass());
    public static final CompatActionResult FAIL = new CompatActionResult(InteractionResult.FAIL, EventResult.fail());
    public static final CompatActionResult CONSUME = new CompatActionResult(InteractionResult.CONSUME, EventResult.success());
    public static final CompatActionResult PASS_TO_DEFAULT_BLOCK_ACTION = new CompatActionResult(InteractionResult.TRY_WITH_EMPTY_HAND, EventResult.pass());
    public static final CompatActionResult SUCCESS_SERVER = new CompatActionResult(InteractionResult.SUCCESS_SERVER, EventResult.success());
    public static final CompatActionResult STOP = new CompatActionResult(InteractionResult.FAIL, EventResult.stop());

    private final InteractionResult actionResult;
    private final EventResult eventResult;

    protected CompatActionResult(InteractionResult actionResult, EventResult eventResult) {
        this.actionResult = actionResult;
        this.eventResult = eventResult;
    }

    public InteractionResult toActionResult() {
        return actionResult;
    }

    public EventResult toEventResult() {
        return eventResult;
    }

    public Optional<ItemStack> getNewMidohraHandStack() {
        return getNewHandStack().map(ItemStack::of);
    }

    public Optional<net.minecraft.world.item.ItemStack> getNewHandStack() {
        if (!(actionResult instanceof InteractionResult.Success)) return Optional.empty();

        InteractionResult.Success success = (InteractionResult.Success) actionResult;
        return Optional.ofNullable(success.heldItemTransformedTo());
    }

    public static CompatActionResult of(InteractionResult result) {
        if (result == InteractionResult.SUCCESS)
            return SUCCESS;

        if (result == InteractionResult.PASS)
            return PASS;

        if (result == InteractionResult.FAIL)
            return FAIL;

        if (result == InteractionResult.CONSUME)
            return CONSUME;

        if (result == InteractionResult.TRY_WITH_EMPTY_HAND)
            return PASS_TO_DEFAULT_BLOCK_ACTION;

        if (result == InteractionResult.SUCCESS_SERVER)
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
    public static CompatActionResult create(InteractionResult result, EventResult eventResult) {
        return new CompatActionResult(result, eventResult);
    }

    /**
     * @deprecated Use {@link #of(InteractionResult)} instead.
     */
    @Deprecated
    public static CompatActionResult create(InteractionResult result) {
        return of(result);
    }

    @Deprecated
    public static CompatActionResult create2(InteractionResult result) {
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
