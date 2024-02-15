package net.pitan76.mcpitanlib.test;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandler;

public class ExampleScreenHandler extends SimpleScreenHandler {

    public ExampleScreenHandler(int i, PlayerInventory playerInventory) {
        super(ExampleMod.supplierEXAMPLE_SCREENHANDLER.getOrNull(), i);

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
