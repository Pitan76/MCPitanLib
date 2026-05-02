package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.pitan76.mcpitanlib.api.command.argument.PlayerCommand;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public class PlayerCommandEvent extends RequiredCommandEvent {
    public Entity getValue() {
        try {
            return EntityArgument.getPlayer(context, ((PlayerCommand) getCommand()).getArgumentName());
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public EntityWrapper getWrapper() {
        return EntityWrapper.of(getValue());
    }

    public Player getValueAsPlayer() {
        return getWrapper().toPlayer().orElse(null);
    }
}
