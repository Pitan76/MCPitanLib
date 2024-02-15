package net.pitan76.mcpitanlib.test;

import net.pitan76.mcpitanlib.api.client.registry.ArchRegistryClient;

public class ExampleModClient {
    public static void init() {
        ArchRegistryClient.registerScreen(ExampleMod.EXAMPLE_SCREENHANDLER.getOrNull(), ExampleScreen::new);
    }
}
