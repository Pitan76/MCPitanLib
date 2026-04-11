package net.pitan76.mcpitanlib.midohra.component.item;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;

public abstract class ItemComponentType<T> {

    private final DataComponentType<?> type;

    protected ItemComponentType(DataComponentType<?> type) {
        this.type = type;
    }

    public abstract void put(ItemStack stack, T value);

    public abstract T get(ItemStack stack);

    public boolean has(ItemStack stack) {
        return stack.has(type);
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
