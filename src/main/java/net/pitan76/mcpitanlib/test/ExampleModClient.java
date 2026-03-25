package net.pitan76.mcpitanlib.test;

import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.mcpitanlib.guilib.GuiRegistry;

public class ExampleModClient {
    public static void init() {
        CompatRegistryClient.registerScreen(ExampleMod.MOD_ID, ExampleMod.EXAMPLE_SCREENHANDLER.get(), ExampleScreen::new);

        GuiRegistry.registerSimpleContainerGui(ExampleMod.MOD_ID, ExampleMod.EXAMPLE_CONTAINER_GUI.get());
    }
}
