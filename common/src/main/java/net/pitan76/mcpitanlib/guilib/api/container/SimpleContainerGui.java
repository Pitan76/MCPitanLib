package net.pitan76.mcpitanlib.guilib.api.container;

import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.guilib.api.ISimpleScreenInfo;

public abstract class SimpleContainerGui extends ContainerGui implements ISimpleScreenInfo {

    protected SimpleContainerGui(MenuType<?> type, int syncId) {
        super(type, syncId);
    }

    protected SimpleContainerGui(MenuType<?> type, CreateMenuEvent e) {
        super(type, e);
    }
}
