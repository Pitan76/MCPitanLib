package net.pitan76.mcpitanlib.test;

import net.minecraft.inventory.Inventory;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.util.InventoryUtil;
import net.pitan76.mcpitanlib.api.util.debug.OutputUtil;
import net.pitan76.mcpitanlib.guilib.api.container.SimpleContainerGui;

public class ExampleContainerGui extends SimpleContainerGui {

    public static Inventory EXAMPLE_INVENTORY = InventoryUtil.createSimpleInventory(1);

    public ExampleContainerGui(CreateMenuEvent e) {
        super(ExampleMod.EXAMPLE_CONTAINER_GUI.get(), e);
        addPlayerMainInventorySlots(e.playerInventory, 8, 84);
        addPlayerHotbarSlots(e.playerInventory, 8, 142);

        addNormalSlot(EXAMPLE_INVENTORY, 0, 24, 24);
    }

    @Override
    public void close(Player player) {
        OutputUtil.print(EXAMPLE_INVENTORY);
    }
}
