package net.pitan76.mcpitanlib.api.command.argument;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.pitan76.mcpitanlib.api.event.IntegerCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class IntegerCommand extends RequiredCommand<Integer> {
    @Override
    public IntegerArgumentType getArgumentType() {
        return IntegerArgumentType.integer();
    }

    public abstract void execute(IntegerCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((IntegerCommandEvent) event);
    }
}
