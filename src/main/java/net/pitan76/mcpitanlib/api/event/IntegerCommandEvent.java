package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.pitan76.mcpitanlib.api.command.argument.IntegerCommand;

public class IntegerCommandEvent extends RequiredCommandEvent {
    public Integer getValue() {
        return IntegerArgumentType.getInteger(context, ((IntegerCommand) getCommand()).getArgumentName());
    }
}
