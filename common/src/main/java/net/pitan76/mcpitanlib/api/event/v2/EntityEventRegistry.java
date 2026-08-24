package net.pitan76.mcpitanlib.api.event.v2;

import net.pitan76.mcpitanlib.api.event.v2.listener.PlayerTickTask;

public class EntityEventRegistry {
    public static OrderedEvent<PlayerTickTask> PLAYER_TICK = new OrderedEvent<>();
}
