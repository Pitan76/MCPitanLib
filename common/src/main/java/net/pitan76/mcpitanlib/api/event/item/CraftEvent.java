package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import org.jetbrains.annotations.Nullable;

public class CraftEvent {

    public ItemStack stack;
    public Level world;
    public Player player;

    public CraftEvent(ItemStack stack, Level world, Player player) {
        this.stack = stack;
        this.world = world;
        this.player = player;
    }

    public CraftEvent(ItemStack stack, Level world, net.minecraft.world.entity.player.Player player) {
        this.stack = stack;
        this.world = world;
        this.player = new Player(player);
    }

    public CraftEvent(ItemStack stack, Level world) {
        this.stack = stack;
        this.world = world;
    }

    public ItemStack getStack() {
        return stack;
    }

    public Level getWorld() {
        return world;
    }

    @Nullable
    public Player getPlayer() {
        return player;
    }

    public boolean isClient() {
        return world.isClientSide();
    }
}
