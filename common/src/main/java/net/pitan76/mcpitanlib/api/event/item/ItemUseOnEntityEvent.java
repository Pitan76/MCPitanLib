package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class ItemUseOnEntityEvent extends BaseEvent {

    public ItemStack stack;
    public Player user;
    public LivingEntity entity;
    public Hand hand;

    public ItemUseOnEntityEvent(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        this.stack = stack;
        this.user = new Player(user);
        this.hand = hand;
        this.entity = entity;
    }

    public ItemUseOnEntityEvent(ItemStack stack, Player user, LivingEntity entity, Hand hand) {
        this.stack = stack;
        this.user = user;
        this.hand = hand;
        this.entity = entity;
    }

    public ItemStack getStack() {
        return stack;
    }

    public Hand getHand() {
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
}
