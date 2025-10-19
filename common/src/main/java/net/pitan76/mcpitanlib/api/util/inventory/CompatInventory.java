package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.pitan76.mcpitanlib.api.entity.CompatContainerUser;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
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
        return callRemoveStack(slot, amount);
    }

    public ItemStack callRemoveStack(int slot, int amount) {
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

//    @Deprecated
//    public net.minecraft.nbt.NbtList toNbtList(RegistryWrapper.WrapperLookup registries) {
//        return toNbtList(new CompatRegistryLookup(registries)).toMinecraft();
//    }
//
//    @Deprecated
//    public void readNbtList(net.minecraft.nbt.NbtList list, RegistryWrapper.WrapperLookup registries) {
//        readNbtList(NbtList.of(list), new CompatRegistryLookup(registries));
//    }

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
        this.onOpen(new CompatContainerUser(player.getEntity()));
    }

    public void onClose(Player player) {
        this.onClose(new CompatContainerUser(player.getEntity()));
    }

    public void onOpen(CompatContainerUser user) {
        super.onOpen(user.getRaw());
    }

    public void onClose(CompatContainerUser user) {
        super.onClose(user.getRaw());
    }

    public NbtList toNbtList(CompatRegistryLookup registries) {
//        WriteView.ListAppender<ItemStack> listAppender = new ListApp
//        toDataList();
//
//        super.toDataList();
//
//        return NbtList.of(super.toNbtList(registries.getRegistryLookup()));
        return NbtList.of(NbtUtil.createNbtList());
    }

    public void readNbtList(NbtList list, CompatRegistryLookup registries) {
//        super.readNbtList(list.toMinecraft(), registries.getRegistryLookup());
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

    @Deprecated
    @Override
    public DefaultedList<ItemStack> getHeldStacks() {
        return callGetHeldStacks();
    }

    public DefaultedList<ItemStack> callGetHeldStacks() {
        return super.getHeldStacks();
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

    @Deprecated
    @Override
    public int size() {
        return getSize();
    }

    public int getSize() {
        return super.size();
    }

    @Deprecated
    @Override
    public boolean isEmpty() {
        return callIsEmpty();
    }

    public boolean callIsEmpty() {
        return super.isEmpty();
    }

    @Deprecated
    @Override
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return callCanTransferTo(hopperInventory, slot, stack);
    }

    public boolean callCanTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return super.canTransferTo(hopperInventory, slot, stack);
    }

    @Deprecated
    @Override
    public ItemStack addStack(ItemStack stack) {
        return callAddStack(stack);
    }

    public ItemStack callAddStack(ItemStack stack) {
        return super.addStack(stack);
    }

    @Deprecated
    @Override
    public int getMaxCountPerStack() {
        return callGetMaxCountPerStack();
    }

    public int callGetMaxCountPerStack() {
        return super.getMaxCountPerStack();
    }
}
