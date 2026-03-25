package net.pitan76.mcpitanlib.api.command.argument;

import net.minecraft.block.Block;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.api.event.BlockCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public abstract class BlockCommand extends RequiredCommand<Block> {
    @Override
    public BlockStateArgumentType getArgumentType() {
        return BlockStateArgumentType.blockState(CommandRegistry.latestCommandRegistryAccess);
    }

    public abstract void execute(BlockCommandEvent event);

    @Override
    public void execute(ServerCommandEvent event) {
        execute((BlockCommandEvent) event);
    }
}
