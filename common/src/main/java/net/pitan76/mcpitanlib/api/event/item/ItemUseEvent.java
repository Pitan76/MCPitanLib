package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;

public class ItemUseEvent extends BaseEvent {

    public World world;
    public Player user;
    public Hand hand;
    public ItemStack stack;

    public ItemUseEvent(World world, PlayerEntity user, Hand hand) {
        this.world = world;
        this.user = new Player(user);
        this.hand = hand;
        this.stack = user.getStackInHand(hand);
    }

    public ItemStack getStack() {
        return stack;
    }

    public Hand getHand() {
        return hand;
    }

    public World getWorld() {
        return world;
    }
    public Player getUser() {
        return user;
    }

    public boolean isClient() {
        return world.isClient();
    }

    public TypedActionResult<ItemStack> success(ItemStack stack) {
        return TypedActionResult.success(stack);
    }

    public TypedActionResult<ItemStack> fail() {
        return TypedActionResult.fail(stack);
    }

    public TypedActionResult<ItemStack> pass() {
        return TypedActionResult.pass(stack);
    }

    public TypedActionResult<ItemStack> consume() {
        return TypedActionResult.consume(stack);
    }

    public boolean isSneaking() {
        return user.isSneaking();
    }
}
