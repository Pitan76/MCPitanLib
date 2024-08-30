package net.pitan76.mcpitanlib.api.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.enchantment.CompatEnchantment;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentUtil {
    public static CompatEnchantment getEnchantment(Identifier identifier) {
        RegistryKey<Enchantment> registryKey = RegistryKey.of(RegistryKeys.ENCHANTMENT, identifier);
        return new CompatEnchantment(registryKey);
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

        EnchantmentHelper.getEnchantments(stack).getEnchantments().forEach((enchantment) -> {
            if (enchantment.getKey().isPresent())
                enchantments.add(new CompatEnchantment(enchantment.getKey().get()));
        });

        return enchantments;
    }
}
