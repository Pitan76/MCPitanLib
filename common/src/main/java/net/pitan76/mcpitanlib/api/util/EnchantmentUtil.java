package net.pitan76.mcpitanlib.api.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.enchantment.CompatEnchantment;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentUtil {
    public static CompatEnchantment getEnchantment(Identifier identifier) {
        return new CompatEnchantment(Registry.ENCHANTMENT.get(identifier));
    }

    public static Identifier getId(CompatEnchantment enchantment) {
        return enchantment.getId();
    }

    public static int getLevel(CompatEnchantment enchantment, ItemStack stack, @Nullable World world) {
        return enchantment.getLevel(stack, world);
    }

    // CompatIdentifier
    public static CompatEnchantment getEnchantment(CompatIdentifier identifier) {
        return getEnchantment(identifier.toMinecraft());
    }

    public static CompatIdentifier getCompatId(CompatEnchantment enchantment) {
        return CompatIdentifier.fromMinecraft(enchantment.getId());
    }

    public static List<CompatEnchantment> getEnchantments(ItemStack stack) {
        List<CompatEnchantment> enchantments = new ArrayList<>();

        EnchantmentHelper.get(stack).forEach((key, value) -> {
            enchantments.add(new CompatEnchantment(key));
        });

        return enchantments;
    }
}
