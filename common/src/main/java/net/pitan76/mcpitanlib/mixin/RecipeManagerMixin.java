package net.pitan76.mcpitanlib.mixin;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
import net.pitan76.mcpitanlib.api.event.v0.event.RecipeManagerEvent;
import net.pitan76.mcpitanlib.api.event.v1.RecipeManagerRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Shadow
    private RecipeMap recipes;

    @Unique
    private ResourceManager mcpitanlib$resourceManager;

    @Unique
    private ProfilerFiller mcpitanlib$profiler;

    @Inject(method = "apply(Lnet/minecraft/world/item/crafting/RecipeMap;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At("HEAD"))
    private void mcpitanlib$captureReloadContext(RecipeMap recipeMap, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci) {
        mcpitanlib$resourceManager = resourceManager;
        mcpitanlib$profiler = profiler;
    }

    @Inject(method = "finalizeRecipeLoading", at = @At("HEAD"))
    private void mcpitanlib$invokeApply(FeatureFlagSet featureFlags, CallbackInfo ci) {
        if (RecipeManagerRegistry.managers.isEmpty()) return;

        SortedMap<Identifier, Recipe<?>> sortedMap = new TreeMap<>();
        for (RecipeHolder<?> holder : recipes.values())
            sortedMap.put(holder.id().identifier(), holder.value());

        RecipeManagerEvent event = new RecipeManagerEvent(sortedMap, mcpitanlib$resourceManager, mcpitanlib$profiler);
        RecipeManagerRegistry.managers.forEach((manager) -> manager.apply(event));

        List<RecipeHolder<?>> holders = new ArrayList<>(sortedMap.size());
        sortedMap.forEach((id, recipe) -> holders.add(new RecipeHolder<>(ResourceKey.create(Registries.RECIPE, id), recipe)));

        recipes = RecipeMap.create(holders);
    }
}
