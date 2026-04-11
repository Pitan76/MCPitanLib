package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.pitan76.mcpitanlib.api.command.argument.DoubleCommand;

public class DoubleCommandEvent extends RequiredCommandEvent {
    public Double getValue() {
        return DoubleArgumentType.getDouble(context, ((DoubleCommand) getCommand()).getArgumentName());
    }
}
