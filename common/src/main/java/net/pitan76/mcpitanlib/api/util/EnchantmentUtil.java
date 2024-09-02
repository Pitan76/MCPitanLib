package net.pitan76.mcpitanlib.api.util;

import net.minecraft.component.type.ItemEnchantmentsComponent;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void hasEnchantment(ItemStack stack) {
        EnchantmentHelper.hasEnchantments(stack);
    }

    public static Map<CompatEnchantment, Integer> getEnchantment(ItemStack stack, @Nullable World world) {
        Map<CompatEnchantment, Integer> enchantments = new HashMap<>();

        List<CompatEnchantment> enchantmentList = getEnchantments(stack);
        enchantmentList.forEach((enchantment) -> {
            enchantments.put(enchantment, getLevel(enchantment, stack, world));
        });

        return enchantments;
    }

    public static void setEnchantment(ItemStack stack, Map<CompatEnchantment, Integer> enchantments, @Nullable World world) {
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);

        enchantments.forEach((compatEnchantment, integer) -> {
            builder.add(compatEnchantment.getEntry(world), integer);
        });

        EnchantmentHelper.set(stack, builder.build());
    }
}
