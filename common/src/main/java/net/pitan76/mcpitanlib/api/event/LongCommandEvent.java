package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.arguments.LongArgumentType;
import net.pitan76.mcpitanlib.api.command.argument.LongCommand;

public class LongCommandEvent extends RequiredCommandEvent {
    public Long getValue() {
        return LongArgumentType.getLong(context, ((LongCommand) getCommand()).getArgumentName());
    }
}
