package net.pitan76.mcpitanlib.api.recipe;

import net.minecraft.recipe.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class CompatibleRecipeEntry {
    private final RecipeHolder<?> entry;

    public String group = "";
    public RecipeUtil.CompatibilityCraftingRecipeCategory category = null;

    @Deprecated
    public CompatibleRecipeEntry(RecipeHolder<?> entry) {
        this.entry = entry;
    }

    public CompatibleRecipeEntry(Identifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ShapelessRecipe shapelessRecipe) {
        this.entry = new RecipeHolder<>(ResourceKey.create(Registries.RECIPE, id), shapelessRecipe);
        this.group = group;
        this.category = category;
    }

    public CompatibleRecipeEntry(CompatIdentifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, ShapelessRecipe shapelessRecipe) {
        this(id.toMinecraft(), group, category, shapelessRecipe);
    }

    @Deprecated
    public RecipeHolder<?> getRecipeEntry() {
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
        return entry.id().identifier();
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
