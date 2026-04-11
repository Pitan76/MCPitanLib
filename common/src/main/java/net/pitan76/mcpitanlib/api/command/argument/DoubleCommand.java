package net.pitan76.mcpitanlib.api.command.argument;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.pitan76.mcpitanlib.api.event.DoubleCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class DoubleCommand extends RequiredCommand<Double> {
    @Override
    public DoubleArgumentType getArgumentType() {
        return DoubleArgumentType.doubleArg();
    }

    public abstract void execute(DoubleCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((DoubleCommandEvent) event);
    }
}
