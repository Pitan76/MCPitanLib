package net.pitan76.mcpitanlib.api.event;

import net.minecraft.block.Block;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.pitan76.mcpitanlib.api.command.argument.BlockCommand;

public class BlockCommandEvent extends RequiredCommandEvent {
    public Block getValue() {
        return BlockStateArgumentType.getBlockState(context, ((BlockCommand) getCommand()).getArgumentName()).getBlockState().getBlock();
    }
}
