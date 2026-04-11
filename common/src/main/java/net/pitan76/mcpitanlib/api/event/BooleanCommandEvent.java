package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.pitan76.mcpitanlib.api.command.argument.BooleanCommand;

public class BooleanCommandEvent extends RequiredCommandEvent {
    public Boolean getValue() {
        return BoolArgumentType.getBool(context, ((BooleanCommand) getCommand()).getArgumentName());
    }
}
