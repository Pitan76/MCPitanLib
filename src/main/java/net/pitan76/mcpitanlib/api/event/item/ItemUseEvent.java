package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;

public class ItemUseEvent extends BaseEvent {

    public Level world;
    public Player user;
    public InteractionHand hand;
    public ItemStack stack;

    public ItemUseEvent(Level world, Player user, InteractionHand hand) {
        this.world = world;
        this.user = new Player(user);
        this.hand = hand;
        this.stack = user.getItemInHand(hand);
    }

    public ItemStack getStack() {
        return stack;
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
    }

    public InteractionHand getHand() {
        return hand;
    }

    public Level getWorld() {
        return world;
    }

    public Player getUser() {
        return user;
    }

    public boolean isClient() {
        return world.isClientSide();
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
        return StackActionResult.create(CompatActionResult.SUCCESS);
    }

    public StackActionResult fail() {
        return StackActionResult.fail();
    }

    public StackActionResult fail(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return StackActionResult.fail(stack.toMinecraft());
    }

    public StackActionResult pass() {
        return StackActionResult.pass();
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
        return StackActionResult.create(CompatActionResult.CONSUME);
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

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack());
    }

    public Item getItem() {
        return stack.getItem();
    }

    public ItemWrapper getItemWrapper() {
        return ItemWrapper.of(getItem());
    }
}
