package net.pitan76.mcpitanlib.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.recipe.*;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.resources.Identifier;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
import net.pitan76.mcpitanlib.api.event.v0.event.RecipeManagerEvent;
import net.pitan76.mcpitanlib.api.event.v1.RecipeManagerRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.SortedMap;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/world/item/crafting/RecipeMap;",
            at = @At(value = "TAIL"))
    private void mcpitanlib$invokeApply(ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfoReturnable<RecipeMap> cir, @Local SortedMap<Identifier, Recipe<?>> sortedMap) {
        if (RecipeManagerRegistry.managers.isEmpty()) return;
        RecipeManagerRegistry.managers.forEach((manager) -> manager.apply(new RecipeManagerEvent(sortedMap, resourceManager, profiler)));
    }

}
