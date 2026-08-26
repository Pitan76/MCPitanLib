package net.pitan76.mcpitanlib.api.potion.fabric;

import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.pitan76.mcpitanlib.api.potion.BrewingRecipeUtil;

public class BrewingRecipeUtilImpl {

    public static void init() {
        FabricPotionBrewingBuilder.BUILD.register(BrewingRecipeUtil::apply);
    }
}
