package net.pitan76.mcpitanlib.api.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.predicates.PotionsPredicate;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.BrewingRecipe;
import net.minecraft.world.item.crafting.PotionIngredient;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.util.ItemUtil;
import net.pitan76.mcpitanlib.core.registry.DynamicRecipeRegistry;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.potion.PotionWrapper;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 醸造台のレシピを登録する。
 * <p>
 * 26.3 で醸造はデータパックのレシピ ({@code minecraft:brewing}) になり、
 * バニラの {@code PotionBrewing.Builder} も Fabric / NeoForge の登録用フックも消滅した。
 * そのためここでは {@link BrewingRecipe} を直接組み立て、
 * {@link DynamicRecipeRegistry} 経由でレシピレジストリへ流し込んでいる。
 * <p>
 * 醸造レシピはワールドを読み込むたびに組み直されるため、登録内容は保持し続ける。
 */
public class BrewingRecipeUtil {

    /**
     * バニラがポーション系レシピを生成するときの器。
     */
    private static final List<Item> POTION_CONTAINERS = List.of(Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION);

    private static final List<DynamicRecipeRegistry.Injector> recipes = new CopyOnWriteArrayList<>();
    private static boolean initialized = false;

    private static void add(DynamicRecipeRegistry.Injector recipe) {
        if (!initialized) {
            initialized = true;
            DynamicRecipeRegistry.register(BrewingRecipeUtil::apply);
        }

        recipes.add(recipe);
    }

    private static void apply(DynamicRecipeRegistry.Context context) {
        for (DynamicRecipeRegistry.Injector recipe : recipes) {
            recipe.inject(context);
        }
    }

    /**
     * 水入り瓶などのベースに材料を加えてポーションを作るレシピ。
     * <p>
     * バニラと同じく、通常 / スプラッシュ / 残留の3種を生成する。
     */
    public static void registerPotionRecipe(PotionWrapper input, ItemWrapper ingredient, PotionWrapper output) {
        registerPotionRecipe(input.getEntry(), ingredient.get(), output.getEntry());
    }

    public static void registerPotionRecipe(RegistryResult<Potion> input, ItemWrapper ingredient, RegistryResult<Potion> output) {
        registerPotionRecipe(PotionUtil.toEntry(input), ingredient.get(), PotionUtil.toEntry(output));
    }

    public static void registerPotionRecipe(Holder<Potion> input, Item ingredient, Holder<Potion> output) {
        add(context -> {
            for (Item container : POTION_CONTAINERS) {
                context.add(
                        buildId(container, input, ingredient),
                        new BrewingRecipe(
                                PotionIngredient.of(container, PotionsPredicate.ofPotion(input)),
                                PotionIngredient.of(ingredient),
                                createPotionTemplate(container, output)
                        )
                );
            }
        });
    }

    /**
     * ポーションの器そのものを変えるレシピ (水入り瓶 → スプラッシュ等)。
     */
    public static void registerItemRecipe(ItemWrapper input, ItemWrapper ingredient, ItemWrapper output) {
        registerItemRecipe(input.get(), ingredient.get(), output.get());
    }

    public static void registerItemRecipe(Item input, Item ingredient, Item output) {
        add(context -> context.add(
                buildId(input, null, ingredient),
                new BrewingRecipe(
                        PotionIngredient.of(input),
                        PotionIngredient.of(ingredient),
                        new ItemStackTemplate(output)
                )
        ));
    }

    /**
     * 醸造の入力として使えるアイテムを追加する。
     * 26.3 では「醸造できる器」という概念がレシピ側に吸収されたため、
     * 単体では意味を持たない。{@link #registerPotionRecipe} / {@link #registerItemRecipe} を使うこと。
     */
    public static void registerPotionType(ItemWrapper item) {
    }

    private static ItemStackTemplate createPotionTemplate(Item container, Holder<Potion> potion) {
        return new ItemStackTemplate(container, DataComponentPatch.builder()
                .set(DataComponents.POTION_CONTENTS, new PotionContents(potion))
                .build());
    }

    private static Identifier buildId(Item input, Holder<Potion> inputPotion, Item ingredient) {
        StringBuilder path = new StringBuilder("brewing/");
        path.append(ItemUtil.toID(input).getPath());
        if (inputPotion != null && inputPotion.unwrapKey().isPresent())
            path.append('_').append(inputPotion.unwrapKey().get().identifier().getPath());
        path.append('_').append(ItemUtil.toID(ingredient).getPath());

        return Identifier.fromNamespaceAndPath("mcpitanlib", path.toString());
    }
}
