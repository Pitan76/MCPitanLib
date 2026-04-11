package net.pitan76.mcpitanlib.api.command.argument;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.pitan76.mcpitanlib.api.event.FloatCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class FloatCommand extends RequiredCommand<Float> {
    @Override
    public FloatArgumentType getArgumentType() {
        return FloatArgumentType.floatArg();
    }

    public abstract void execute(FloatCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((FloatCommandEvent) event);
    }
}
