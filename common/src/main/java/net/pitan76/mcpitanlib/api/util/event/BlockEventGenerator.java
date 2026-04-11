package net.pitan76.mcpitanlib.api.util.event;

import net.minecraft.world.level.block.Block;
import net.pitan76.mcpitanlib.api.event.block.BlockBreakEvent;
import net.pitan76.mcpitanlib.api.event.block.BlockPlacedEvent;
import net.pitan76.mcpitanlib.api.event.block.ItemScattererUtil;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;

public class BlockEventGenerator {
    public static void onPlaced(Block block, BlockPlacedEvent e) {
        block.setPlacedBy(e.world, e.pos, e.state, e.placer, e.stack);
    }

    public static void onBreak(Block block, BlockBreakEvent e) {
        block.playerWillDestroy(e.world, e.pos, e.state, e.getPlayerEntity());
    }

    public static void onStateReplaced(Block block, StateReplacedEvent e) {
        ItemScattererUtil.onStateReplaced(e);
    }
}
