package net.pitan76.mcpitanlib.api.event.result;

import me.shedaniel.architectury.event.CompoundEventResult;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

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

    public TypedActionResult<T> toTypedActionResult() {
        return result.asMinecraft();
    }

    public ActionResult toActionResult() {
        return result.asMinecraft().getResult();
    }
}
