package net.pitan76.mcpitanlib.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.pitan76.mcpitanlib.api.event.v0.event.RecipeManagerEvent;
import net.pitan76.mcpitanlib.api.event.v1.RecipeManagerRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(value = "TAIL"))
    private void mcpitanlib$invokeApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci, ImmutableMultimap.Builder<RecipeType<?>, RecipeEntry<?>> builder, ImmutableMap.Builder<Identifier, RecipeEntry<?>> builder2) {
        if (RecipeManagerRegistry.managers.isEmpty()) return;
        RecipeManagerRegistry.managers.forEach((manager) -> manager.apply(new RecipeManagerEvent(map, resourceManager, profiler, builder2, builder)));
    }

}
