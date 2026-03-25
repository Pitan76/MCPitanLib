package net.pitan76.mcpitanlib.api.command.argument;

import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.pitan76.mcpitanlib.api.event.PlayersCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class PlayersCommand extends RequiredCommand<Entity> {
    @Override
    public EntityArgument getArgumentType() {
        return EntityArgument.players();
    }

    public abstract void execute(PlayersCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((PlayersCommandEvent) event);
    }
}
