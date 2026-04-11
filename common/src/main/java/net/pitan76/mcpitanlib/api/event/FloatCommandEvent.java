package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.pitan76.mcpitanlib.api.command.argument.FloatCommand;

public class FloatCommandEvent extends RequiredCommandEvent {
    public Float getValue() {
        return FloatArgumentType.getFloat(context, ((FloatCommand) getCommand()).getArgumentName());
    }
}
