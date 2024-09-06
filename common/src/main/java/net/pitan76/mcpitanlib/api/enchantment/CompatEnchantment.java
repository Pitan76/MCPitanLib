package net.pitan76.mcpitanlib.api.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.EnchantmentUtil;
import org.jetbrains.annotations.Nullable;

public class CompatEnchantment {
    private final Enchantment enchantment;

    @Deprecated
    public CompatEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public CompatEnchantment of(Identifier identifier) {
        return EnchantmentUtil.getEnchantment(identifier);
    }

    public Identifier getId() {
        return Registries.ENCHANTMENT.getId(enchantment);
    }

    public String toString() {
        return getId().toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CompatEnchantment) {
            return ((CompatEnchantment) obj).getId().equals(getId());
        }
        return false;
    }

    public Enchantment getEnchantment(@Nullable World world) {
        return enchantment;
    }

    public int getLevel(ItemStack stack, @Nullable World world) {
        return EnchantmentHelper.getLevel(enchantment, stack);
    }
}
