package net.pitan76.mcpitanlib.api.simple.item;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.v2.SimpleScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.CompatItem;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class SimpleGuiItem extends CompatItem implements SimpleScreenHandlerFactory {

    public ScreenHandlerFactory factory;
    public Text name;

    public SimpleGuiItem(CompatibleItemSettings settings, ScreenHandlerFactory factory, Text name) {
        super(settings);
        this.factory = factory;
        this.name = name;
    }

    public SimpleGuiItem(CompatibleItemSettings settings, ScreenHandlerFactory factory) {
        super(settings);
        this.factory = factory;
    }

    public SimpleGuiItem(CompatibleItemSettings settings) {
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
        if (name == null)
            return getName();

        return name;
    }

    @Override
    public ScreenHandler createMenu(CreateMenuEvent e) {
        if (factory == null)
            return null;

        return factory.create(e);
    }

    @FunctionalInterface
    public interface ScreenHandlerFactory {
        ScreenHandler create(CreateMenuEvent e);
    }
}
