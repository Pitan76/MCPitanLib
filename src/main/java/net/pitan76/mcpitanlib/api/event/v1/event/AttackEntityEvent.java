package net.pitan76.mcpitanlib.api.event.v1.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import org.jetbrains.annotations.Nullable;

public class AttackEntityEvent {

    public Player player;
    public Level world;
    public Entity target;
    public InteractionHand hand;
    public EntityHitResult result;

    public AttackEntityEvent(net.minecraft.world.entity.player.Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result) {
        this(new Player(player), level, target, hand, result);
    }

    public AttackEntityEvent(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result) {
        this.player = player;
        this.world = level;
        this.target = target;
        this.hand = hand;
        this.result = result;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getWorld() {
        return world;
    }

    public Entity getTarget() {
        return target;
    }

    public EntityHitResult getResult() {
        return result;
    }

    public InteractionHand getHand() {
        return hand;
    }

    public ItemStack getStackInPlayer() {
        return player.getStackInHand(hand);
    }

    public Item getItemInPlayer() {
        return getStackInPlayer().getItem();
    }

    public net.pitan76.mcpitanlib.midohra.world.World getWorldAsMidohra() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackInPlayerAsMidohra() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStackInPlayer());
    }

    public ItemWrapper getItemWrapperInPlayer() {
        return ItemWrapper.of(getItemInPlayer());
    }
}
