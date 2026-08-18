package net.pitan76.mcpitanlib.api.event.result;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.StackActionResult;

public class TypedEventResult<T> {
    protected final ActionResult result;
    protected final T object;

    protected TypedEventResult(ActionResult result, T object) {
        this.result = result;
        this.object = object;
    }

    public static <T> TypedEventResult<T> success(T value) {
        return new TypedEventResult<>(ActionResult.SUCCESS, value);
    }

    public static <T> TypedEventResult<T> stop(T value) {
        return new TypedEventResult<>(ActionResult.FAIL, value);
    }

    public static <T> TypedEventResult<T> pass() {
        return new TypedEventResult<>(ActionResult.PASS, null);
    }

    public static <T> TypedEventResult<T> fail(T value) {
        return new TypedEventResult<>(ActionResult.FAIL, value);
    }

    @Deprecated
    public ActionResult getResult() {
        return result;
    }

    public ActionResult toActionResult() {
        return result;
    }

    public CompatActionResult toCompatActionResult() {
        return CompatActionResult.of(result);
    }

    public CompatActionResult toCompatActionResult(ItemStack stack) {
        if (object != stack)
            return toCompatActionResult();

        if (toActionResult() == ActionResult.SUCCESS)
            return StackActionResult.success(stack);

        if (toActionResult() == ActionResult.CONSUME)
            return StackActionResult.consume(stack);

        return toCompatActionResult();
    }

    public CompatActionResult toCompatActionResult(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return toCompatActionResult(stack.toMinecraft());
    }
}
