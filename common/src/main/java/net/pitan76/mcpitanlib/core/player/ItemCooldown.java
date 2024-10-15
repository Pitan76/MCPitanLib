package net.pitan76.mcpitanlib.core.player;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;

public class ItemCooldown {
    public final Player player;

    public ItemCooldown(Player player) {
        this.player = player;
    }

    public boolean isCoolingDown(Item item) {
        for (ItemStack stack : player.getMain()) {
            if (stack.getItem() == item) {
                return player.getItemCooldownManager().isCoolingDown(stack);
            }
        }

        return false;
    }

    public void set(Item item, int duration) {
        for (ItemStack stack : player.getMain()) {
            if (stack.getItem() == item) {
                player.getItemCooldownManager().set(stack, duration);
            }
        }
    }
}
