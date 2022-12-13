package ml.pkom.mcpitanlibarch.api.command.argument;

import ml.pkom.mcpitanlibarch.api.event.EntitiesCommandEvent;
import ml.pkom.mcpitanlibarch.api.event.ServerCommandEvent;
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
