package net.pitan76.mcpitanlib.api.event;

import net.pitan76.mcpitanlib.api.command.argument.ItemCommand;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.item.Item;

public class ItemCommandEvent extends RequiredCommandEvent {
    public Item getValue() {
        return ItemStackArgumentType.getItemStackArgument(context, ((ItemCommand) getCommand()).getArgumentName()).getItem();
    }
}
