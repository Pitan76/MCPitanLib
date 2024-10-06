package net.pitan76.mcpitanlib.guilib.api.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandler;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.guilib.api.IScreenInfo;

import java.util.ArrayList;
import java.util.List;

public abstract class ContainerGui extends SimpleScreenHandler implements IScreenInfo {

    private final List<Inventory> INVENTORIES = new ArrayList<>();

    protected ContainerGui(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    protected ContainerGui(ScreenHandlerType<?> type, CreateMenuEvent e) {
        super(type, e);
    }

    @Override
    public boolean canUse(Player player) {
        return true;
    }

    @Override
    protected Slot addSlot(Slot slot) {
        if (slot.inventory instanceof Inventory && !INVENTORIES.contains(slot.inventory))
            INVENTORIES.add(slot.inventory);

        return super.addSlot(slot);
    }

    public int playerMainInventoryX = 8;
    public int playerMainInventoryY = 84;

    public int playerHotbarX = 8;
    public int playerHotbarY = 142;

    @Override
    protected List<Slot> addPlayerMainInventorySlots(PlayerInventory inventory, int x, int y) {
        playerMainInventoryX = x;
        playerMainInventoryY = y;

        return super.addPlayerMainInventorySlots(inventory, x, y);
    }

    @Override
    protected List<Slot> addPlayerHotbarSlots(PlayerInventory inventory, int x, int y) {
        playerHotbarX = x;
        playerHotbarY = y;

        return super.addPlayerHotbarSlots(inventory, x, y);
    }

    @Override
    public void close(Player player) {
        INVENTORIES.forEach((inv -> inv.onClose(player.getEntity())));
    }
}
