package net.pitan76.mcpitanlib.api.event.v1.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import org.jetbrains.annotations.Nullable;

public class AttackEntityEvent {

    public Player player;
    public World world;
    public Entity target;
    public Hand hand;
    public EntityHitResult result;

    public AttackEntityEvent(PlayerEntity player, World level, Entity target, Hand hand, @Nullable EntityHitResult result) {
        this(new Player(player), level, target, hand, result);
    }

    public AttackEntityEvent(Player player, World level, Entity target, Hand hand, @Nullable EntityHitResult result) {
        this.player = player;
        this.world = level;
        this.target = target;
        this.hand = hand;
        this.result = result;
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public Entity getTarget() {
        return target;
    }

    public EntityHitResult getResult() {
        return result;
    }

    public Hand getHand() {
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
