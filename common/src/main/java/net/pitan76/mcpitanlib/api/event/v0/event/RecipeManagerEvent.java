package net.pitan76.mcpitanlib.api.event.v0.event;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonElement;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.pitan76.mcpitanlib.api.recipe.CompatibleRecipeEntry;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;

import java.util.Map;

public class RecipeManagerEvent {
    public Map<Identifier, JsonElement> jsonMap;
    public ResourceManager resourceManager;
    public Profiler profiler;

    @Deprecated
    public ImmutableMap.Builder<Identifier, RecipeEntry<?>> recipesById;

    @Deprecated
    public ImmutableMultimap.Builder<RecipeType<?>, RecipeEntry<?>> recipesByType;

    public RecipeManagerEvent(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, ImmutableMap.Builder<Identifier, RecipeEntry<?>> recipesById, ImmutableMultimap.Builder<RecipeType<?>, RecipeEntry<?>> recipesByType) {
        this.jsonMap = map;
        this.resourceManager = resourceManager;
        this.profiler = profiler;
        this.recipesById = recipesById;
        this.recipesByType = recipesByType;
    }

    public Map<Identifier, JsonElement> getJsonMap() {
        return jsonMap;
    }

    @Deprecated
    public ImmutableMap.Builder<Identifier, RecipeEntry<?>> getRecipesById() {
        return recipesById;
    }

    @Deprecated
    public ImmutableMultimap.Builder<RecipeType<?>, RecipeEntry<?>> getRecipesByType() {
        return recipesByType;
    }

    public Profiler getProfiler() {
        return profiler;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public void putCompatibleRecipeEntry(Identifier id, CompatibleRecipeEntry entry) {
        putCompatibleRecipeEntry(entry);
    }

    public void putCompatibleRecipeEntry(CompatibleRecipeEntry entry) {
        recipesById.put(entry.getId(), entry.getRecipeEntry());
        recipesByType.put(entry.getType(), entry.getRecipeEntry());
    }

    public <T extends Recipe<?>> void putCompatibleRecipeEntry(CompatRecipeEntry<T> entry) {
        recipesById.put(entry.getId(), entry.getRecipeEntry());
        recipesByType.put(entry.getType(), entry.getRecipeEntry());
    }
}
