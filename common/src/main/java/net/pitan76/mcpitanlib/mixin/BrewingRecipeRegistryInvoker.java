package net.pitan76.mcpitanlib.mixin;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * このバージョンのバニラは醸造レシピの登録メソッドがprivateで、
 * FabricにもForgeにも共通の入口が無いため直接呼べるようにする。
 */
@Mixin(BrewingRecipeRegistry.class)
public interface BrewingRecipeRegistryInvoker {

    @Invoker("registerPotionRecipe")
    static void registerPotionRecipe_invoke(Potion input, Item ingredient, Potion output) {
        throw new AssertionError();
    }

    @Invoker("registerItemRecipe")
    static void registerItemRecipe_invoke(Item input, Item ingredient, Item output) {
        throw new AssertionError();
    }

    @Invoker("registerPotionType")
    static void registerPotionType_invoke(Item item) {
        throw new AssertionError();
    }
}
