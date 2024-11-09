package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.pitan76.mcpitanlib.api.event.result.EventResult;

import java.util.Optional;

public class StackActionResult extends CompatActionResult {
    private final ItemStack stack;
    private final CompatActionResult compatActionResult;
    private boolean isNewStack = false;

    public StackActionResult(ActionResult actionResult, EventResult eventResult, ItemStack stack) {
        this(new CompatActionResult(actionResult, eventResult), stack);
    }

    public StackActionResult(CompatActionResult actionResult, ItemStack stack) {
        super(null, null);
        compatActionResult = actionResult;
        this.stack = stack;
    }

    public void setNewStack(boolean b) {
        isNewStack = b;
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
            return Optional.ofNullable(getStack());

        return Optional.empty();
    }

    public boolean hasNewStack() {
        return isNewStack;
    }

    public boolean hasStack() {
        return getStack() != null;
    }

    public CompatActionResult asCompatActionResult() {
        return compatActionResult;
    }

    public static StackActionResult create(CompatActionResult compatActionResult, ItemStack stack) {
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

    public static StackActionResult create(TypedActionResult<ItemStack> result) {
        StackActionResult actionResult = create(CompatActionResult.create(result.getResult()), result.getValue());
        actionResult.setNewStack(true);
        return actionResult;
    }

    public static StackActionResult success(ItemStack stack) {
        CompatActionResult compatActionResult = CompatActionResult.create(ActionResult.SUCCESS);
        StackActionResult actionResult = create(compatActionResult, stack);
        actionResult.setNewStack(true);
        return actionResult;
    }

    public static StackActionResult successServer(ItemStack stack) {
        CompatActionResult compatActionResult = CompatActionResult.create(ActionResult.SUCCESS);
        StackActionResult actionResult = create(compatActionResult, stack);
        actionResult.setNewStack(true);
        return actionResult;
    }

    public static StackActionResult consume(ItemStack stack) {
        CompatActionResult compatActionResult = CompatActionResult.create(ActionResult.CONSUME);
        StackActionResult actionResult = create(compatActionResult, stack);
        actionResult.setNewStack(true);
        return actionResult;
    }

    public TypedActionResult<ItemStack> toTypedActionResult() {
        return new TypedActionResult<>(toActionResult(), getStack());
    }

    public static StackActionResult pass(ItemStack stack) {
        return create(CompatActionResult.PASS, stack);
    }

    public static StackActionResult pass() {
        return create(CompatActionResult.PASS);
    }

    public static StackActionResult fail(ItemStack stack) {
        return create(CompatActionResult.FAIL, stack);
    }

    public static StackActionResult fail() {
        return create(CompatActionResult.FAIL);
    }

    public ItemStack getStack() {
        return stack;
    }
}
