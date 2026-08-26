package net.pitan76.mcpitanlib.api.potion;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.potion.PotionWrapper;
import net.pitan76.mcpitanlib.mixin.BrewingRecipeRegistryInvoker;

/**
 * 醸造台のレシピを登録する。
 * <p>
 * このバージョンのバニラは登録メソッドがprivateなので、Mixinのinvoker経由で呼ぶ。
 */
public class BrewingRecipeUtil {

    /**
     * 水入り瓶などのベースに材料を加えてポーションを作るレシピ。
     */
    public static void registerPotionRecipe(Potion input, Item ingredient, Potion output) {
        BrewingRecipeRegistryInvoker.registerPotionRecipe_invoke(input, ingredient, output);
    }

    public static void registerPotionRecipe(PotionWrapper input, ItemWrapper ingredient, PotionWrapper output) {
        registerPotionRecipe(input.get(), ingredient.get(), output.get());
    }

    public static void registerPotionRecipe(RegistryResult<Potion> input, ItemWrapper ingredient, RegistryResult<Potion> output) {
        registerPotionRecipe(input.get(), ingredient.get(), output.get());
    }

    /**
     * ポーションの器そのものを変えるレシピ (水入り瓶 → スプラッシュ等)。
     */
    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        BrewingRecipeRegistryInvoker.registerItemRecipe_invoke(input, ingredient, output);
    }

    public static void registerItemRecipe(ItemWrapper input, ItemWrapper ingredient, ItemWrapper output) {
        registerItemRecipe(input.get(), ingredient.get(), output.get());
    }

    /**
     * 醸造の入力として使えるアイテムを追加する。
     */
    public static void registerPotionType(ItemWrapper item) {
        BrewingRecipeRegistryInvoker.registerPotionType_invoke(item.get());
    }
}
