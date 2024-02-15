package net.pitan76.mcpitanlib.api.timer;

import java.util.function.Supplier;

public class TimerItem {
    public long ticksUntilSomething;

    public Supplier<Boolean> executeSupplier;

    public TimerItem(long ticksUntilSomething, Supplier<Boolean> executeSupplier) {
        this.ticksUntilSomething = ticksUntilSomething;
        this.executeSupplier = executeSupplier;
    }
}
