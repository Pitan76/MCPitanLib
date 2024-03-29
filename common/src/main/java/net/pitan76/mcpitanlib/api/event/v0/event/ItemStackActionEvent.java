package net.pitan76.mcpitanlib.api.event.v0.event;

import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

import java.util.ArrayList;
import java.util.List;

public class ItemStackActionEvent {

    public static Boolean returnValue = null;

    public static void setReturnValue(boolean setReturnValue) {
        ItemStackActionEvent.returnValue = setReturnValue;
    }

    private static final List<EventRegistry.ItemStackAction.ItemStackDamageState> states = new ArrayList<>();

    public static void register(EventRegistry.ItemStackAction.ItemStackDamageState state) {
        states.add(state);
    }

    public static void call(ItemStack stack) {
        if (states.isEmpty()) return;
        for (EventRegistry.ItemStackAction.ItemStackDamageState state : states) {
            state.onDamage(stack);
        }
    }

    public static void unregister(EventRegistry.ItemStackAction.ItemStackDamageState state) {
        states.remove(state);
    }

}
