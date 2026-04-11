package net.pitan76.mcpitanlib.api.command.argument;

import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.pitan76.mcpitanlib.api.event.PlayerCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class PlayerCommand extends RequiredCommand<Entity> {
    @Override
    public EntityArgument getArgumentType() {
        return EntityArgument.player();
    }

    public abstract void execute(PlayerCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((PlayerCommandEvent) event);
    }
}
