package net.pitan76.mcpitanlib.api.recipe;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;

import java.util.Optional;

@Deprecated
public interface MatchGetter<I extends RecipeInput, T extends Recipe<I>> {
    Optional<CompatRecipeEntry<T>> getFirstMatch(CompatRecipeInput<I> input, Level world);
}
