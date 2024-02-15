package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.pitan76.mcpitanlib.api.command.argument.PlayersCommand;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;

public class PlayersCommandEvent extends RequiredCommandEvent {
    public Entity getValue() {
        try {
            return EntityArgumentType.getPlayer(context, ((PlayersCommand) getCommand()).getArgumentName());
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
