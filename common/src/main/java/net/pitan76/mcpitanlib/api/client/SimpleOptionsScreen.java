package net.pitan76.mcpitanlib.api.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

public class SimpleOptionsScreen extends SimpleScreen {

    protected final Screen parent;
    protected final GameOptions gameOptions;

    public SimpleOptionsScreen(Text title, Screen parent, GameOptions gameOptions) {
        super(title);
        this.parent = parent;
        this.gameOptions = gameOptions;
    }

    @Override
    public void removed() {
        client.options.write();
    }

    @Override
    public void close() {
        client.setScreen(this.parent);
    }
}
