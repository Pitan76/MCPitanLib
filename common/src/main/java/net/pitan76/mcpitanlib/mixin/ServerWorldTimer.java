package net.pitan76.mcpitanlib.mixin;

import net.minecraft.server.world.ServerWorld;
import net.pitan76.mcpitanlib.api.timer.ServerWorldTimerAccess;
import net.pitan76.mcpitanlib.api.timer.TimerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mixin(ServerWorld.class)
public class ServerWorldTimer implements ServerWorldTimerAccess {
    @Unique
    private final List<TimerItem> mcpitanlib$timerItems = new ArrayList<>();

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        if (mcpitanlib$timerItems.isEmpty()) return;
        List<TimerItem> items = new ArrayList<>(mcpitanlib$timerItems);

        for (TimerItem item : items) {
            if (--item.ticksUntilSomething == 0L) {
                if (item.executeSupplier.get())
                    mcpitanlib$timerItems.remove(item);
            }
        }
    }

    @Override
    public void mcpitanlib$addTimer(long ticksUntilSomething, Supplier<Boolean> executeSupplier) {
        mcpitanlib$timerItems.add(new TimerItem(ticksUntilSomething, executeSupplier));
    }
}
