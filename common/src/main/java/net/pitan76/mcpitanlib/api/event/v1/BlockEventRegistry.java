package net.pitan76.mcpitanlib.api.event.v1;

import net.pitan76.mcpitanlib.api.event.v1.listener.BlockBreakTask;
import net.pitan76.mcpitanlib.api.event.v1.listener.BlockPlacedTask;

@Deprecated
public class BlockEventRegistry {
    @Deprecated
    public static void register(BlockPlacedTask listener) {
        net.pitan76.mcpitanlib.api.event.v2.BlockEventRegistry.ON_PLACED.register(listener);
    }

    @Deprecated
    public static void register(BlockBreakTask listener) {
        net.pitan76.mcpitanlib.api.event.v2.BlockEventRegistry.ON_BREAK.register(listener);
    }

    public static void registerPlacedListener(BlockPlacedTask listener) {
        net.pitan76.mcpitanlib.api.event.v2.BlockEventRegistry.ON_PLACED.register(listener);

    }

    public static void registerBreakListener(BlockBreakTask listener) {
        net.pitan76.mcpitanlib.api.event.v2.BlockEventRegistry.ON_BREAK.register(listener);
    }
}
