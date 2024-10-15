package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

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

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
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

    public CompatActionResult success(ItemStack stack) {
        if (getStack() != stack) {
            CompatActionResult.create(ActionResult.SUCCESS.withNewHandStack(stack));
        }

        return success();
    }

    public CompatActionResult success(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return success(stack.toMinecraft());
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

    public CompatActionResult consume(ItemStack stack) {
        if (getStack() != stack) {
            CompatActionResult.create(ActionResult.CONSUME.withNewHandStack(stack));
        }

        return consume();
    }

    public CompatActionResult consume(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return consume(stack.toMinecraft());
    }

    public CompatActionResult consume() {
        return CompatActionResult.CONSUME;
    }

    public boolean isSneaking() {
        return user.isSneaking();
    }
}
