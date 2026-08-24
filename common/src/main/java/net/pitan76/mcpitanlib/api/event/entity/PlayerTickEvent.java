package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class PlayerTickEvent extends BaseEvent {
    public net.minecraft.world.entity.player.Player player;

    public PlayerTickEvent(net.minecraft.world.entity.player.Player player) {
        this.player = player;
    }

    public net.minecraft.world.entity.player.Player getPlayerEntity() {
        return player;
    }

    public Player getPlayer() {
        return new Player(player);
    }

    public Level getWorld() {
        return player.level();
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(getWorld());
    }

    public boolean isClient() {
        return WorldUtil.isClient(getWorld());
    }

    public boolean isServer() {
        return !isClient();
    }
}
