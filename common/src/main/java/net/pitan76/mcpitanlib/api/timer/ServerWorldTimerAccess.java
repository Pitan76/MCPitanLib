package net.pitan76.mcpitanlib.api.timer;

import java.util.function.Supplier;

public interface ServerWorldTimerAccess {
    void addTimer(long ticksUntilSomething, Supplier<Boolean> supplier);
}