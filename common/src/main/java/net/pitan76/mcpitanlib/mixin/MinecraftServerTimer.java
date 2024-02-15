package net.pitan76.mcpitanlib.mixin;

import net.pitan76.mcpitanlib.api.timer.MinecraftServerTimerAccess;
import net.pitan76.mcpitanlib.api.timer.TimerItem;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mixin(MinecraftServer.class)
public class MinecraftServerTimer implements MinecraftServerTimerAccess {
    @Unique
    private final List<TimerItem> timerItems = new ArrayList<>();

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        if (timerItems.isEmpty()) return;
        List<TimerItem> items = new ArrayList<>(timerItems);

        for (TimerItem item : items) {
            if (--item.ticksUntilSomething == 0L) {
                if (item.executeSupplier.get())
                    timerItems.remove(item);
            }
        }
    }

    @Override
    public void addTimer(long ticksUntilSomething, Supplier<Boolean> executeSupplier) {
        timerItems.add(new TimerItem(ticksUntilSomething, executeSupplier));
    }
}
