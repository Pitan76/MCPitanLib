package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.pitan76.mcpitanlib.api.command.argument.StringCommand;

public class StringCommandEvent extends RequiredCommandEvent {
    public String getValue() {
        return StringArgumentType.getString(context, ((StringCommand) getCommand()).getArgumentName());
    }
}
