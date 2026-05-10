package net.pitan76.mcpitanlib.api.gui.inventory;

import net.minecraft.entity.player.PlayerEntity;
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
        return size();
    }

    default boolean callIsEmpty() {
        return isEmpty();
    }

    default ItemStack getStackM(int slot) {
        return ItemStack.of(getStack(slot));
    }


    default ItemStack removeStackM(int slot, int count) {
        return ItemStack.of(removeStack(slot, count));
    }

    default ItemStack removeStackM(int slot) {
        return ItemStack.of(removeStack(slot));
    }

    default void setStack(int slot, ItemStack stack) {
        setStack(slot, stack.toMinecraft());
    }

    default void callClearCII() {
        clear();
    }

    default void callMarkDirtyCII() {
        markDirty();
    }

    default boolean canPlayerUseCII(Player player) {
        return true;
    }

    @Deprecated
    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return canPlayerUseCII(new Player(player));
    }

    default ItemStackList getCompatItems() {
        return ItemStackList.of(getItemsM());
    }
}
