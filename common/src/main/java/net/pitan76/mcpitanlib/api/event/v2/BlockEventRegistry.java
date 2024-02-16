package net.pitan76.mcpitanlib.api.event.v2;

import net.pitan76.mcpitanlib.api.event.v1.listener.BlockBreakTask;
import net.pitan76.mcpitanlib.api.event.v1.listener.BlockPlacedTask;

public class BlockEventRegistry {
    /**
     * Event that is called when a block is placed
     * <pre>{@code
     * BlockEventRegistry.ON_PLACED.register((event) -> {
     *     // Do something
     * }</pre>
     */
    public static OrderedEvent<BlockPlacedTask> ON_PLACED = new OrderedEvent<>();

    /**
     * Event that is called when a block is broken
     * <pre>{@code
     * BlockEventRegistry.ON_BREAK.register((event) -> {
     *     // Do something
     *     return new BlockBreakResult(event.state);
     * }</pre>
     */
    public static OrderedEvent<BlockBreakTask> ON_BREAK = new OrderedEvent<>();
}
