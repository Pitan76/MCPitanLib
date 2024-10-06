package net.pitan76.mcpitanlib.api.simple.item;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.v2.SimpleScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItem;

public class SimpleGuiItem extends ExtendItem implements SimpleScreenHandlerFactory {

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
    public TypedActionResult<ItemStack> onRightClick(ItemUseEvent e) {
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
