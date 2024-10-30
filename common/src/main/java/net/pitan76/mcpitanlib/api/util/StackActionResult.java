package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.event.result.EventResult;

import java.util.Optional;

public class StackActionResult extends CompatActionResult {
    private final ItemStack stack;
    private final CompatActionResult compatActionResult;

    public StackActionResult(ActionResult actionResult, EventResult eventResult, ItemStack stack) {
        this(new CompatActionResult(actionResult, eventResult), stack);
    }

    public StackActionResult(CompatActionResult actionResult, ItemStack stack) {
        super(null, null);
        compatActionResult = actionResult;
        this.stack = stack;
    }

    @Override
    public ActionResult toActionResult() {
        return compatActionResult.toActionResult();
    }

    @Override
    public EventResult toEventResult() {
        return compatActionResult.toEventResult();
    }

    @Deprecated
    @Override
    public Optional<ItemStack> getNewHandStack() {
        if (hasNewStack())
            return compatActionResult.getNewHandStack();

        return Optional.empty();
    }

    public boolean hasNewStack() {
        return compatActionResult.getNewHandStack().isPresent();
    }

    public boolean hasStack() {
        return getStack() != null;
    }

    public CompatActionResult asCompatActionResult() {
        return compatActionResult;
    }

    public static StackActionResult create(CompatActionResult compatActionResult, ItemStack stack) {
        if (compatActionResult.getNewHandStack().isPresent())
            return new StackActionResult(compatActionResult, compatActionResult.getNewHandStack().get());

        return new StackActionResult(compatActionResult, stack);
    }

    public static StackActionResult create(CompatActionResult compatActionResult) {
        return new StackActionResult(compatActionResult, null);
    }

    public static StackActionResult create(ActionResult actionResult, EventResult eventResult, ItemStack stack) {
        return new StackActionResult(actionResult, eventResult, stack);
    }

    public static StackActionResult create(ActionResult actionResult, ItemStack stack) {
        return new StackActionResult(actionResult, null, stack);
    }

    public static StackActionResult success(ItemStack stack) {
        CompatActionResult compatActionResult = CompatActionResult.create(ActionResult.SUCCESS.withNewHandStack(stack));
        return create(compatActionResult, stack);
    }

    public static StackActionResult successServer(ItemStack stack) {
        CompatActionResult compatActionResult = CompatActionResult.create(ActionResult.SUCCESS_SERVER.withNewHandStack(stack));
        return create(compatActionResult, stack);
    }

    public static StackActionResult consume(ItemStack stack) {
        CompatActionResult compatActionResult = CompatActionResult.create(ActionResult.CONSUME.withNewHandStack(stack));
        return create(compatActionResult, stack);
    }

    public ItemStack getStack() {
        return stack;
    }
}
