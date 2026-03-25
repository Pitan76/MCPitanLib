package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;

public interface ICompatInventory extends Container {
    default void callSetStack(int slot, ItemStack stack) {
        setItem(slot, stack);
    }

    default ItemStack callGetStack(int slot) {
        return getItem(slot);
    }

    default int callSize() {
        return getContainerSize();
    }

    default boolean callIsEmpty() {
        return isEmpty();
    }

    default ItemStack callRemoveStack(int slot, int amount) {
        return removeItem(slot, amount);
    }

    default ItemStack callRemoveStack(int slot) {
        return removeItemNoUpdate(slot);
    }

    default void callClear() {
        clearContent();
    }

    default void callMarkDirty() {
        setChanged();
    }

    default boolean callCanPlayerUse(net.minecraft.world.entity.player.Player player) {
        return stillValid(player);
    }

    default boolean canPlayerUse(Player player) {
        return stillValid(player.getEntity());
    }


    default void callSetStack(int slot, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        setItem(slot, stack.toMinecraft());
    }

    default net.pitan76.mcpitanlib.midohra.item.ItemStack callGetStackAsMidohra(int slot) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getItem(slot));
    }

    default int callGetMaxCountPerStack() {
        return getMaxStackSize();
    }
}
