package net.pitan76.mcpitanlib.guilib.api.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandler;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.guilib.api.IScreenInfo;
import net.pitan76.mcpitanlib.midohra.screen.ScreenHandlerTypeWrapper;

import java.util.ArrayList;
import java.util.List;

public abstract class ContainerGui extends SimpleScreenHandler implements IScreenInfo {

    private final List<Container> INVENTORIES = new ArrayList<>();

    protected ContainerGui(MenuType<?> type, int syncId) {
        super(type, syncId);
    }

    protected ContainerGui(MenuType<?> type, CreateMenuEvent e) {
        super(type, e);
    }

    protected ContainerGui(ScreenHandlerTypeWrapper type, int syncId) {
        super(type, syncId);
    }

    protected ContainerGui(ScreenHandlerTypeWrapper type, CreateMenuEvent e) {
        super(type, e);
    }

    @Override
    public boolean canUse(Player player) {
        return true;
    }

    @Override
    protected Slot addSlot(Slot slot) {
        if (slot.container instanceof Container && !INVENTORIES.contains(slot.container))
            INVENTORIES.add(slot.container);

        return super.addSlot(slot);
    }

    public int playerMainInventoryX = 8;
    public int playerMainInventoryY = 84;

    public int playerHotbarX = 8;
    public int playerHotbarY = 142;

    @Override
    protected List<Slot> addPlayerMainInventorySlots(Inventory inventory, int x, int y) {
        playerMainInventoryX = x;
        playerMainInventoryY = y;

        return super.addPlayerMainInventorySlots(inventory, x, y);
    }

    @Override
    protected List<Slot> addPlayerHotbarSlots(Inventory inventory, int x, int y) {
        playerHotbarX = x;
        playerHotbarY = y;

        return super.addPlayerHotbarSlots(inventory, x, y);
    }

    @Override
    public void close(Player player) {
        INVENTORIES.forEach((inv -> inv.stopOpen(player.getEntity())));
    }
}
