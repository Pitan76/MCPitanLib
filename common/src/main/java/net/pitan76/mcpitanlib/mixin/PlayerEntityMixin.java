package net.pitan76.mcpitanlib.mixin;

import net.minecraft.world.entity.player.Player;
import net.pitan76.mcpitanlib.api.event.entity.PlayerTickEvent;
import net.pitan76.mcpitanlib.api.event.v2.EntityEventRegistry;
import net.pitan76.mcpitanlib.api.event.v2.listener.PlayerTickTask;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerEntityMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void mcpitanlib$tick(CallbackInfo ci) {
        if (EntityEventRegistry.PLAYER_TICK.isEmpty()) return;

        PlayerTickEvent event = new PlayerTickEvent((Player) (Object) this);
        int maxPriority = EntityEventRegistry.PLAYER_TICK.getMaxPriority();
        for (int p = maxPriority; p >= 0; p--) {
            for (PlayerTickTask listener : EntityEventRegistry.PLAYER_TICK.getListenersAsList(p)) {
                listener.playerTick(event);
            }
        }
    }
}
