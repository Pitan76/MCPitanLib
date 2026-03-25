package net.pitan76.mcpitanlib.api.event.v0.event;

import net.minecraft.recipe.Recipe;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resources.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.pitan76.mcpitanlib.api.recipe.CompatibleRecipeEntry;
import net.pitan76.mcpitanlib.api.recipe.v2.CompatRecipeEntry;
import net.pitan76.mcpitanlib.api.recipe.v3.CompatRecipe;
import net.pitan76.mcpitanlib.midohra.recipe.entry.RecipeEntry;

import java.util.SortedMap;

public class RecipeManagerEvent {

    @Deprecated
    private SortedMap<Identifier, Recipe<?>> sortedMap;

    public ResourceManager resourceManager;
    public Profiler profiler;

    public RecipeManagerEvent(SortedMap<Identifier, Recipe<?>> sortedMap, ResourceManager resourceManager, Profiler profiler) {
        this.sortedMap = sortedMap;
        this.resourceManager = resourceManager;
        this.profiler = profiler;
    }

    public Profiler getProfiler() {
        return profiler;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public net.pitan76.mcpitanlib.midohra.resource.ResourceManager getResourceManagerM() {
        return net.pitan76.mcpitanlib.midohra.resource.ResourceManager.of(resourceManager);
    }

    public void putCompatibleRecipeEntry(Identifier id, CompatibleRecipeEntry entry) {
        putCompatibleRecipeEntry(entry);
    }

    public void putCompatibleRecipeEntry(CompatibleRecipeEntry entry) {
        sortedMap.put(entry.getId(), entry.getRecipe());
    }

    public <T extends Recipe<?>> void putCompatibleRecipeEntry(CompatRecipeEntry<T> entry) {
        sortedMap.put(entry.getId(), entry.getRecipe());
    }

    public void putRecipeEntry(RecipeEntry entry) {
        sortedMap.put(entry.getId().toMinecraft(), entry.getRawRecipe());
    }

    public void putRecipe(CompatRecipe recipe) {
        sortedMap.put(recipe.getId(), recipe.getRecipe());
    }
}
