package net.pitan76.mcpitanlib.fabric.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.pitan76.mcpitanlib.fabric.event.ClientPreAttackCallbacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Inject(method = "doAttack", at = @At("HEAD"))
    private void mcpitanlib$onDoAttack(CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        if (client.player == null) return;

        ClientPreAttackCallbacks.preAttack(client.player);
    }
}
