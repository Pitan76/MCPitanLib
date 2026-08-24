package net.pitan76.mcpitanlib.api.event.v2.listener;

import net.pitan76.mcpitanlib.api.event.entity.PlayerTickEvent;

@FunctionalInterface
public interface PlayerTickTask {
    void playerTick(PlayerTickEvent event);
}
