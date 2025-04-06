package net.pitan76.mcpitanlib.midohra.component.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

public abstract class ItemComponentType<T> {

    private final String key;

    protected ItemComponentType(String key) {
        this.key = key;
    }

    public abstract void put(ItemStack stack, T value);

    public abstract T get(ItemStack stack);

    public boolean has(ItemStack stack) {
        if (!stack.hasNbt()) return false;

        String[] keys = key.split("\\.");
        NbtCompound tmp = stack.getNbt();
        for (String k : keys) {
            if (!NbtUtil.has(tmp, k)) return false;

            if (keys[keys.length - 1].equals(k)) {
                return true;
            }

            if (tmp.get(k) instanceof NbtCompound) {
                tmp = (NbtCompound) tmp.get(k);
            } else {
                return false;
            }
        }

        return false;
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
