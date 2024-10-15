package net.pitan76.mcpitanlib.api.event.result;

import me.shedaniel.architectury.event.CompoundEventResult;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.StackActionResult;

public class TypedEventResult<T> {
    protected final CompoundEventResult<T> result;

    protected TypedEventResult(CompoundEventResult<T> result) {
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
    public CompoundEventResult<T> getResult() {
        return result;
    }

    public ActionResult toActionResult() {
        return result.asMinecraft().getResult();
    }

    @Deprecated
    public TypedActionResult<T> toTypedActionResult() {
        return result.asMinecraft();
    }

    public CompatActionResult toCompatActionResult() {
        return CompatActionResult.of(result.asMinecraft().getResult());
    }

    public CompatActionResult toCompatActionResult(ItemStack stack) {
        if (result.object() != stack)
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
