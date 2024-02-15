package net.pitan76.mcpitanlib.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import net.pitan76.mcpitanlib.api.event.v0.event.ItemStackActionEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z", at = @At("HEAD"), cancellable = true)
    public void damage(int amount, Random random, ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        ItemStackActionEvent.returnValue = null;
        ItemStackActionEvent.call((ItemStack) (Object) this);
        if (ItemStackActionEvent.returnValue != null)
            cir.setReturnValue(ItemStackActionEvent.returnValue);
    }
}
