package net.pitan76.mcpitanlib.api.recipe;

import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatRecipeType<T extends Recipe<?>> {
    public static final CompatRecipeType<CraftingRecipe> CRAFTING = new CompatRecipeType<>(RecipeType.CRAFTING);
    public static final CompatRecipeType<SmeltingRecipe> SMELTING = new CompatRecipeType<>(RecipeType.SMELTING);
    public static final CompatRecipeType<BlastingRecipe> BLASTING = new CompatRecipeType<>(RecipeType.BLASTING);
    public static final CompatRecipeType<SmokingRecipe> SMOKING = new CompatRecipeType<>(RecipeType.SMOKING);
    public static final CompatRecipeType<CampfireCookingRecipe> CAMPFIRE_COOKING = new CompatRecipeType<>(RecipeType.CAMPFIRE_COOKING);
    public static final CompatRecipeType<StonecuttingRecipe> STONECUTTING = new CompatRecipeType<>(RecipeType.STONECUTTING);
    public static final CompatRecipeType<SmithingRecipe> SMITHING = new CompatRecipeType<>(RecipeType.SMITHING);

    private final RecipeType<T> type;

    public CompatRecipeType(String id) {
        this(RecipeType.register(id));
    }

    public CompatRecipeType(RecipeType<T> type) {
        this.type = type;
    }

    public RecipeType<T> getType() {
        return type;
    }

    public CompatIdentifier getName() {
        Identifier id = Registry.RECIPE_TYPE.getId(type);
        if (id == null) return CompatIdentifier.empty();

        return CompatIdentifier.fromMinecraft(id);
    }

    public static CompatRecipeType<?> of(CompatIdentifier id) {
        RecipeType<?> type = Registry.RECIPE_TYPE.get(id.toMinecraft());
        if (type == null) return null;

        return new CompatRecipeType<>(type);
    }

    public static <T extends Recipe<?>> CompatRecipeType<T> of(CompatIdentifier id, Class<T> clazz) {
        return (CompatRecipeType<T>) of(id);
    }

    public static <T extends Recipe<?>> CompatRecipeType<T> of(RecipeType<T> type) {
        return new CompatRecipeType<>(type);
    }
}
