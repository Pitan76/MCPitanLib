package net.pitan76.mcpitanlib.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.pitan76.mcpitanlib.api.event.v0.event.ItemStackActionEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "damage(ILnet/minecraft/server/world/ServerWorld;Lnet/minecraft/server/network/ServerPlayerEntity;Ljava/util/function/Consumer;)V", at = @At("HEAD"), cancellable = true)
    public void mcpitanlib$damage(int amount, ServerWorld world, ServerPlayerEntity player, Consumer<Item> breakCallback, CallbackInfo ci) {
        ItemStackActionEvent.returnValue = null;
        ItemStackActionEvent.call((ItemStack) (Object) this);
        if (ItemStackActionEvent.returnValue != null)
            ci.cancel();
    }
}
