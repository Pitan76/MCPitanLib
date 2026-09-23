package net.pitan76.mcpitanlib.forge.mixin;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.pitan76.mcpitanlib.api.client.model.forge.CompatArmorModelRegistryImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ForgeHooksClient.class, remap = false)
public class ForgeHooksClientMixin {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Inject(method = "getArmorModel", at = @At("RETURN"), cancellable = true)
    private static void mcpitanlib$getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, BipedEntityModel original, CallbackInfoReturnable cir) {
        BipedEntityModel<?> model = CompatArmorModelRegistryImpl.getArmorModel(stack, slot, original);
        if (model != null) cir.setReturnValue(model);
    }

    @Inject(method = "getArmorTexture", at = @At("RETURN"), cancellable = true)
    private static void mcpitanlib$getArmorTexture(Entity entity, ItemStack stack, String _default, EquipmentSlot slot, String type, CallbackInfoReturnable<String> cir) {
        String texture = CompatArmorModelRegistryImpl.getArmorTexture(stack);
        if (texture != null) cir.setReturnValue(texture);
    }
}
