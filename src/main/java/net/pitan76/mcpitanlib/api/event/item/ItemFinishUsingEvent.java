package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

public class ItemFinishUsingEvent extends BaseEvent {
    public ItemStack stack;
    public Level world;
    public LivingEntity user;

    public ItemFinishUsingEvent(ItemStack stack, Level world, LivingEntity user) {
        this.stack = stack;
        this.world = world;
        this.user = user;
    }

    public ItemStack getStack() {
        return stack;
    }

    public Level getWorld() {
        return world;
    }

    public LivingEntity getUser() {
        return user;
    }

    public boolean isClient() {
        return world.isClientSide();
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
    }

    public net.pitan76.mcpitanlib.midohra.world.World getWorldM() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public Item getItem() {
        return stack.getItem();
    }

    public ItemWrapper getItemWrapper() {
        return ItemWrapper.of(getItem());
    }
}
