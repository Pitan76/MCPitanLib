package net.pitan76.mcpitanlib.guilib.api.container;

import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.guilib.api.ISimpleScreenInfo;
import net.pitan76.mcpitanlib.midohra.screen.ScreenHandlerTypeWrapper;

public abstract class SimpleContainerGui extends ContainerGui implements ISimpleScreenInfo {

    protected SimpleContainerGui(MenuType<?> type, int syncId) {
        super(type, syncId);
    }

    protected SimpleContainerGui(MenuType<?> type, CreateMenuEvent e) {
        super(type, e);
    }

    protected SimpleContainerGui(ScreenHandlerTypeWrapper type, int syncId) {
        this(type.get(), syncId);
    }

    protected SimpleContainerGui(ScreenHandlerTypeWrapper type, CreateMenuEvent e) {
        this(type.get(), e);
    }
}
