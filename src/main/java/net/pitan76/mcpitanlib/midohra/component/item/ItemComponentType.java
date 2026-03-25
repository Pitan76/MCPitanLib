package net.pitan76.mcpitanlib.midohra.component.item;

import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;

public abstract class ItemComponentType<T> {

    private final ComponentType<?> type;

    protected ItemComponentType(ComponentType<?> type) {
        this.type = type;
    }

    public abstract void put(ItemStack stack, T value);

    public abstract T get(ItemStack stack);

    public boolean has(ItemStack stack) {
        return stack.contains(type);
    }

    public void putOrDefault(ItemStack stack, T value, T defaultValue) {
        if (value.equals(defaultValue)) return;
        put(stack, value);
    }

    public T getOrDefault(ItemStack stack, T defaultValue) {
        if (!has(stack)) return defaultValue;
        return get(stack);
    }
}
