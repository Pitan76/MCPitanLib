package net.pitan76.mcpitanlib.test;

import net.pitan76.mcpitanlib.guilib.GuiRegistry;

public class ExampleModClient {
    public static void init() {
        // Forge/NeoForgeは登録が遅延されるため、値ではなくSupplierのまま渡す
        GuiRegistry.register(ExampleMod.MOD_ID, ExampleMod.EXAMPLE_SCREENHANDLER, ExampleScreen::new);

        GuiRegistry.registerSimpleContainerGui(ExampleMod.MOD_ID, ExampleMod.EXAMPLE_CONTAINER_GUI);
    }
}
