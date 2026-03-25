package net.pitan76.mcpitanlib.guilib.api.container;

import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.guilib.api.ISimpleScreenInfo;

public abstract class BlockEntityContainerGui extends ContainerGui implements ISimpleScreenInfo {
    protected BlockEntityContainerGui(ScreenHandlerType<?> type, CreateMenuEvent e) {
        super(type, e);
    }
}
