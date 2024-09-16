package net.pitan76.mcpitanlib.api.command.argument;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.pitan76.mcpitanlib.api.event.BooleanCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class BooleanCommand extends RequiredCommand<Boolean> {
    @Override
    public BoolArgumentType getArgumentType() {
        return BoolArgumentType.bool();
    }

    public abstract void execute(BooleanCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((BooleanCommandEvent) event);
    }
}
