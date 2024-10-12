package net.pitan76.mcpitanlib.api.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;

import java.util.Optional;

public interface MatchGetter<I extends RecipeInput, T extends Recipe<I>> {
    Optional<CompatRecipeEntry<T>> getFirstMatch(CompatRecipeInput<I> input, World world);
}
