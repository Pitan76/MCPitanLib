package net.pitan76.mcpitanlib.api.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import net.pitan76.mcpitanlib.api.command.AbstractCommand;

public abstract class RequiredCommand<T extends Object> extends AbstractCommand<T> {
    public abstract String getArgumentName();

    public abstract ArgumentType getArgumentType();
}
