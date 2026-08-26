package net.pitan76.mcpitanlib.api.potion;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.core.Holder;
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
 * {@link PotionBrewing.Builder} を渡してくるのでそこに寄せている。
 * <p>
 * 醸造レシピはワールドを読み込むたびに組み直されるため、登録内容は保持し続ける。
 */
public class BrewingRecipeUtil {

    private static final List<Consumer<PotionBrewing.Builder>> recipes = new CopyOnWriteArrayList<>();
    private static boolean initialized = false;

    /**
     * プラットフォーム側のイベントに接続する。初回登録時に自動で呼ばれる。
     */
    @ExpectPlatform
    public static void init() {
        throw new AssertionError();
    }

    public static void add(Consumer<PotionBrewing.Builder> recipe) {
        if (!initialized) {
            initialized = true;
            init();
        }

        recipes.add(recipe);
    }

    /**
     * プラットフォーム実装から呼ばれる。
     */
    public static void apply(PotionBrewing.Builder builder) {
        for (Consumer<PotionBrewing.Builder> recipe : recipes) {
            recipe.accept(builder);
        }
    }

    /**
     * 水入り瓶などのベースに材料を加えてポーションを作るレシピ。
     */
    public static void registerPotionRecipe(PotionWrapper input, ItemWrapper ingredient, PotionWrapper output) {
        add(builder -> builder.addMix(input.getEntry(), ingredient.get(), output.getEntry()));
    }

    public static void registerPotionRecipe(RegistryResult<Potion> input, ItemWrapper ingredient, RegistryResult<Potion> output) {
        add(builder -> builder.addMix(PotionUtil.toEntry(input), ingredient.get(), PotionUtil.toEntry(output)));
    }

    public static void registerPotionRecipe(Holder<Potion> input, Item ingredient, Holder<Potion> output) {
        add(builder -> builder.addMix(input, ingredient, output));
    }

    /**
     * ポーションの器そのものを変えるレシピ (水入り瓶 → スプラッシュ等)。
     */
    public static void registerItemRecipe(ItemWrapper input, ItemWrapper ingredient, ItemWrapper output) {
        add(builder -> builder.addContainerRecipe(input.get(), ingredient.get(), output.get()));
    }

    /**
     * 醸造の入力として使えるアイテムを追加する。
     */
    public static void registerPotionType(ItemWrapper item) {
        add(builder -> builder.addContainer(item.get()));
    }
}
