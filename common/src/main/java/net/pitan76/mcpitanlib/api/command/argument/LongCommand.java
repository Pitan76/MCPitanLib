package net.pitan76.mcpitanlib.api.command.argument;

import com.mojang.brigadier.arguments.LongArgumentType;
import net.pitan76.mcpitanlib.api.event.LongCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class LongCommand extends RequiredCommand<Integer> {
    @Override
    public LongArgumentType getArgumentType() {
        return LongArgumentType.longArg();
    }

    public abstract void execute(LongCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((LongCommandEvent) event);
    }
}
