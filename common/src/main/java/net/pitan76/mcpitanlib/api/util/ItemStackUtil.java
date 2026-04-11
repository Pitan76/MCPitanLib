package net.pitan76.mcpitanlib.api.util;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.Objects;
import java.util.Optional;

public class ItemStackUtil {
    public static ItemStack copy(ItemStack stack) {
        return stack.copy();
    }

    public static ItemStack copyWithCount(ItemStack stack, int count) {
        return stack.copyWithCount(count);
    }

    public static boolean areItemsEqual(ItemStack left, ItemStack right) {
        return ItemStack.isSameItem(left, right);
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
        return Objects.equals(left.getComponents(), right.getComponents());
    }

    /**
     * NBTかComponentが存在するかどうか
     * @param stack ItemStack
     * @return Whether NBT or Component exists
     */
    public static boolean hasNbtOrComponent(ItemStack stack) {
        return !stack.getComponents().isEmpty();
    }

    /**
     * NBTからItemStackを取得する
     * @param world World
     * @param nbt NbtCompound
     * @return ItemStack
     */
    public static ItemStack fromNbt(Level world, CompoundTag nbt) {
        return fromNbt(new ReadNbtArgs(nbt));
    }

    /**
     * NBTからItemStackを取得する
     * @param args NbtRWArgs
     * @return ItemStack
     */
    public static ItemStack fromNbt(NbtRWArgs args) {
        DataResult<Pair<ItemStack, Tag>> result = ItemStack.CODEC.decode(NbtOps.INSTANCE, args.nbt);
        if (result.error().isPresent()) return ItemStack.EMPTY;

        Pair<ItemStack, Tag> pair = result.result().orElseThrow();
        return pair.getFirst();
    }

    public static ItemStack getDefaultStack(Item item) {
        return item.getDefaultInstance();
    }

    public static int getMaxDamage(ItemStack stack) {
        return stack.getMaxDamage();
    }

    public static int getMaxDamage(Item item) {
        return getMaxDamage(getDefaultStack(item));
    }

    public static int getDamage(ItemStack stack) {
        return stack.getDamageValue();
    }

    public static void setDamage(ItemStack stack, int damage) {
        stack.setDamageValue(damage);
    }

    public static int getCount(ItemStack stack) {
        return stack.getCount();
    }

    public static void setCount(ItemStack stack, int count) {
        stack.setCount(count);
    }

    public static void decrementCount(ItemStack stack, int count) {
        stack.shrink(count);
    }

    public static void incrementCount(ItemStack stack, int count) {
        stack.grow(count);
    }

    public static void damage(ItemStack stack, int amount, ServerPlayer entity, Runnable breakCallback) {
        stack.hurtAndBreak(amount, entity.level(), entity, (item) -> breakCallback.run());
    }

    public static void damage(ItemStack stack, int amount, LivingEntity entity, EquipmentSlot slot) {
        stack.hurtAndBreak(amount, entity, slot);
    }

    public static void damage(ItemStack stack, int amount, ServerPlayer entity) {
        stack.hurtAndBreak(amount, entity.level(), entity, (item) -> entity.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
    }

    public static void damage(ItemStack stack, int amount, Player entity) {
        Optional<ServerPlayer> player = entity.getServerPlayer();
        if (player.isEmpty()) return;

        damage(stack, amount, player.get());
    }

    public static ItemStack empty() {
        return ItemStack.EMPTY;
    }

    public static ItemStack create(Item item) {
        if (item == null) return empty();
        return new ItemStack(item);
    }

    public static ItemStack create(Item item, int count) {
        if (item == null) return empty();
        return new ItemStack(item, count);
    }

    public static ItemStack create(ItemLike item) {
        if (item == null) return empty();
        return new ItemStack(item);
    }

    public static ItemStack create(ItemLike item, int count) {
        if (item == null) return empty();
        return new ItemStack(item, count);
    }

    public static boolean isEmpty(ItemStack stack) {
        if (stack == null) return true;
        return stack.isEmpty();
    }

    public static boolean isEnchantable(ItemStack stack) {
        return stack.isEnchantable();
    }

    public static boolean isDamageable(ItemStack stack) {
        return stack.isDamageableItem();
    }

    public static boolean isBreak(ItemStack stack) {
        if (isDamageable(stack))
            return getDamage(stack) >= getMaxDamage(stack);

        return false;
    }

    public static ItemWrapper getItemWrapper(ItemStack stack) {
        return ItemWrapper.of(stack.getItem());
    }

    public static int getMaxCount(ItemStack stack) {
        return stack.getMaxStackSize();
    }

    public static Item getItem(ItemStack stack) {
        return stack.getItem();
    }
}
