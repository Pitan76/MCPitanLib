package net.pitan76.mcpitanlib.api.simple.item;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.v2.SimpleScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.CompatItem;
import net.pitan76.mcpitanlib.api.util.StackActionResult;

public class SimpleGuiItem extends CompatItem implements SimpleScreenHandlerFactory {

    public ScreenHandlerFactory factory;
    public Component name;

    public SimpleGuiItem(CompatibleItemSettings settings, ScreenHandlerFactory factory, Component name) {
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
    public StackActionResult onRightClick(ItemUseEvent e) {
        if (!e.isClient())
            e.user.openGuiScreen(this);

        return e.success();
    }

    @Override
    public Component getDisplayName(DisplayNameArgs args) {
        if (name == null)
            return getName();

        return name;
    }

    @Override
    public AbstractContainerMenu createMenu(CreateMenuEvent e) {
        if (factory == null)
            return null;

        return factory.create(e);
    }

    @FunctionalInterface
    public interface ScreenHandlerFactory {
        AbstractContainerMenu create(CreateMenuEvent e);
    }
}
