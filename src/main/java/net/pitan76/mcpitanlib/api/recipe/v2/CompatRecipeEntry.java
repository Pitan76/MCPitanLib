package net.pitan76.mcpitanlib.api.recipe.v2;

import net.minecraft.recipe.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.RecipeUtil;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class CompatRecipeEntry<T extends Recipe<?>> {
    private final RecipeHolder<T> entry;

    public String group = "";
    public RecipeUtil.CompatibilityCraftingRecipeCategory category = null;

    @Deprecated
    public CompatRecipeEntry(RecipeHolder<T> entry) {
        this.entry = entry;
    }

    public CompatRecipeEntry(Identifier id, String group, RecipeUtil.CompatibilityCraftingRecipeCategory category, T recipe) {
        this.entry = new RecipeHolder<>(ResourceKey.create(Registries.RECIPE, id), recipe);
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
    public RecipeHolder<?> getRecipeEntry() {
        return entry;
    }

    public T getRecipe() {
        return entry.value();
    }

    public Identifier getId() {
        return entry.id().identifier();
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
