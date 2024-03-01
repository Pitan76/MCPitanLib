package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.pitan76.mcpitanlib.api.command.argument.FloatCommand;

public class DoubleCommandEvent extends RequiredCommandEvent {
    public Double getValue() {
        return DoubleArgumentType.getDouble(context, ((FloatCommand) getCommand()).getArgumentName());
    }
}
