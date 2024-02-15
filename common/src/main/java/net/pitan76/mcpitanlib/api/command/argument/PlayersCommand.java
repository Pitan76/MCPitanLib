package net.pitan76.mcpitanlib.api.command.argument;

import net.pitan76.mcpitanlib.api.event.PlayersCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;

public abstract class PlayersCommand extends RequiredCommand<Entity> {
    @Override
    public EntityArgumentType getArgumentType() {
        return EntityArgumentType.players();
    }

    public abstract void execute(PlayersCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((PlayersCommandEvent) event);
    }
}
