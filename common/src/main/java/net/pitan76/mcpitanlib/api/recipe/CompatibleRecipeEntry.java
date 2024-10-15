package net.pitan76.mcpitanlib.api.recipe;

import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;

public class CompatibleRecipeEntry {
    private final RecipeEntry<?> entry;

    public String group = "";
    public RecipeUtil.CompatibilityCraftingRecipeCategory category = null;

    @Deprecated
    public CompatibleRecipeEntry(RecipeEntry<?> entry) {
        this.entry = entry;
    }

    public CompatibleRecipeEntry(Identifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ShapelessRecipe shapelessRecipe) {
        this.entry = new RecipeEntry<>(RegistryKey.of(RegistryKeys.RECIPE, id), shapelessRecipe);
        this.group = group;
        this.category = category;
    }

    public CompatibleRecipeEntry(CompatIdentifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ShapelessRecipe shapelessRecipe) {
        this(id.toMinecraft(), group, category, shapelessRecipe);
    }

    @Deprecated
    public RecipeEntry<?> getRecipeEntry() {
        return entry;
    }

    public Recipe<?> getRecipe() {
        Object object = entry.value();
        if (object instanceof Recipe<?>) {
            return (Recipe<?>) object;
        }
        return null;
    }

    public Identifier getId() {
        return entry.id().getValue();
    }

    public CompatIdentifier getCompatId() {
        return CompatIdentifier.fromMinecraft(getId());
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
