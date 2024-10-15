package net.pitan76.mcpitanlib.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.recipe.*;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.pitan76.mcpitanlib.api.event.v0.event.RecipeManagerEvent;
import net.pitan76.mcpitanlib.api.event.v1.RecipeManagerRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.SortedMap;

@Mixin(ServerRecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Lnet/minecraft/recipe/PreparedRecipes;",
            at = @At(value = "TAIL"))
    private void mcpitanlib$invokeApply(ResourceManager resourceManager, Profiler profiler, CallbackInfoReturnable<PreparedRecipes> cir, @Local SortedMap<Identifier, Recipe<?>> sortedMap) {
        if (RecipeManagerRegistry.managers.isEmpty()) return;
        RecipeManagerRegistry.managers.forEach((manager) -> manager.apply(new RecipeManagerEvent(sortedMap, resourceManager, profiler)));
    }

}
