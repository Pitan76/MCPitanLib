package net.pitan76.mcpitanlib.test;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.client.gui.screen.CompatInventoryScreen;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatibleTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class ExampleScreen extends CompatInventoryScreen<ExampleScreenHandler> {

    public static CompatIdentifier GUI = CompatIdentifier.of("textures/gui/container/blast_furnace.png");

    public ExampleScreen(ExampleScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    public CompatIdentifier getCompatTexture() {
        return GUI;
    }

    @Override
    public void initOverride() {
        super.initOverride();
        System.out.println("hogehogehoge1111");
        this.addDrawableCTBW(new CompatibleTexturedButtonWidget(0,  0, 30, 30, 0, 0, GUI, (buttonWidget) -> {
            System.out.println("hogehoge");
        }));
    }
}
