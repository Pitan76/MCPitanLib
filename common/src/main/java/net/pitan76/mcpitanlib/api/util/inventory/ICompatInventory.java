package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;

public interface ICompatInventory extends Inventory {
    default void callSetStack(int slot, ItemStack stack) {
        setStack(slot, stack);
    }

    default ItemStack callGetStack(int slot) {
        return getStack(slot);
    }

    default int callSize() {
        return size();
    }

    default boolean callIsEmpty() {
        return isEmpty();
    }

    default ItemStack callRemoveStack(int slot, int amount) {
        return removeStack(slot, amount);
    }

    default ItemStack callRemoveStack(int slot) {
        return removeStack(slot);
    }

    default void callClear() {
        clear();
    }

    default void callMarkDirty() {
        markDirty();
    }

    default boolean callCanPlayerUse(net.minecraft.entity.player.PlayerEntity player) {
        return canPlayerUse(player);
    }

    default boolean canPlayerUse(Player player) {
        return canPlayerUse(player.getEntity());
    }


    default void callSetStack(int slot, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        setStack(slot, stack.toMinecraft());
    }

    default net.pitan76.mcpitanlib.midohra.item.ItemStack callGetStackAsMidohra(int slot) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack(slot));
    }

    default int callGetMaxCountPerStack() {
        return getMaxCountPerStack();
    }
}
