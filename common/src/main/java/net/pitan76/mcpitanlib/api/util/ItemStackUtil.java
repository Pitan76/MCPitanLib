package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.Objects;

public class ItemStackUtil {
    public static ItemStack copy(ItemStack stack) {
        return stack.copy();
    }

    public static ItemStack copyWithCount(ItemStack stack, int count) {
        return stack.copyWithCount(count);
    }

    public static boolean areItemsEqual(ItemStack left, ItemStack right) {
        return ItemStack.areItemsEqual(left, right);
    }

    @Deprecated
    public static boolean areNbtEqual(ItemStack left, ItemStack right) {
        return areNbtOrComponentEqual(left, right);
    }

    /**
     * NBT (1.20.4) か Component (1.20.5以降) が一致するかどうかを取得する。
     * @param left ItemStack
     * @param right ItemStack
     * @return NBTかComponentが一致するかどうか
     */
    public static boolean areNbtOrComponentEqual(ItemStack left, ItemStack right) {
        return Objects.equals(left.getNbt(), right.getNbt());
    }

    /**
     * NBTかComponentが存在するかどうか
     * @param stack ItemStack
     * @return Whether NBT or Component exists
     */
    public static boolean hasNbtOrComponent(ItemStack stack) {
        return stack.hasNbt();
    }

    /**
     * NBTからItemStackを取得する
     * @param world World
     * @param nbt NbtCompound
     * @return ItemStack
     */
    public static ItemStack fromNbt(World world, NbtCompound nbt) {
        return ItemStack.fromNbt(nbt);
    }
}
