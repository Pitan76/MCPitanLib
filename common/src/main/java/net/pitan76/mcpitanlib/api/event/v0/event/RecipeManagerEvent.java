package net.pitan76.mcpitanlib.api.event.v0.event;

import com.google.common.collect.ImmutableMap;
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
    public Map<RecipeType<?>, ImmutableMap.Builder<Identifier, RecipeEntry<?>>> map;

    public RecipeManagerEvent(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, Map<RecipeType<?>, ImmutableMap.Builder<Identifier, RecipeEntry<?>>> map2) {
        this.jsonMap = map;
        this.resourceManager = resourceManager;
        this.profiler = profiler;
        this.map = map2;
    }

    public Map<Identifier, JsonElement> getJsonMap() {
        return jsonMap;
    }

    @Deprecated
    public Map<RecipeType<?>, ImmutableMap.Builder<Identifier, RecipeEntry<?>>> getMap() {
        return map;
    }

    public Profiler getProfiler() {
        return profiler;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public void putCompatibleRecipeEntry(Identifier id, CompatibleRecipeEntry entry) {
        map.get(entry.getType()).put(entry.getId(), entry.getRecipeEntry());
    }

    public void putCompatibleRecipeEntry(CompatibleRecipeEntry entry) {
        map.get(entry.getType()).put(entry.getId(), entry.getRecipeEntry());
    }

    public <T extends Recipe<?>> void putCompatibleRecipeEntry(CompatRecipeEntry<T> entry) {
        map.get(entry.getType()).put(entry.getId(), entry.getRecipeEntry());
    }
}
