package net.pitan76.mcpitanlib.test;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.client.gui.screen.CompatInventoryScreen;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatibleTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class ExampleContainerGuiScreen extends CompatInventoryScreen<ExampleScreenHandler> {

    public static CompatIdentifier GUI = CompatIdentifier.of("textures/gui/container/blast_furnace.png");

    public ExampleContainerGuiScreen(ExampleScreenHandler handler, PlayerInventory inventory, Text title) {
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
