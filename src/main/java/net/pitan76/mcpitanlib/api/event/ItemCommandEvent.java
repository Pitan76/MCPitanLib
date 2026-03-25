package net.pitan76.mcpitanlib.api.event;

import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.world.item.Item;
import net.pitan76.mcpitanlib.api.command.argument.ItemCommand;

public class ItemCommandEvent extends RequiredCommandEvent {
    public Item getValue() {
        return ItemArgument.getItem(context, ((ItemCommand) getCommand()).getArgumentName()).item().value();
    }
}
