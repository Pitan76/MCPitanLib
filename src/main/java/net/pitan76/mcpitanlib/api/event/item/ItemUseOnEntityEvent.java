package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

public class ItemUseOnEntityEvent extends BaseEvent {

    public ItemStack stack;
    public Player user;
    public LivingEntity entity;
    public InteractionHand hand;

    public ItemUseOnEntityEvent(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand) {
        this.stack = stack;
        this.user = new Player(user);
        this.hand = hand;
        this.entity = entity;
    }

    public ItemUseOnEntityEvent(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand) {
        this.stack = stack;
        this.user = user;
        this.hand = hand;
        this.entity = entity;
    }

    public ItemStack getStack() {
        return stack;
    }

    public InteractionHand getHand() {
        return hand;
    }

    public Player getUser() {
        return user;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public boolean isClient() {
        return user.isClient();
    }

    public CompatActionResult success() {
        return CompatActionResult.SUCCESS;
    }

    public CompatActionResult fail() {
        return CompatActionResult.FAIL;
    }

    public CompatActionResult pass() {
        return CompatActionResult.PASS;
    }

    public CompatActionResult consume() {
        return CompatActionResult.CONSUME;
    }

    public boolean isSneaking() {
        return user.isSneaking();
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
    }

    public Item getItem() {
        return stack.getItem();
    }

    public ItemWrapper getItemWrapper() {
        return ItemWrapper.of(getItem());
    }
}
