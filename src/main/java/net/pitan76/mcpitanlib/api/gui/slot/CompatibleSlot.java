package net.pitan76.mcpitanlib.api.gui.slot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.pitan76.mcpitanlib.api.entity.Player;

public class CompatibleSlot extends Slot {
    
    public Container inventory;
    public int index;
    public int x;
    public int y;

    public CompatibleSlot(Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.inventory = inventory;
        this.index = index;
        this.x = x;
        this.y = y;
    }

    public void callSetStack(ItemStack stack) {
        super.setByPlayer(stack);
    }

    public void callSetStackNoCallbacks(ItemStack stack) {
        super.set(stack);
    }

    public ItemStack callGetStack() {
        return super.getItem();
    }

    public ItemStack callTakeStack(int amount) {
        return super.remove(amount);
    }

    public boolean callHasStack() {
        return super.hasItem();
    }

    @Deprecated
    @Override
    public void setByPlayer(ItemStack stack) {
       callSetStack(stack);
    }

    @Deprecated
    @Override
    public void set(ItemStack stack) {
        callSetStackNoCallbacks(stack);
    }

    @Deprecated
    @Override
    public ItemStack getItem() {
        return callGetStack();
    }

    @Deprecated
    @Override
    public ItemStack remove(int amount) {
        return callTakeStack(amount);
    }

    @Deprecated
    @Override
    public boolean hasItem() {
        return callHasStack();
    }

    public Container callGetInventory() {
        return inventory;
    }

    public int callGetIndex() {
        return super.getContainerSlot();
    }

    public int callGetId() {
        return super.index;
    }

    public int callGetX() {
        return super.x;
    }

    public int callGetY() {
        return super.y;
    }

    public void callMarkDirty() {
        super.setChanged();
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return canInsert(net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack));
    }

    @Override
    public boolean mayPickup(Player playerEntity) {
        return canTakeItems(new Player(playerEntity));
    }

    public boolean canInsert(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return super.mayPlace(stack.toMinecraft());
    }

    public boolean canTakeItems(Player player) {
        return super.mayPickup(player.getEntity());
    }

    @Deprecated
    @Override
    public boolean allowModification(Player player) {
        return canTakePartial(new Player(player));
    }

    public boolean canTakePartial(Player player) {
        return super.allowModification(player.getEntity());
    }
}
