package net.pitan76.mcpitanlib.test;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.v2.SimpleScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class ExampleGuiBlock extends ExtendBlock implements SimpleScreenHandlerFactory {

    public ExampleGuiBlock(Settings settings) {
        super(settings);
    }

    @Override
    public CompatActionResult onRightClick(BlockUseEvent e) {
        if (e.isClient())
            e.player.openGuiScreen(this);

        return e.success();
    }

    @Override
    public Text getDisplayName(DisplayNameArgs args) {
        return getName();
    }

    @Override
    public ScreenHandler createMenu(CreateMenuEvent e) {
        return new ExampleScreenHandler(e);
    }
}
