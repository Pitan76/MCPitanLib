package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import org.jetbrains.annotations.Nullable;

public class CraftEvent {

    public ItemStack stack;
    public World world;
    public Player player;

    public CraftEvent(ItemStack stack, World world, Player player) {
        this.stack = stack;
        this.world = world;
        this.player = player;
    }

    public CraftEvent(ItemStack stack, World world, PlayerEntity player) {
        this.stack = stack;
        this.world = world;
        this.player = new Player(player);
    }

    public CraftEvent(ItemStack stack, World world) {
        this.stack = stack;
        this.world = world;
    }

    public ItemStack getStack() {
        return stack;
    }

    public World getWorld() {
        return world;
    }

    @Nullable
    public Player getPlayer() {
        return player;
    }

    public boolean isClient() {
        return world.isClient();
    }
}
