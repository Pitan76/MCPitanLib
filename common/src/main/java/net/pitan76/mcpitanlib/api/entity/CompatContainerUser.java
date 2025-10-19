package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.ContainerUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CompatContainerUser {
    protected ContainerUser containerUser;

    public CompatContainerUser(ContainerUser containerUser) {
        this.containerUser = containerUser;
    }

    public double getContainerInteractionRange() {
        return containerUser.getContainerInteractionRange();
    }

    public LivingEntity asLivingEntity() {
        return containerUser.asLivingEntity();
    }

    public boolean isPlayer() {
        return containerUser instanceof PlayerEntity;
    }

    public Player asPlayer() {
        return new Player((PlayerEntity) containerUser);
    }
}
