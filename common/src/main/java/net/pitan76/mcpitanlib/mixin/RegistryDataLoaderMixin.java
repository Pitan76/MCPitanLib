package net.pitan76.mcpitanlib.mixin;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.server.packs.resources.ResourceManager;
import net.pitan76.mcpitanlib.core.registry.DynamicRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * レジストリ読み込みに使われた {@link ResourceManager} を拾っておく。
 * {@code RecipeManagerEvent} が公開している getResourceManager() を維持するため。
 */
@Mixin(RegistryDataLoader.class)
public class RegistryDataLoaderMixin {

    @Inject(method = "load(Lnet/minecraft/server/packs/resources/ResourceManager;Ljava/util/List;Ljava/util/List;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;",
            at = @At("HEAD"))
    private static void mcpitanlib$captureResourceManager(ResourceManager resourceManager, List<HolderLookup.RegistryLookup<?>> lookups, List<RegistryDataLoader.RegistryData<?>> registries, Executor executor, CallbackInfoReturnable<CompletableFuture<RegistryAccess.Frozen>> cir) {
        DynamicRecipeRegistry.setResourceManager(resourceManager);
    }
}
