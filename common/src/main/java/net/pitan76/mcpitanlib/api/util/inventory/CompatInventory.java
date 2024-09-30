package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.api.util.inventory.args.CanInsertArgs;
import net.pitan76.mcpitanlib.midohra.nbt.NbtList;

import java.util.List;

public class CompatInventory extends SimpleInventory {
    public CompatInventory(int size) {
        super(size);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        super.setStack(slot, stack);
    }

    /**
     * super method of setStack(slot, stack)
     */
    public final void superSetStack(int slot, ItemStack stack) {
        super.setStack(slot, stack);
    }

    /**
     * super method of removeStack(slot, amount)
     */
    public final ItemStack superRemoveStack(int slot, int amount) {
        return super.removeStack(slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return super.removeStack(slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return super.removeStack(slot);
    }

    @Override
    public ItemStack removeItem(Item item, int count) {
        return super.removeItem(item, count);
    }

    @Deprecated
    @Override
    public void onOpen(PlayerEntity player) {
        onOpen(new Player(player));
    }

    @Deprecated
    @Override
    public void onClose(PlayerEntity player) {
        onClose(new Player(player));
    }

    @Deprecated
    @Override
    public net.minecraft.nbt.NbtList toNbtList() {
        return toNbtList(new CompatRegistryLookup()).toMinecraft();
    }

    @Deprecated
    @Override
    public void readNbtList(net.minecraft.nbt.NbtList list) {
        readNbtList(NbtList.of(list), new CompatRegistryLookup());
    }

    @Deprecated
    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return canPlayerUse(new Player(player));
    }

    @Deprecated
    @Override
    public boolean canInsert(ItemStack stack) {
        return canInsert(new CanInsertArgs(stack));
    }

    public void onOpen(Player player) {
        super.onOpen(player.getEntity());
    }

    public void onClose(Player player) {
        super.onClose(player.getEntity());
    }

    public NbtList toNbtList(CompatRegistryLookup registries) {
        return NbtList.of(super.toNbtList());
    }

    public void readNbtList(NbtList list, CompatRegistryLookup registries) {
        super.readNbtList(list.toMinecraft());
    }

    public boolean canPlayerUse(Player player) {
        return true;
    }

    public boolean canInsert(CanInsertArgs args) {
        return super.canInsert(args.getMcStack());
    }

    @Deprecated
    @Override
    public List<ItemStack> clearToList() {
        return callClearToList();
    }

    public List<ItemStack> callClearToList() {
        return super.clearToList();
    }

    public DefaultedList<ItemStack> callGetHeldStacks() {
        ItemStackList list = ItemStackList.ofSize(size());
        for (int i = 0; i < size(); i++) {
            list.add(callGetStack(i));
        }

        return list;
    }

    public ItemStackList callGetHeldStacksAsItemStackList() {
        return ItemStackList.of(callGetHeldStacks());
    }

    @Deprecated
    @Override
    public ItemStack getStack(int slot) {
        return callGetStack(slot);
    }

    public ItemStack callGetStack(int slot) {
        return super.getStack(slot);
    }
}
