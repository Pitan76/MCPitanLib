package net.pitan76.mcpitanlib.api.recipe.v2;

import net.minecraft.recipe.*;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;

public class CompatRecipeEntry<T extends Recipe<?>> {
    private final RecipeEntry<T> entry;

    public String group = "";
    public RecipeUtil.CompatibilityCraftingRecipeCategory category = null;

    @Deprecated
    public CompatRecipeEntry(RecipeEntry<T> entry) {
        this.entry = entry;
    }

    public CompatRecipeEntry(Identifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, T recipe) {
        this.entry = new RecipeEntry<>(id, recipe);
        this.group = group;
        this.category = category;
    }

    public boolean isNull() {
        return entry == null;
    }

    @Deprecated
    public RecipeEntry<?> getRecipeEntry() {
        return entry;
    }

    public T getRecipe() {
        return entry.value();
    }

    public Identifier getId() {
        return entry.id();
    }

    public RecipeType<?> getType() {
        T recipe = getRecipe();
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
