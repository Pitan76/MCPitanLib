package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;

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

    public StackActionResult success(ItemStack stack) {
        if (getStack() != stack)
            StackActionResult.success(stack);

        return success();
    }

    public StackActionResult success(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return success(stack.toMinecraft());
    }

    public StackActionResult success() {
        return StackActionResult.create(CompatActionResult.SUCCESS, stack);
    }

    public StackActionResult fail() {
        return new StackActionResult(CompatActionResult.FAIL, stack);
    }

    public StackActionResult fail(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return StackActionResult.fail(stack.toMinecraft());
    }

    public StackActionResult pass() {
        return new StackActionResult(CompatActionResult.PASS, stack);
    }

    public StackActionResult pass(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return StackActionResult.pass(stack.toMinecraft());
    }

    public StackActionResult consume(ItemStack stack) {
        if (getStack() != stack)
            StackActionResult.consume(stack);

        return consume();
    }

    public CompatActionResult consume(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return consume(stack.toMinecraft());
    }

    public StackActionResult consume() {
        return StackActionResult.create(CompatActionResult.CONSUME, stack);
    }

    public boolean isSneaking() {
        return user.isSneaking();
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public IWorldView getWorldView() {
        return getMidohraWorld();
    }
}
