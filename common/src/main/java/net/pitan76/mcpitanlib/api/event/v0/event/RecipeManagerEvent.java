package net.pitan76.mcpitanlib.api.event.v0.event;

import com.google.common.collect.Multimap;
import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.pitan76.mcpitanlib.api.recipe.CompatibleRecipeEntry;

import java.util.Map;

public class RecipeManagerEvent {
    public Map<Identifier, JsonElement> jsonMap;
    public ResourceManager resourceManager;
    public Profiler profiler;

    @Deprecated
    public Map<Identifier, RecipeEntry<?>> recipesById;

    @Deprecated
    public Multimap<RecipeType<?>, RecipeEntry<?>> recipesByType;

    public RecipeManagerEvent(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, Map<Identifier, RecipeEntry<?>> recipesById, Multimap<RecipeType<?>, RecipeEntry<?>> recipesByType) {
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
    public Map<Identifier, RecipeEntry<?>> getRecipesById() {
        return recipesById;
    }

    @Deprecated
    public Multimap<RecipeType<?>, RecipeEntry<?>> getRecipesByType() {
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
}
