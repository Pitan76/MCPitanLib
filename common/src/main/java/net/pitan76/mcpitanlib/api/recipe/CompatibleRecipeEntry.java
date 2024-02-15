package net.pitan76.mcpitanlib.api.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;

public class CompatibleRecipeEntry {
    private final Recipe<?> recipe;

    private Identifier id;

    public String group = "";
    public RecipeUtil.CompatibilityCraftingRecipeCategory category = null;

    @Deprecated
    public CompatibleRecipeEntry(Recipe<?> recipe) {
        this.recipe = recipe;
    }

    public CompatibleRecipeEntry(Identifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ShapelessRecipe shapelessRecipe) {
        this.recipe = shapelessRecipe;
        this.id = id;
        this.group = group;
        this.category = category;
    }

    public Recipe<?> getRecipe() {
        return recipe;
    }

    public Identifier getId() {
        return id;
    }

    public RecipeType<?> getType() {
        Recipe<?> recipe = getRecipe();
        if (recipe == null) return null;

        return recipe.getType();
    }

    public RecipeSerializer<?> getSerializer() {
        Recipe<?> recipe = getRecipe();
        if (recipe == null) return null;

        return recipe.getSerializer();
    }

    @Nullable
    public RecipeUtil.CompatibilityCraftingRecipeCategory getCategory() {
        return category;
    }

    public String getGroup() {
        return group;
    }
}
