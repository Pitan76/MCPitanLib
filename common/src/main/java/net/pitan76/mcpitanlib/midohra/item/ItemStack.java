package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.pitan76.mcpitanlib.api.enchantment.CompatEnchantment;
import net.pitan76.mcpitanlib.api.item.stack.LoreUtil;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemStack {
    private final net.minecraft.item.ItemStack stack;
    public static final ItemStack EMPTY = new ItemStack(ItemStackUtil.empty());

    protected ItemStack(net.minecraft.item.ItemStack stack) {
        this.stack = stack;
    }

    public static ItemStack of(net.minecraft.item.ItemStack stack) {
        if (stack == null || stack.isEmpty())
            return EMPTY;

        return new ItemStack(stack);
    }

    public static ItemStack of(ItemConvertible item) {
        return new Builder().item(item).build();
    }

    public static ItemStack of(ItemConvertible item, int count) {
        return new Builder().item(item).count(count).build();
    }

    public static ItemStack empty() {
        return EMPTY;
    }

    public int getCount() {
        return stack.getCount();
    }

    public void setCount(int count) {
        stack.setCount(count);
    }

    public NbtCompound getCustomNbt() {
        return CustomDataUtil.getNbt(stack);
    }

    public void setCustomNbt(NbtCompound nbt) {
        CustomDataUtil.setNbt(stack, nbt);
    }

    public void removeCustomNbt() {
        CustomDataUtil.remove(stack);
    }

    public boolean hasCustomNbt() {
        return CustomDataUtil.hasNbt(stack);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public CompatIdentifier getItemId() {
        return ItemUtil.toCompatID(stack.getItem());
    }

    public ItemStack copy() {
        return new ItemStack(stack.copy());
    }

    public ItemStack copyWithCount(int count) {
        return new ItemStack(ItemStackUtil.copyWithCount(stack, count));
    }

    public Map<CompatEnchantment, Integer> getEnchantments(@Nullable World world) {
        return EnchantmentUtil.getEnchantment(stack, world);
    }

    public void setEnchantments(Map<CompatEnchantment, Integer> enchantments, @Nullable World world) {
        EnchantmentUtil.setEnchantment(stack, enchantments, world);
    }

    public int getMaxCount() {
        return ItemStackUtil.getMaxCount(stack);
    }

    public boolean hasLore() {
        return LoreUtil.hasLore(stack);
    }

    public List<Text> getLore() {
        return LoreUtil.getLore(stack);
    }

    public List<String> getLoreAsStringList() {
        return LoreUtil.getLoreAsStringList(stack);
    }

    public void setLore(List<Text> lore) {
        LoreUtil.setLore(stack, lore);
    }

    public void setLoreString(List<String> lore) {
        LoreUtil.setLoreStringList(stack, lore);
    }

    public void setLore(String lore) {
        LoreUtil.setLore(stack, lore);
    }

    @Deprecated
    public net.minecraft.item.ItemStack toMinecraft() {
        return stack;
    }

    public ItemWrapper getItem() {
        if (isEmpty())
            return ItemWrapper.of();

        return ItemWrapper.of(getRawItem());
    }

    public Item getRawItem() {
        return ItemStackUtil.getItem(stack);
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

    @Override
    public String toString() {
        return stack.toString();
    }

    public void increment(int amount) {
        ItemStackUtil.incrementCount(stack, amount);
    }

    public void decrement(int amount) {
        ItemStackUtil.decrementCount(stack, amount);
    }

    public void increment() {
        increment(1);
    }

    public void decrement() {
        decrement(1);
    }

    public boolean is(ItemWrapper item) {
        return getRawItem() == item.get();
    }

    public boolean is(CompatIdentifier id) {
        return getItemId().equals(id);
    }

    public boolean isBlockItem() {
        return getItem().isBlock();
    }

    public net.pitan76.mcpitanlib.midohra.nbt.NbtCompound getCustomNbtM() {
        return net.pitan76.mcpitanlib.midohra.nbt.NbtCompound.of(getCustomNbt());
    }

    public void setCustomNbt(net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt) {
        setCustomNbt(nbt.toMinecraft());
    }

    public void setLoreM(List<TextComponent> lore) {
        LoreUtil.setLore(stack, lore.stream().map(TextComponent::getText).collect(Collectors.toList()));
    }

    public List<TextComponent> getLoreM() {
        return getLore().stream().map(TextComponent::new).collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        return stack.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ItemStack other = (ItemStack) obj;
        return stack.equals(other.stack);
    }

    /**
     * instanceof check for the raw item of this stack.
     * @param clazz the class of the item to check
     * @return true if the raw item of this stack is an instance of the given class, false otherwise
     */
    public boolean instanceOf(Class<?> clazz) {
        if (isEmpty()) return false;

        return clazz.isInstance(getRawItem());
    }

    /**
     * instanceof check for the raw item of this stack.
     * @param wrapper the item to check
     * @return true if the raw item of this stack is an instance of the given item, false otherwise
     */
    public boolean instanceOf(ItemWrapper wrapper) {
        if (isEmpty()) return false;

        Item item = wrapper.get();
        if (item == null) return false;

        Class<?> clazz = item.getClass();
        return clazz.isInstance(getRawItem());
    }

    public List<TextComponent> getTooltip() {
        return stack.getTooltip(ClientUtil.getClientPlayer(), ClientUtil.getOptions().getRaw().advancedItemTooltips ? TooltipContext.Default.ADVANCED : TooltipContext.Default.NORMAL)
                .stream().map(TextComponent::new).collect(Collectors.toList());
    }

    public <T> void putCustomNbt(String key, T value) {
        CustomDataUtil.put(stack, key, value);
    }

    public void putCustomNbt(String key, net.pitan76.mcpitanlib.midohra.nbt.NbtCompound value) {
        CustomDataUtil.put(stack, key, value.toMinecraft());
    }

    public <T> T getCustomNbt(String key, Class<T> clazz) {
        return CustomDataUtil.get(stack, key, clazz);
    }

    public net.pitan76.mcpitanlib.midohra.nbt.NbtCompound getCustomNbt(String key) {
        if (PlatformUtil.isClient()) {
            return stack.getTooltipLines(net.minecraft.world.item.Item.TooltipContext.EMPTY, net.pitan76.mcpitanlib.api.util.client.ClientUtil.getClientPlayer(),
                            net.pitan76.mcpitanlib.api.util.client.ClientUtil.getOptions().getRaw().advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL)
                    .stream().map(TextComponent::new).collect(Collectors.toList());
        }

        return stack.getTooltipLines(net.minecraft.world.item.Item.TooltipContext.EMPTY, null, TooltipFlag.Default.NORMAL)
                .stream().map(TextComponent::new).collect(Collectors.toList());
    }

    public boolean hasCustomNbt(String key) {
        return CustomDataUtil.has(stack, key);
    }

    public void removeCustomNbt(String key) {
        CustomDataUtil.remove(stack, key);
    }

    public boolean areItemsEqual(ItemStack other) {
        return ItemStackUtil.areItemsEqual(this.stack, other.stack);
    }

    public boolean areNbtOrComponentEqual(ItemStack other) {
        return ItemStackUtil.areNbtOrComponentEqual(this.stack, other.stack);
    }
}
