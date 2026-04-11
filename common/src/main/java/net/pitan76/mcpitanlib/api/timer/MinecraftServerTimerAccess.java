package net.pitan76.mcpitanlib.api.timer;

import java.util.function.Supplier;

public interface MinecraftServerTimerAccess {
    void mcpitanlib$addTimer(long ticksUntilSomething, Supplier<Boolean> supplier);
}
