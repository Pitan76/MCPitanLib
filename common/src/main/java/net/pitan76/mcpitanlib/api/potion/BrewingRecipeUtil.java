package net.pitan76.mcpitanlib.api.potion;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.potion.PotionWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 醸造台のレシピを登録する。
 * <p>
 * このバージョンのバニラは登録メソッドがprivateなので、プラットフォームごとの入口を使う。
 */
public class BrewingRecipeUtil {

    private static final List<Runnable> deferredRecipes = new ArrayList<>();

    /**
     * 水入り瓶などのベースに材料を加えてポーションを作るレシピ。
     */
    @ExpectPlatform
    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        throw new AssertionError();
    }

    public static void registerPotionRecipe(PotionWrapper input, ItemWrapper ingredient, PotionWrapper output) {
        deferredRecipes.add(() -> registerPotionRecipe(input.get(), ingredient.get(), output.get()));
    }

    public static void registerPotionRecipe(RegistryResult<Potion> input, ItemWrapper ingredient, RegistryResult<Potion> output) {
        deferredRecipes.add(() -> registerPotionRecipe(input.get(), ingredient.get(), output.get()));
    }

    /**
     * ポーションの器そのものを変えるレシピ (水入り瓶 → スプラッシュ等)。
     */
    @ExpectPlatform
    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        throw new AssertionError();
    }

    public static void registerItemRecipe(ItemWrapper input, ItemWrapper ingredient, ItemWrapper output) {
        registerItemRecipe(input.get(), ingredient.get(), output.get());
    }

    /**
     * 遅延登録されたレシピを一括実行する。～1.20.4
     * レジストリが凍結された後に呼び出すこと。
     */
    public static void executeDeferredRecipes() {
        for (Runnable recipe : deferredRecipes) {
            recipe.run();
        }
        deferredRecipes.clear();
    }
}
