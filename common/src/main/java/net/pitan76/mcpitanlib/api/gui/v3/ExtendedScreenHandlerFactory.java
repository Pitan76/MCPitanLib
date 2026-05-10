package net.pitan76.mcpitanlib.api.gui.v3;

import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandler;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.text.TextComponent;

public interface ExtendedScreenHandlerFactory extends net.pitan76.mcpitanlib.api.gui.v2.ExtendedScreenHandlerFactory {
    TextComponent getDisplayText(DisplayNameArgs args);

    @Deprecated
    @Override
    default Text getDisplayName(DisplayNameArgs args) {
        return getDisplayText(args).getText();
    }

    @Override
    default Text getDisplayName() {
        return getDisplayText(new DisplayNameArgs()).getText();
    }

    @Override
    SimpleScreenHandler createMenu(CreateMenuEvent event);
}
