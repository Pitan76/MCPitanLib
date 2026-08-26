package net.pitan76.mcpitanlib.api.potion.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.pitan76.mcpitanlib.api.potion.BrewingRecipeUtil;

/**
 * NeoForgeはイベント購読が静的に行われるため、initは何もしない。
 */
@EventBusSubscriber(modid = "mcpitanlib")
public class BrewingRecipeUtilImpl {

    public static void init() {
    }

    @SubscribeEvent
    public static void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event) {
        BrewingRecipeUtil.apply(event.getBuilder());
    }
}
