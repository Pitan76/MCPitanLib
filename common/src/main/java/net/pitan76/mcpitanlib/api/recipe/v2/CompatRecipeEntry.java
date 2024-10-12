package net.pitan76.mcpitanlib.api.recipe.v2;

import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;

public class CompatRecipeEntry<T extends Inventory> {
    private final Recipe<T> recipe;

    private Identifier id;

    public String group = "";
    public RecipeUtil.CompatibilityCraftingRecipeCategory category = null;

    @Deprecated
    public CompatRecipeEntry(Recipe<T> recipe) {
        this.recipe = recipe;
    }

    public CompatRecipeEntry(Identifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, Recipe<T> recipe) {
        this.recipe = recipe;
        this.id = id;
        this.group = group;
        this.category = category;
    }

    public boolean isNull() {
        return recipe == null;
    }

    public Recipe<T> getRecipe() {
        return recipe;
    }

    public Identifier getId() {
        return id;
    }

    public RecipeType<?> getType() {
        Recipe<T> recipe = getRecipe();
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
