package net.pitan76.mcpitanlib.api.item.args;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;

import java.util.Optional;

public class StoppedUsingArgs {

    public ItemStack stack;
    public Level world;
    public LivingEntity user;
    public int remainingUseTicks;

    public StoppedUsingArgs(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        this.stack = stack;
        this.world = world;
        this.user = user;
        this.remainingUseTicks = remainingUseTicks;
    }

    public StoppedUsingArgs(ItemStack stack, Level world, LivingEntity user) {
        this(stack, world, user, 0);
    }

    public StoppedUsingArgs(ItemStack stack, Level world, Player player, int remainingUseTicks) {
        this(stack, world, player.getEntity(), remainingUseTicks);
    }

    public StoppedUsingArgs(ItemStack stack, Level world, Player player) {
        this(stack, world, player, 0);
    }

    public ItemStack getStack() {
        return stack;
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStack() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
    }

    public Level getWorld() {
        return world;
    }

    public LivingEntity getUser() {
        return user;
    }

    public int getRemainingUseTicks() {
        return remainingUseTicks;
    }

    public boolean hasRemainingUseTicks() {
        return remainingUseTicks > 0;
    }

    public boolean hasNoRemainingUseTicks() {
        return !hasRemainingUseTicks();
    }

    public boolean isClient() {
        return world.isClientSide();
    }

    public boolean isServer() {
        return !isClient();
    }

    public boolean isPlayer() {
        return user instanceof Player;
    }

    public Optional<Player> getPlayer() {
        return isPlayer() ? Optional.of(new Player((Player) user)) : Optional.empty();
    }
}
