package net.pitan76.mcpitanlib.api.event;

import net.minecraft.world.level.block.Block;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.pitan76.mcpitanlib.api.command.argument.BlockCommand;

public class BlockCommandEvent extends RequiredCommandEvent {
    public Block getValue() {
        return BlockStateArgument.getBlock(context, ((BlockCommand) getCommand()).getArgumentName()).getState().getBlock();
    }
}
