package net.pitan76.mcpitanlib.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.pitan76.mcpitanlib.api.event.v0.event.ItemStackActionEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V", at = @At("HEAD"), cancellable = true)
    public void mcpitanlib$damage(int amount, ServerLevel world, ServerPlayer player, Consumer<Item> breakCallback, CallbackInfo ci) {
        ItemStackActionEvent.returnValue = null;
        ItemStackActionEvent.call((ItemStack) (Object) this);
        if (ItemStackActionEvent.returnValue != null)
            ci.cancel();
    }
}
