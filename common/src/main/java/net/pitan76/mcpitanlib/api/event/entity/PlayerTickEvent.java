package net.pitan76.mcpitanlib.api.event.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class PlayerTickEvent extends BaseEvent {
    public PlayerEntity player;

    public PlayerTickEvent(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayerEntity() {
        return player;
    }

    public Player getPlayer() {
        return new Player(player);
    }

    public World getWorld() {
        return player.getEntityWorld();
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
