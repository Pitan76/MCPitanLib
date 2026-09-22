package net.pitan76.mcpitanlib.mixin;

import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryLoadTask;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.pitan76.mcpitanlib.core.registry.DynamicRecipeRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

/**
 * レジストリが凍結される直前に、コードから登録されたレシピを流し込む。
 */
@Mixin(RegistryLoadTask.class)
public abstract class RegistryLoadTaskMixin<T> {

    @Shadow
    @Final
    private WritableRegistry<T> registry;

    @Shadow
    protected abstract ResourceKey<? extends Registry<T>> registryKey();

    @SuppressWarnings("unchecked")
    @Inject(method = "freezeRegistry", at = @At("HEAD"))
    private void mcpitanlib$injectDynamicRecipes(Map<ResourceKey<?>, Exception> errors, CallbackInfoReturnable<Boolean> cir) {
        if (!registryKey().equals(Registries.RECIPE)) return;

        DynamicRecipeRegistry.inject((WritableRegistry<Recipe<?>>) registry);
    }
}
