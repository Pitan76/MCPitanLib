package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;
import net.pitan76.mcpitanlib.api.entity.CompatContainerUser;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.api.util.inventory.args.CanInsertArgs;
import net.pitan76.mcpitanlib.midohra.nbt.NbtList;

import java.util.List;

public class CompatInventory extends SimpleContainer {
    public CompatInventory(int size) {
        super(size);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        super.setItem(slot, stack);
    }

    /**
     * super method of setStack(slot, stack)
     */
    public final void superSetStack(int slot, ItemStack stack) {
        super.setItem(slot, stack);
    }

    /**
     * super method of removeStack(slot, amount)
     */
    public final ItemStack superRemoveStack(int slot, int amount) {
        return super.removeItem(slot, amount);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return callRemoveStack(slot, amount);
    }

    public ItemStack callRemoveStack(int slot, int amount) {
        return super.removeItem(slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return super.removeItemNoUpdate(slot);
    }

    @Override
    public ItemStack removeItemType(Item item, int count) {
        return super.removeItemType(item, count);
    }

    @Deprecated
    @Override
    public void startOpen(ContainerUser user) {
        if (user instanceof Player) {
            onOpen(new Player((net.minecraft.world.entity.player.Player) user));
            return;
        }

        this.onOpen(new CompatContainerUser(user));

    }

    @Deprecated
    @Override
    public void stopOpen(ContainerUser user) {
        if (user instanceof Player) {
            onClose(new Player((net.minecraft.world.entity.player.Player) user));
            return;
        }

        this.onClose(new CompatContainerUser(user));
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
    public boolean stillValid(net.minecraft.world.entity.player.Player player) {
        return canPlayerUse(new Player(player));
    }

    @Deprecated
    @Override
    public boolean canAddItem(ItemStack stack) {
        return canInsert(new CanInsertArgs(stack));
    }

    public void onOpen(Player player) {
        onOpen(new CompatContainerUser(player.getEntity()));
    }

    public void onClose(Player player) {
        onClose(new CompatContainerUser(player.getEntity()));
    }

    public void onOpen(CompatContainerUser user) {
        super.startOpen(user.getRaw());
    }

    public void onClose(CompatContainerUser user) {
        super.stopOpen(user.getRaw());
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
        return super.canAddItem(args.getMcStack());
    }

    @Deprecated
    @Override
    public List<ItemStack> removeAllItems() {
        return callClearToList();
    }

    public List<ItemStack> callClearToList() {
        return super.removeAllItems();
    }

    @Deprecated
    @Override
    public NonNullList<ItemStack> getItems() {
        return callGetHeldStacks();
    }

    public NonNullList<ItemStack> callGetHeldStacks() {
        return super.getItems();
    }

    public ItemStackList callGetHeldStacksAsItemStackList() {
        return ItemStackList.of(callGetHeldStacks());
    }

    @Deprecated
    @Override
    public ItemStack getItem(int slot) {
        return callGetStack(slot);
    }

    public ItemStack callGetStack(int slot) {
        return super.getItem(slot);
    }

    @Deprecated
    @Override
    public int getContainerSize() {
        return getSize();
    }

    public int getSize() {
        return super.getContainerSize();
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
    public boolean canTakeItem(Container hopperInventory, int slot, ItemStack stack) {
        return callCanTransferTo(hopperInventory, slot, stack);
    }

    public boolean callCanTransferTo(Container hopperInventory, int slot, ItemStack stack) {
        return super.canTakeItem(hopperInventory, slot, stack);
    }

    @Deprecated
    @Override
    public ItemStack addItem(ItemStack stack) {
        return callAddStack(stack);
    }

    public ItemStack callAddStack(ItemStack stack) {
        return super.addItem(stack);
    }

    @Deprecated
    @Override
    public int getMaxStackSize() {
        return callGetMaxCountPerStack();
    }

    public int callGetMaxCountPerStack() {
        return super.getMaxStackSize();
    }
}
