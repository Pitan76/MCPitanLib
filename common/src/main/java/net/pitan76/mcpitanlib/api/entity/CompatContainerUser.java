package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CompatContainerUser {
    protected PlayerEntity containerUser;

    public CompatContainerUser(PlayerEntity containerUser) {
        this.containerUser = containerUser;
    }

    public double getContainerInteractionRange() {
        return 4.5d;
    }

    public LivingEntity asLivingEntity() {
        return containerUser;
    }

    public boolean isPlayer() {
        return true;
    }

    public Player asPlayer() {
        return new Player(containerUser);
    }

    @Deprecated
    public PlayerEntity getRaw() {
        return containerUser;
    }
}
