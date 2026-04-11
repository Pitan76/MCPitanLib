package net.pitan76.mcpitanlib.test;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.v2.SimpleScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class ExampleGuiBlock extends CompatBlock implements SimpleScreenHandlerFactory {

    public ExampleGuiBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    @Override
    public CompatActionResult onRightClick(BlockUseEvent e) {
        if (!e.isClient())
            e.player.openGuiScreen(this);

        return e.success();
    }

    @Override
    public Component getDisplayName(DisplayNameArgs args) {
        return getName();
    }

    @Override
    public AbstractContainerMenu createMenu(CreateMenuEvent e) {
        return new ExampleScreenHandler(e);
    }
}
