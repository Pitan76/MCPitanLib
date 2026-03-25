package net.pitan76.mcpitanlib.api.simple.block;

import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.gui.v2.SimpleScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public abstract class SimpleGuiBlock extends ExtendBlock implements SimpleScreenHandlerFactory {

    public SimpleGuiBlock(Settings settings) {
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
}
