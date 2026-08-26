package net.pitan76.mcpitanlib.api.potion;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.potion.PotionWrapper;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * 醸造台のレシピを登録する。
 * <p>
 * Fabricは {@code FabricBrewingRecipeRegistryBuilder.BUILD}、NeoForgeは
 * {@code RegisterBrewingRecipesEvent} と入口が違うが、どちらもバニラの
 * {@link BrewingRecipeRegistry.Builder} を渡してくるのでそこに寄せている。
 * <p>
 * 醸造レシピはワールドを読み込むたびに組み直されるため、登録内容は保持し続ける。
 */
public class BrewingRecipeUtil {

    private static final List<Consumer<BrewingRecipeRegistry.Builder>> recipes = new CopyOnWriteArrayList<>();
    private static boolean initialized = false;

    /**
     * プラットフォーム側のイベントに接続する。初回登録時に自動で呼ばれる。
     */
    @ExpectPlatform
    public static void init() {
        throw new AssertionError();
    }

    public static void add(Consumer<BrewingRecipeRegistry.Builder> recipe) {
        if (!initialized) {
            initialized = true;
            init();
        }

        recipes.add(recipe);
    }

    /**
     * プラットフォーム実装から呼ばれる。
     */
    public static void apply(BrewingRecipeRegistry.Builder builder) {
        for (Consumer<BrewingRecipeRegistry.Builder> recipe : recipes) {
            recipe.accept(builder);
        }
    }

    /**
     * 水入り瓶などのベースに材料を加えてポーションを作るレシピ。
     */
    public static void registerPotionRecipe(PotionWrapper input, ItemWrapper ingredient, PotionWrapper output) {
        add(builder -> builder.registerPotionRecipe(input.getEntry(), ingredient.get(), output.getEntry()));
    }

    public static void registerPotionRecipe(RegistryResult<Potion> input, ItemWrapper ingredient, RegistryResult<Potion> output) {
        add(builder -> builder.registerPotionRecipe(PotionUtil.toEntry(input), ingredient.get(), PotionUtil.toEntry(output)));
    }

    public static void registerPotionRecipe(RegistryEntry<Potion> input, Item ingredient, RegistryEntry<Potion> output) {
        add(builder -> builder.registerPotionRecipe(input, ingredient, output));
    }

    /**
     * ポーションの器そのものを変えるレシピ (水入り瓶 → スプラッシュ等)。
     */
    public static void registerItemRecipe(ItemWrapper input, ItemWrapper ingredient, ItemWrapper output) {
        add(builder -> builder.registerItemRecipe(input.get(), ingredient.get(), output.get()));
    }

    /**
     * 醸造の入力として使えるアイテムを追加する。
     */
    public static void registerPotionType(ItemWrapper item) {
        add(builder -> builder.registerPotionType(item.get()));
    }
}
