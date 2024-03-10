package net.pitan76.mcpitanlib.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import net.pitan76.mcpitanlib.api.event.v0.event.ItemStackActionEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;Ljava/lang/Runnable;)V", at = @At("HEAD"), cancellable = true)
    public void mcpitanlib$damage(int amount, Random random, ServerPlayerEntity player, Runnable breakCallback, CallbackInfo ci) {
        ItemStackActionEvent.returnValue = null;
        ItemStackActionEvent.call((ItemStack) (Object) this);
        if (ItemStackActionEvent.returnValue != null)
            ci.cancel();
    }
}
