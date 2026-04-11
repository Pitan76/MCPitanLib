package net.pitan76.mcpitanlib.core.player;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;

public class ItemCooldown {
    public final Player player;

    public ItemCooldown(Player player) {
        this.player = player;
    }

    public boolean isCoolingDown(Item item) {
        for (ItemStack stack : player.getMain()) {
            if (stack.getItem() == item) {
                return player.getItemCooldownManager().isOnCooldown(stack);
            }
        }

        return false;
    }

    public void set(Item item, int duration) {
        for (ItemStack stack : player.getMain()) {
            if (stack.getItem() == item) {
                player.getItemCooldownManager().addCooldown(stack, duration);
            }
        }
    }
}
