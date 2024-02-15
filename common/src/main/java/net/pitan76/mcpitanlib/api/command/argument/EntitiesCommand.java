package net.pitan76.mcpitanlib.api.command.argument;

import net.pitan76.mcpitanlib.api.event.EntitiesCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;

public abstract class EntitiesCommand extends RequiredCommand<Entity> {
    @Override
    public EntityArgumentType getArgumentType() {
        return EntityArgumentType.entities();
    }

    public abstract void execute(EntitiesCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((EntitiesCommandEvent) event);
    }
}
