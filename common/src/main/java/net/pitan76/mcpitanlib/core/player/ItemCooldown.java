package net.pitan76.mcpitanlib.core.player;

import net.pitan76.mcpitanlib.api.entity.Player;
import net.minecraft.item.Item;

public class ItemCooldown {
    public final Player player;

    public ItemCooldown(Player player) {
        this.player = player;
    }

    public boolean isCoolingDown(Item item) {
        return player.getItemCooldownManager().isCoolingDown(item);
    }

    public void set(Item item, int duration) {
        player.getItemCooldownManager().set(item, duration);
    }
}
