package net.pitan76.mcpitanlib.api.command.argument;

import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.pitan76.mcpitanlib.api.event.EntitiesCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class EntitiesCommand extends RequiredCommand<Entity> {
    @Override
    public EntityArgument getArgumentType() {
        return EntityArgument.entities();
    }

    public abstract void execute(EntitiesCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((EntitiesCommandEvent) event);
    }
}
