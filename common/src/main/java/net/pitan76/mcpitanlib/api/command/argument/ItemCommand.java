package net.pitan76.mcpitanlib.api.command.argument;

import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.entity.Entity;
import net.pitan76.mcpitanlib.api.event.ItemCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class ItemCommand extends RequiredCommand<Entity> {
    @Override
    public ItemStackArgumentType getArgumentType() {
        return ItemStackArgumentType.itemStack();
    }

    public abstract void execute(ItemCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((ItemCommandEvent) event);
    }
}
