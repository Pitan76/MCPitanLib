package net.pitan76.mcpitanlib.api.command.argument;

import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.pitan76.mcpitanlib.api.event.EntityCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class EntityCommand extends RequiredCommand<Entity> {
    @Override
    public EntityArgument getArgumentType() {
        return EntityArgument.entity();
    }

    public abstract void execute(EntityCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((EntityCommandEvent) event);
    }
}
