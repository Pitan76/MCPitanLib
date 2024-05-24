package net.pitan76.mcpitanlib.api.event.v0.event;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeEntry;
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
    public ImmutableMap.Builder<Identifier, RecipeEntry<?>> builder;

    public RecipeManagerEvent(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, ImmutableMap.Builder<Identifier, RecipeEntry<?>> builder) {
        this.jsonMap = map;
        this.resourceManager = resourceManager;
        this.profiler = profiler;
        this.builder = builder;
    }

    public Map<Identifier, JsonElement> getJsonMap() {
        return jsonMap;
    }

    /*
    @Deprecated
    public Map<RecipeType<?>, ImmutableMap.Builder<Identifier, RecipeEntry<?>>> getMap() {
        return map;
    }

     */

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
        builder.put(entry.getId(), entry.getRecipeEntry());
    }
}
