package net.pitan76.mcpitanlib.test;

import net.pitan76.mcpitanlib.api.client.registry.ArchRegistryClient;

public class ExampleModClient {
    public static void init() {
        ArchRegistryClient.registerScreen(ExampleMod.supplierEXAMPLE_SCREENHANDLER.getOrNull(), ExampleScreen::new);
    }
}
