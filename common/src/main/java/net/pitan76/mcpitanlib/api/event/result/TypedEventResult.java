package net.pitan76.mcpitanlib.api.event.result;

import dev.architectury.event.CompoundEventResult;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class TypedEventResult<T> {
    protected final dev.architectury.event.CompoundEventResult<T> result;

    protected TypedEventResult(dev.architectury.event.CompoundEventResult<T> result) {
        this.result = result;
    }

    public static <T> TypedEventResult<T> success(T value) {
        return new TypedEventResult<>(CompoundEventResult.interruptTrue(value));
    }

    public static <T> TypedEventResult<T> stop(T value) {
        return new TypedEventResult<>(CompoundEventResult.interruptDefault(value));
    }

    public static <T> TypedEventResult<T> pass() {
        return new TypedEventResult<>(CompoundEventResult.pass());
    }

    public static <T> TypedEventResult<T> fail(T value) {
        return new TypedEventResult<>(CompoundEventResult.interruptFalse(value));
    }

    @Deprecated
    public dev.architectury.event.CompoundEventResult<T> getResult() {
        return result;
    }

    public ActionResult toActionResult() {
        return result.result().asMinecraft();
    }

    public CompatActionResult toCompatActionResult() {
        return CompatActionResult.of(result.result().asMinecraft());
    }

    public CompatActionResult toCompatActionResult(ItemStack stack) {
        if (result.object() != stack)
            return toCompatActionResult();

        if (toActionResult() instanceof ActionResult.Success) {
            ActionResult.Success success = (ActionResult.Success) toActionResult();

            return CompatActionResult.create(success.withNewHandStack(stack));
        }

        return toCompatActionResult();
    }

    public CompatActionResult toCompatActionResult(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return toCompatActionResult(stack.toMinecraft());
    }
}
