package net.pitan76.mcpitanlib.api.command.argument;

import com.mojang.brigadier.arguments.StringArgumentType;

public abstract class GreedyStringCommand extends StringCommand {
    @Override
    public StringArgumentType getArgumentType() {
        return StringArgumentType.greedyString();
    }
}
