package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.StringRange;
import net.pitan76.mcpitanlib.api.command.AbstractCommand;

public class CommandEvent<T> {
    public CommandContext<T> context;
    public AbstractCommand<?> command;

    public CommandContext<T> getContext() {
        return context;
    }

    public void setContext(CommandContext<T> context) {
        this.context = context;
    }

    public String getInput() {
        return context.getInput();
    }

    public StringRange getRange() {
        return context.getRange();
    }

    public void setCommand(AbstractCommand<?> command) {
        this.command = command;
    }

    public AbstractCommand<?> getCommand() {
        return command;
    }
}
