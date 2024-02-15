package net.pitan76.mcpitanlib.api.command.argument;

import net.pitan76.mcpitanlib.api.event.EntityCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;

public abstract class EntityCommand extends RequiredCommand<Entity> {
    @Override
    public EntityArgumentType getArgumentType() {
        return EntityArgumentType.entity();
    }

    public abstract void execute(EntityCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((EntityCommandEvent) event);
    }
}
