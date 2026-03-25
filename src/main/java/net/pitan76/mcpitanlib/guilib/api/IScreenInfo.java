package net.pitan76.mcpitanlib.guilib.api;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.guilib.GuiTextures;

public interface IScreenInfo {
    default int getScreenBackgroundWidth() {
        return getScreenWidth();
    }

    default int getScreenBackgroundHeight() {
        return getScreenHeight();
    }

    int getScreenWidth();

    int getScreenHeight();

    default int getScreenX() {
        return -1;
    }

    default int getScreenY() {
        return -1;
    }

    default int getScreenTitleX() {
        return -1;
    }

    default int getScreenTitleY() {
        return -1;
    }

    default CompatIdentifier getTexture() {
        return GuiTextures.BASE_BACKGROUND;
    }
}
