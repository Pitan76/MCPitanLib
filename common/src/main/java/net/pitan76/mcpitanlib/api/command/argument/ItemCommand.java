package net.pitan76.mcpitanlib.api.command.argument;

import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.api.event.ItemCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class ItemCommand extends RequiredCommand<Item> {
    @Override
    public ItemStackArgumentType getArgumentType() {
        return ItemStackArgumentType.itemStack(CommandRegistry.latestCommandRegistryAccess);
    }

    public abstract void execute(ItemCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((ItemCommandEvent) event);
    }
}
