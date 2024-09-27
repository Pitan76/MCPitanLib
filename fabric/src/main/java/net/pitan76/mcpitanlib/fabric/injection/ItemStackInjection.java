package net.pitan76.mcpitanlib.fabric.injection;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

import java.util.Optional;

public interface ItemStackInjection {
    default NbtCompound method_7953(NbtCompound nbt) {
        return nbt;
    }

    static ItemStack method_7915(NbtCompound nbt) {
        Optional<ItemStack> stack = NbtUtil.getSimpleItemStack(nbt, "simple_stack");
        return stack.orElse(ItemStack.EMPTY);
    }
}
