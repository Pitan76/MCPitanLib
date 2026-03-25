package net.pitan76.mcpitanlib.api.recipe.v3;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.recipe.CompatibleRecipeEntry;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.RecipeUtil;
import net.pitan76.mcpitanlib.midohra.recipe.entry.RecipeEntry;

public class CompatRecipe {
    public Recipe<?> recipe;
    public Identifier id;

    public CompatRecipe(Recipe<?> recipe, Identifier id) {
        this.recipe = recipe;
        this.id = id;
    }

    @Deprecated
    public CompatRecipe(net.minecraft.recipe.RecipeEntry<?> entry) {
        this(entry.value(), entry.id().getValue());
    }

    @Deprecated
    public CompatRecipe(Recipe<?> recipe) {
        this.recipe = recipe;
        this.id = RecipeUtil.getId(recipe);
    }

    public boolean isNull() {
        return recipe == null;
    }

    public Recipe<?> getRecipe() {
        return recipe;
    }

    public Identifier getId() {
        return id;
    }

    public CompatIdentifier getCompatId() {
        return CompatIdentifier.fromMinecraft(getId());
    }

    public RecipeType<?> getType() {
        Recipe<?> recipe = getRecipe();
        if (recipe == null) return null;

        return recipe.getType();
    }

    @Deprecated
    public CompatRecipeEntry<?> getRecipeEntry() {
        return new CompatRecipeEntry<>(getId(), "", null, getRecipe());
    }

    @Deprecated
    public CompatibleRecipeEntry getCompatibleRecipeEntry() {
        return new CompatibleRecipeEntry(new net.minecraft.recipe.RecipeEntry<Recipe<?>>(RegistryKey.of(RegistryKeys.RECIPE, getId()), getRecipe()));
    }

    // MidohraAPI
    public net.pitan76.mcpitanlib.midohra.recipe.Recipe getMidohraRecipe() {
        if (getMidohraType() == net.pitan76.mcpitanlib.midohra.recipe.RecipeType.CRAFTING) {
            return net.pitan76.mcpitanlib.midohra.recipe.CraftingRecipe.of((net.minecraft.recipe.CraftingRecipe) getRecipe());
        }

        return net.pitan76.mcpitanlib.midohra.recipe.Recipe.of(getRecipe());
    }

    public net.pitan76.mcpitanlib.midohra.recipe.entry.RecipeEntry getMidohraRecipeEntry() {
        if (getMidohraType() == net.pitan76.mcpitanlib.midohra.recipe.RecipeType.CRAFTING) {
            return net.pitan76.mcpitanlib.midohra.recipe.entry.CraftingRecipeEntry.of((net.minecraft.recipe.CraftingRecipe) getRecipe(), getCompatId());
        }

        return RecipeEntry.of(getRecipe(), getCompatId());
    }

    public net.pitan76.mcpitanlib.midohra.recipe.RecipeType getMidohraType() {
        return net.pitan76.mcpitanlib.midohra.recipe.RecipeType.of(getType());
    }
}
