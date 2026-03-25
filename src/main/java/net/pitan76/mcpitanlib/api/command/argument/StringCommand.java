package net.pitan76.mcpitanlib.api.command.argument;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.mcpitanlib.api.event.StringCommandEvent;

public abstract class StringCommand extends RequiredCommand<String> {
    @Override
    public StringArgumentType getArgumentType() {
        return StringArgumentType.string();
    }

    public abstract void execute(StringCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((StringCommandEvent) event);
    }
}
