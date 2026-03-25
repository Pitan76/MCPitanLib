package net.pitan76.mcpitanlib.api.recipe;

import net.minecraft.recipe.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

@Deprecated
public class CompatRecipeType<T extends Recipe<?>> {
    public static final CompatRecipeType<CraftingRecipe> CRAFTING = new CompatRecipeType<>(RecipeType.CRAFTING);
    public static final CompatRecipeType<SmeltingRecipe> SMELTING = new CompatRecipeType<>(RecipeType.SMELTING);
    public static final CompatRecipeType<BlastingRecipe> BLASTING = new CompatRecipeType<>(RecipeType.BLASTING);
    public static final CompatRecipeType<SmokingRecipe> SMOKING = new CompatRecipeType<>(RecipeType.SMOKING);
    public static final CompatRecipeType<CampfireCookingRecipe> CAMPFIRE_COOKING = new CompatRecipeType<>(RecipeType.CAMPFIRE_COOKING);
    public static final CompatRecipeType<StonecutterRecipe> STONECUTTING = new CompatRecipeType<>(RecipeType.STONECUTTING);
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
        Identifier id = BuiltInRegistries.RECIPE_TYPE.getKey(type);
        if (id == null) return CompatIdentifier.empty();

        return CompatIdentifier.fromMinecraft(id);
    }

    public static CompatRecipeType<?> of(CompatIdentifier id) {
        RecipeType<?> type = BuiltInRegistries.RECIPE_TYPE.getValue(id.toMinecraft());
        if (type == null) return null;

        return new CompatRecipeType<>(type);
    }

    public static <T extends Recipe<?>> CompatRecipeType<T> of(CompatIdentifier id, Class<T> clazz) {
        return (CompatRecipeType<T>) of(id);
    }

    public static <T extends Recipe<?>> CompatRecipeType<T> of(RecipeType<T> type) {
        return new CompatRecipeType<>(type);
    }

    public net.pitan76.mcpitanlib.midohra.recipe.RecipeType toMidohra() {
        return net.pitan76.mcpitanlib.midohra.recipe.RecipeType.of(type);
    }
}
