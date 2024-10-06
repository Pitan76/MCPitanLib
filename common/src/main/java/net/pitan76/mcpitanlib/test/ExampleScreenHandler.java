package net.pitan76.mcpitanlib.test;

import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandler;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;

public class ExampleScreenHandler extends SimpleScreenHandler {

    public ExampleScreenHandler(CreateMenuEvent e) {
        super(ExampleMod.EXAMPLE_SCREENHANDLER.get(), e);

    }

    @Override
    public ItemStack quickMoveOverride(Player player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(Player player) {
        return true;
    }
}
