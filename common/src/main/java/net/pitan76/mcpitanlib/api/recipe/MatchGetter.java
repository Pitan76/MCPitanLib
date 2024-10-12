package net.pitan76.mcpitanlib.api.recipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;

import java.util.Optional;

public interface MatchGetter<I extends Inventory, T extends Recipe<I>> {
    Optional<CompatRecipeEntry<I>> getFirstMatch(CompatRecipeInput<I> input, World world);
}
