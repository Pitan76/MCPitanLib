package net.pitan76.mcpitanlib.api.recipe.v2;

import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
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
        this.entry = new RecipeEntry<>(RegistryKey.of(RegistryKeys.RECIPE, id), recipe);
        this.group = group;
        this.category = category;
    }

    public CompatRecipeEntry(CompatIdentifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, T recipe) {
        this(id.toMinecraft(), group, category, recipe);
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
        return entry.id().getValue();
    }

    public CompatIdentifier getCompatId() {
        return CompatIdentifier.fromMinecraft(getId());
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
