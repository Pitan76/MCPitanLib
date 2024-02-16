package net.pitan76.mcpitanlib.api.event.v1;

import net.pitan76.mcpitanlib.api.event.v1.listener.BlockBreakTask;
import net.pitan76.mcpitanlib.api.event.v1.listener.BlockPlacedTask;

import java.util.ArrayList;
import java.util.List;

public class BlockEventRegistry {
    public static List<BlockPlacedTask> onPlacedListeners = new ArrayList<>();
    public static List<BlockBreakTask> onBreakListeners = new ArrayList<>();

    public static void register(BlockPlacedTask listener) {
        if (onPlacedListeners.contains(listener)) return;
        onPlacedListeners.add(listener);
    }

    public static void register(BlockBreakTask listener) {
        if (onBreakListeners.contains(listener)) return;
        onBreakListeners.add(listener);
    }
}
