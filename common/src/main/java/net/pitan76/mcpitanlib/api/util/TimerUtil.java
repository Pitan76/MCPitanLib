package net.pitan76.mcpitanlib.api.util;

import net.pitan76.mcpitanlib.api.timer.MinecraftServerTimerAccess;
import net.pitan76.mcpitanlib.api.timer.ServerWorldTimerAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

import java.util.function.Supplier;

public class TimerUtil {
    public static void addTimer(ServerWorld world, long ticksUntilSomething, Supplier<Boolean> supplier) {
        ((ServerWorldTimerAccess) world).addTimer(ticksUntilSomething, supplier);
    }

    public static void addTimer(MinecraftServer server, long ticksUntilSomething, Supplier<Boolean> supplier) {
        ((MinecraftServerTimerAccess) server).addTimer(ticksUntilSomething, supplier);
    }
}
