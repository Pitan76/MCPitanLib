package net.pitan76.mcpitanlib.api.command.argument;

import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.world.item.Item;
import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.api.event.ItemCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class ItemCommand extends RequiredCommand<Item> {
    @Override
    public ItemArgument getArgumentType() {
        return ItemArgument.item(CommandRegistry.latestCommandRegistryAccess);
    }

    public abstract void execute(ItemCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((ItemCommandEvent) event);
    }
}
