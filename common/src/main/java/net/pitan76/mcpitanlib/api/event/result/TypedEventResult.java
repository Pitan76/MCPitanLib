package net.pitan76.mcpitanlib.api.event.result;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class TypedEventResult<T> {
    protected final InteractionResult result;
    protected final T object;

    protected TypedEventResult(InteractionResult result, T object) {
        this.result = result;
        this.object = object;
    }

    public static <T> TypedEventResult<T> success(T value) {
        return new TypedEventResult<>(InteractionResult.SUCCESS, value);
    }

    public static <T> TypedEventResult<T> stop(T value) {
        return new TypedEventResult<>(InteractionResult.FAIL, value);
    }

    public static <T> TypedEventResult<T> pass() {
        return new TypedEventResult<>(InteractionResult.PASS, null);
    }

    public static <T> TypedEventResult<T> fail(T value) {
        return new TypedEventResult<>(InteractionResult.FAIL, value);
    }

    @Deprecated
    public InteractionResult getResult() {
        return result;
    }

    public InteractionResult toActionResult() {
        return result;
    }

    public CompatActionResult toCompatActionResult() {
        return CompatActionResult.of(result);
    }

    public CompatActionResult toCompatActionResult(ItemStack stack) {
        if (object != stack)
            return toCompatActionResult();

        if (toActionResult() instanceof InteractionResult.Success) {
            InteractionResult.Success success = (InteractionResult.Success) toActionResult();

            return CompatActionResult.create(success.heldItemTransformedTo(stack));
        }

        return toCompatActionResult();
    }

    public CompatActionResult toCompatActionResult(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return toCompatActionResult(stack.toMinecraft());
    }
}
