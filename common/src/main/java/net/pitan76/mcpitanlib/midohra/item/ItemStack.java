package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.item.ItemConvertible;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.enchantment.CompatEnchantment;
import net.pitan76.mcpitanlib.api.util.*;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ItemStack {
    private final net.minecraft.item.ItemStack stack;

    protected ItemStack(net.minecraft.item.ItemStack stack) {
        this.stack = stack;
    }

    public static ItemStack of(net.minecraft.item.ItemStack stack) {
        if (stack == null)
            return new ItemStack(ItemStackUtil.empty());

        return new ItemStack(stack);
    }

    public static ItemStack of(ItemConvertible item) {
        return new Builder().item(item).build();
    }

    public static ItemStack of(ItemConvertible item, int count) {
        return new Builder().item(item).count(count).build();
    }

    public int getCount() {
        return stack.getCount();
    }

    public void setCount(int count) {
        stack.setCount(count);
    }

    public NbtCompound getNbt() {
        return CustomDataUtil.getNbt(stack);
    }

    public void setNbt(NbtCompound nbt) {
        CustomDataUtil.setNbt(stack, nbt);
    }

    public CompatIdentifier getItemId() {
        return ItemUtil.toCompatID(stack.getItem());
    }

    public ItemStack copy() {
        return new ItemStack(stack.copy());
    }

    public Map<CompatEnchantment, Integer> getEnchantments(@Nullable World world) {
        return EnchantmentUtil.getEnchantment(stack, world);
    }

    public void setEnchantments(Map<CompatEnchantment, Integer> enchantments, @Nullable World world) {
        EnchantmentUtil.setEnchantment(stack, enchantments, world);
    }

    @Deprecated
    public net.minecraft.item.ItemStack toMinecraft() {
        return stack;
    }

    public static class Builder {

        protected CompatIdentifier id;
        protected int count = 1;
        protected NbtCompound nbt = NbtUtil.create();

        public Builder() {

        }

        public Builder item(CompatIdentifier id) {
            this.id = id;
            return this;
        }

        public Builder item(ItemConvertible item) {
            this.id = ItemUtil.toCompatID(item.asItem());
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder nbt(NbtCompound nbt) {
            this.nbt = nbt;
            return this;
        }

        public ItemStack build() {
            net.minecraft.item.ItemStack stack = new net.minecraft.item.ItemStack(ItemUtil.fromId(id), count);
            CustomDataUtil.setNbt(stack, nbt);

            return new ItemStack(stack);
        }
    }
}
