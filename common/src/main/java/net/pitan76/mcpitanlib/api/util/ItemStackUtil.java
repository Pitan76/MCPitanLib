package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;

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

    /**
     * NBTからItemStackを取得する
     * @param args NbtRWArgs
     * @return ItemStack
     */
    public static ItemStack fromNbt(NbtRWArgs args) {
        return ItemStack.fromNbt(args.getNbt());
    }

    public static ItemStack getDefaultStack(Item item) {
        return item.getDefaultStack();
    }

    public static int getMaxDamage(ItemStack stack) {
        return stack.getMaxDamage();
    }

    public static int getMaxDamage(Item item) {
        return getMaxDamage(getDefaultStack(item));
    }

    public static int getDamage(ItemStack stack) {
        return stack.getDamage();
    }

    public static void setDamage(ItemStack stack, int damage) {
        stack.setDamage(damage);
    }

    public static int getCount(ItemStack stack) {
        return stack.getCount();
    }

    public static void setCount(ItemStack stack, int count) {
        stack.setCount(count);
    }

    public static void decrementCount(ItemStack stack, int count) {
        stack.decrement(count);
    }

    public static void incrementCount(ItemStack stack, int count) {
        stack.increment(count);
    }

    public static void damage(ItemStack stack, int amount, ServerPlayerEntity entity, Runnable breakCallback) {
        stack.damage(amount, entity, (player) -> breakCallback.run());
    }

    public static void damage(ItemStack stack, int amount, LivingEntity entity, EquipmentSlot slot) {
        if (entity instanceof ServerPlayerEntity entity1)
            damage(stack, amount, entity1, () -> {

            });
    }

    public static void damage(ItemStack stack, int amount, ServerPlayerEntity entity) {
        stack.damage(amount, entity.getServerWorld(), entity, (item) -> entity.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
    }
}
