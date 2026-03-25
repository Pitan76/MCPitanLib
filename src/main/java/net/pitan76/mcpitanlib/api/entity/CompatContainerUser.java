package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class CompatContainerUser {
    protected ContainerUser containerUser;

    public CompatContainerUser(ContainerUser containerUser) {
        this.containerUser = containerUser;
    }

    public double getContainerInteractionRange() {
        return containerUser.getContainerInteractionRange();
    }

    public LivingEntity asLivingEntity() {
        return containerUser.getLivingEntity();
    }

    public boolean isPlayer() {
        return containerUser instanceof Player;
    }

    public Player asPlayer() {
        return new Player((Player) containerUser);
    }

    @Deprecated
    public ContainerUser getRaw() {
        return containerUser;
    }
}
