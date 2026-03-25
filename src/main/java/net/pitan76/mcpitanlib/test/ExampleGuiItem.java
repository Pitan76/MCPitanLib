package net.pitan76.mcpitanlib.test;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.v2.SimpleScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.CompatItem;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class ExampleGuiItem extends CompatItem implements SimpleScreenHandlerFactory {

    public ExampleGuiItem(CompatibleItemSettings settings) {
        super(settings);
    }

    @Override
    public StackActionResult onRightClick(ItemUseEvent e) {
        if (!e.isClient())
            e.user.openGuiScreen(this);

        return e.success();
    }

    @Override
    public Component getDisplayName(DisplayNameArgs args) {
        return TextUtil.literal("Example Title");
    }

    @Override
    public AbstractContainerMenu createMenu(CreateMenuEvent e) {
        return new ExampleScreenHandler(e);
    }
}
