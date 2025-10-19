package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.ContainerUser;
import net.minecraft.entity.LivingEntity;

public class CompatContainerUser {
    protected ContainerUser containerUser;

    public double getContainerInteractionRange() {
        // Player = 4.5d
        return containerUser.getContainerInteractionRange();
    }

    public LivingEntity asLivingEntity() {
        return containerUser.asLivingEntity();
    }
}
