package net.pitan76.mcpitanlib.api.potion.fabric;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.pitan76.mcpitanlib.api.potion.BrewingRecipeUtil;

public class BrewingRecipeUtilImpl {

    public static void init() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(BrewingRecipeUtil::apply);
    }
}
