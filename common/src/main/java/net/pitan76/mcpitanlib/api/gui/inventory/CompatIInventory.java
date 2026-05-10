package net.pitan76.mcpitanlib.api.gui.inventory;

import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;

public interface CompatIInventory extends IInventory {
    static CompatIInventory of(ItemStackList items) {
        return () -> items;
    }

    static CompatIInventory ofSize(int size) {
        return of(ItemStackList.ofSize(size));
    }

    default int callSize() {
        return getContainerSize();
    }

    default boolean callIsEmpty() {
        return isEmpty();
    }

    default ItemStack getStackM(int slot) {
        return ItemStack.of(getItem(slot));
    }


    default ItemStack removeStackM(int slot, int count) {
        return ItemStack.of(removeItem(slot, count));
    }

    default ItemStack removeStackM(int slot) {
        return ItemStack.of(removeItemNoUpdate(slot));
    }

    default void setStack(int slot, ItemStack stack) {
        setItem(slot, stack.toMinecraft());
    }

    default void callClearCII() {
        clearContent();
    }

    default void callMarkDirtyCII() {
        setChanged();
    }

    default boolean canPlayerUseCII(Player player) {
        return true;
    }

    @Deprecated
    @Override
    default boolean stillValid(net.minecraft.world.entity.player.Player player) {
        return canPlayerUseCII(new Player(player));
    }

    default ItemStackList getCompatItems() {
        return ItemStackList.of(getItemsM());
    }
}
