package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;

public class ThrownItemEntityUtil {
    public static ItemStack getItem(ThrownItemEntity entity) {
        return entity.getStack();
    }

    public static void setItem(ThrownItemEntity entity, ItemStack stack) {
        entity.setItem(stack);
    }
}
