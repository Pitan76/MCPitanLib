package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class ItemFinishUsingEvent extends BaseEvent {
    public ItemStack stack;
    public World world;
    public LivingEntity user;

    public ItemFinishUsingEvent(ItemStack stack, World world, LivingEntity user) {
        this.stack = stack;
        this.world = world;
        this.user = user;
    }

    public ItemStack getStack() {
        return stack;
    }

    public World getWorld() {
        return world;
    }

    public LivingEntity getUser() {
        return user;
    }

    public boolean isClient() {
        return world.isClient();
    }
}
