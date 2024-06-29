package net.pitan76.mcpitanlib.test;

import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

public class ExampleModClient {
    public static void init() {
        CompatRegistryClient.registerScreen(ExampleMod.EXAMPLE_SCREENHANDLER.getOrNull(), ExampleScreen::new);
    }
}
