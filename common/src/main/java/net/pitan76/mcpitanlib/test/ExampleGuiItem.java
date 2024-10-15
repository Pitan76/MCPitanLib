package net.pitan76.mcpitanlib.test;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.v2.SimpleScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class ExampleGuiItem extends ExtendItem implements SimpleScreenHandlerFactory {

    public ExampleGuiItem(CompatibleItemSettings settings) {
        super(settings);
    }

    @Override
    public CompatActionResult onRightClick(ItemUseEvent e) {
        if (!e.isClient())
            e.user.openGuiScreen(this);

        return e.success();
    }

    @Override
    public Text getDisplayName(DisplayNameArgs args) {
        return TextUtil.literal("Example Title");
    }

    @Override
    public ScreenHandler createMenu(CreateMenuEvent e) {
        return new ExampleScreenHandler(e);
    }
}
