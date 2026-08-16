package net.pitan76.mcpitanlib.api.client;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;

public class SimpleOptionsScreen extends SimpleScreen {

    protected final Screen parent;
    protected final Options gameOptions;

    public SimpleOptionsScreen(Component title, Screen parent, Options gameOptions) {
        super(title);
        this.parent = parent;
        this.gameOptions = gameOptions;
    }

    @Override
    public void removed() {
        client.options.save();
    }

    @Override
    public void onClose() {
        ClientUtil.setScreen(this.parent);
    }
}
