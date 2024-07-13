package net.pitan76.mcpitanlib.api.gui.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.pitan76.mcpitanlib.api.entity.Player;

public class CompatibleSlot extends Slot {
    private final int _index;
    public CompatibleSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this._index = index;
    }

    public void callSetStack(ItemStack stack) {
        super.setStack(stack);
    }

    public void callSetStackNoCallbacks(ItemStack stack) {
        //super.setStackNoCallbacks(stack);
    }

    public ItemStack callGetStack() {
        return super.getStack();
    }

    public ItemStack callTakeStack(int amount) {
        return super.takeStack(amount);
    }

    public boolean callHasStack() {
        return super.hasStack();
    }

    @Deprecated
    @Override
    public void setStack(ItemStack stack) {
       callSetStack(stack);
    }

    @Deprecated
    @Override
    public ItemStack getStack() {
        return callGetStack();
    }

    @Deprecated
    @Override
    public ItemStack takeStack(int amount) {
        return callTakeStack(amount);
    }

    @Deprecated
    @Override
    public boolean hasStack() {
        return callHasStack();
    }

    public Inventory callGetInventory() {
        return inventory;
    }

    public int callGetIndex() {
        return _index;
    }

    public int callGetId() {
        return super.id;
    }

    public int callGetX() {
        return super.x;
    }

    public int callGetY() {
        return super.y;
    }

    public void callMarkDirty() {
        super.markDirty();
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return canInsert(net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack));
    }

    @Override
    public boolean canTakeItems(PlayerEntity playerEntity) {
        return canTakeItems(new Player(playerEntity));
    }

    public boolean canInsert(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return super.canInsert(stack.toMinecraft());
    }

    public boolean canTakeItems(Player player) {
        return super.canTakeItems(player.getEntity());
    }
}
