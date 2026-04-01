package net.pitan76.mcpitanlib.api.gui;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.args.SlotClickEvent;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.ScreenHandlerUtil;
import net.pitan76.mcpitanlib.api.util.SlotUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimpleScreenHandler extends AbstractContainerMenu {
    protected SimpleScreenHandler(@Nullable MenuType<?> type, int syncId) {
        super(type, syncId);
    }

    protected SimpleScreenHandler(@Nullable MenuType<?> type, CreateMenuEvent e) {
        this(type, e.getSyncId());
    }

    public boolean hasMainInventory = false;
    public boolean hasHotbar = false;

    @Deprecated
    @Override
    public boolean stillValid(net.minecraft.world.entity.player.Player player) {
        return canUse(new Player(player));
    }

    public boolean canUse(Player player) {
        return true;
    }

    public Slot addNormalSlot(Container inventory, int index, int x, int y) {
        Slot slot = new Slot(inventory, index, x, y);
        return this.addSlot(slot);
    }

    public <T extends Slot> Slot addSlot(Container inventory, int index, int x, int y, SlotFactory<T> factory) {
        Slot slot = factory.create(inventory, index, x, y);
        return this.addSlot(slot);
    }

    public interface SlotFactory<T extends Slot> {
        T create(Container inventory, int index, int x, int y);
    }

    public Slot callAddSlot(Slot slot) {
        return this.addSlot(slot);
    }

    @Deprecated
    @Override
    protected Slot addSlot(Slot slot) {
        return super.addSlot(slot);
    }

    @Deprecated
    @Override
    public void removed(net.minecraft.world.entity.player.Player player) {
        this.close(new Player(player));
    }

    public void close(Player player) {
        super.removed(player.getPlayerEntity());
    }


    public static final int DEFAULT_SLOT_SIZE = 18;

    /**
     * Add player main inventory slots
     * @param inventory target player inventory
     * @param x start x
     * @param y start y
     */
    protected List<Slot> addPlayerMainInventorySlots(Inventory inventory, int x, int y) {
        hasMainInventory = true;
        return this.addSlots(inventory, 9, x, y, DEFAULT_SLOT_SIZE, 9, 3);
    }

    /**
     * Add player hotbar slots
     * @param inventory target player inventory
     * @param x start x
     * @param y start y
     */
    protected List<Slot> addPlayerHotbarSlots(Inventory inventory, int x, int y) {
        hasHotbar = true;
        return this.addSlotsX(inventory, 0, x, y, DEFAULT_SLOT_SIZE, 9);
    }

    /**
     * 一括でスロットを設置する
     * @param inventory target inventory
     * @param firstIndex fisrt index
     * @param firstX first x
     * @param firstY first y
     * @param size a slot size (if this is -1, set 18 to this)
     * @param maxAmountX x line slot max amount
     * @param maxAmountY y line slot max amount
     * @return Slot list
     */
    protected List<Slot> addSlots(Container inventory, int firstIndex, int firstX, int firstY, int size, int maxAmountX, int maxAmountY) {
        if (size < 0) size = DEFAULT_SLOT_SIZE;
        List<Slot> slots = new ArrayList<>();
        for (int y = 0; y < maxAmountY; ++y) {
            List<Slot> xSlots = this.addSlotsX(inventory, firstIndex + (y * maxAmountX), firstX, firstY + (y * size), size, maxAmountX);
            slots.addAll(xSlots);
        }
        return slots;
    }

    /**
     * 一括で横にスロットを設置する
     * @param inventory target inventory
     * @param firstIndex first index
     * @param firstX first x
     * @param y y
     * @param size a slot size (if this is -1, set 18 to this)
     * @param amount slot amount
     * @return Slot list
     */
    protected List<Slot> addSlotsX(Container inventory, int firstIndex, int firstX, int y, int size, int amount) {
        if (size < 0) size = DEFAULT_SLOT_SIZE;
        List<Slot> slots = new ArrayList<>();
        for (int x = 0; x < amount; ++x) {
            Slot slot = this.addNormalSlot(inventory, firstIndex + x, firstX + (x * size), y);
            slots.add(slot);
        }
        return slots;
    }

    /**
     * 一括で縦にスロットを設置する
     * @param inventory target inventory
     * @param firstIndex first index
     * @param x x
     * @param firstY first y
     * @param size a slot size (if this is -1, set 18 to this)
     * @param amount slot amount
     * @return Slot list
     */
    protected List<Slot> addSlotsY(Container inventory, int firstIndex, int x, int firstY, int size, int amount) {
        if (size < 0) size = DEFAULT_SLOT_SIZE;
        List<Slot> slots = new ArrayList<>();
        for (int y = 0; y < amount; ++y) {
            Slot slot = this.addNormalSlot(inventory, firstIndex + x, x, firstY + (y * size));
            slots.add(slot);
        }
        return slots;
    }

    @Deprecated
    public ItemStack quickMoveOverride(net.minecraft.world.entity.player.Player player, int index) {
        return quickMoveOverride(new Player(player), index);
    }

    public boolean callInsertItem(ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
        return this.moveItemStackTo(stack, startIndex, endIndex, fromLast);
    }

    @Deprecated
    @Override
    protected boolean moveItemStackTo(ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
        return super.moveItemStackTo(stack, startIndex, endIndex, fromLast);
    }

    public ItemStack quickMoveOverride(Player player, int index) {
        ItemStack itemStack = ItemStackUtil.empty();
        Slot slot = ScreenHandlerUtil.getSlot(this, index);
        int size = ScreenHandlerUtil.getSlots(this).size();

        if (SlotUtil.hasStack(slot)) {
            ItemStack itemStack2 = SlotUtil.getStack(slot);
            itemStack = itemStack2.copy();

            if (hasMainInventory && hasHotbar) {
                if (index > 35) {
                    if (!this.callInsertItem(itemStack2, 0, 9, false)) {
                        if (!this.callInsertItem(itemStack2, 9, 36, true)) {
                            return ItemStackUtil.empty();
                        }
                    }
                } else if (size > 36 && !this.callInsertItem(itemStack2, 36, size, false)) {
                    return ItemStackUtil.empty();
                }
            }

            if (itemStack2.isEmpty()) {
                SlotUtil.setStack(slot, ItemStackUtil.empty());
            } else {
                SlotUtil.markDirty(slot);
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStackUtil.empty();
            }

            SlotUtil.onTakeItem(slot, player, itemStack2);
        }

        return itemStack;
    }


    @Deprecated
    @Override
    public ItemStack quickMoveStack(net.minecraft.world.entity.player.Player player, int slot) {
        return quickMoveOverride(player, slot);
    }

    @Deprecated
    @Override
    public Slot getSlot(int index) {
        return callGetSlot(index);
    }

    public Slot callGetSlot(int index) {
        return super.getSlot(index);
    }

    @Deprecated
    @Override
    public void clicked(int slotIndex, int button, ContainerInput actionType, net.minecraft.world.entity.player.Player player) {
        overrideOnSlotClick(slotIndex, button, actionType, new Player(player));
    }

    public void overrideOnSlotClick(int slotIndex, int button, ContainerInput actionType, Player player) {
        onSlotClick(new SlotClickEvent(slotIndex, button, actionType, player));
    }

    public void onSlotClick(SlotClickEvent e) {
        super.clicked(e.slot, e.button, e.actionType, e.player.getEntity());
    }

    @Override
    @Deprecated
    public void setCarried(ItemStack stack) {
        callSetCursorStack(stack);
    }

    public void callSetCursorStack(ItemStack stack) {
        super.setCarried(stack);
    }

    @Override
    @Deprecated
    public void setItem(int slot, int revision, ItemStack stack) {
        callSetStackInSlot(slot, revision, stack);
    }

    public void callSetStackInSlot(int slot, int revision, ItemStack stack) {
        super.setItem(slot, revision, stack);
    }

    @Override
    @Deprecated
    public int getStateId() {
        return callGetRevision();
    }

    public int callGetRevision() {
        return super.getStateId();
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getCursorStackM() {
        return ScreenHandlerUtil.getCursorStackM(this);
    }

    public ItemStack callGetCursorStack() {
        return ScreenHandlerUtil.getCursorStack(this);
    }
}
