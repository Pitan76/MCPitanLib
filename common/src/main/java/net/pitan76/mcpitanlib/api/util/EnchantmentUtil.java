package net.pitan76.mcpitanlib.api.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.enchantment.CompatEnchantment;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnchantmentUtil {
    public static CompatEnchantment getEnchantment(Identifier identifier) {
        ResourceKey<Enchantment> registryKey = ResourceKey.create(Registries.ENCHANTMENT, identifier);
        return new CompatEnchantment(registryKey);
    }

    public static Identifier getId(CompatEnchantment enchantment) {
        return enchantment.getId();
    }

    public static int getLevel(CompatEnchantment enchantment, ItemStack stack, @Nullable Level world) {
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

        EnchantmentHelper.getEnchantmentsForCrafting(stack).keySet().forEach((enchantment) -> {
            if (enchantment.unwrapKey().isPresent())
                enchantments.add(new CompatEnchantment(enchantment.unwrapKey().get()));
        });

        return enchantments;
    }

    public static boolean hasEnchantment(ItemStack stack) {
        return EnchantmentHelper.hasAnyEnchantments(stack);
    }

    public static Map<CompatEnchantment, Integer> getEnchantment(ItemStack stack, @Nullable Level world) {
        Map<CompatEnchantment, Integer> enchantments = new HashMap<>();

        List<CompatEnchantment> enchantmentList = getEnchantments(stack);
        enchantmentList.forEach((enchantment) -> {
            enchantments.put(enchantment, getLevel(enchantment, stack, world));
        });

        return enchantments;
    }

    public static void setEnchantment(ItemStack stack, Map<CompatEnchantment, Integer> enchantments, @Nullable Level world) {
        ItemEnchantments.Mutable builder = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);

        enchantments.forEach((compatEnchantment, integer) -> {
            builder.upgrade(compatEnchantment.getEntry(world), integer);
        });

        EnchantmentHelper.setEnchantments(stack, builder.toImmutable());
    }

    public static void removeEnchantment(ItemStack stack) {
        stack.remove(DataComponents.ENCHANTMENTS);
    }

    /**
     * 指定したエンチャントが付いたエンチャントの本を作る。
     * データパックにそのエンチャントが無い場合は、素の本を返す。
     */
    public static ItemStack createEnchantedBook(net.pitan76.mcpitanlib.midohra.enchantment.EnchantmentWrapper enchantment, @Nullable Level world, int level) {
        ItemStack stack = new ItemStack(net.minecraft.world.item.Items.ENCHANTED_BOOK);
        if (world == null) return stack;

        enchantment.getEntry(world).ifPresent(entry -> {
            net.minecraft.world.item.enchantment.ItemEnchantments.Mutable builder =
                    new net.minecraft.world.item.enchantment.ItemEnchantments.Mutable(net.minecraft.world.item.enchantment.ItemEnchantments.EMPTY);
            builder.set(entry, level);
            stack.set(net.minecraft.core.component.DataComponents.STORED_ENCHANTMENTS, builder.toImmutable());
        });

        return stack;
    }

    public static ItemStack createEnchantedBook(net.pitan76.mcpitanlib.midohra.enchantment.EnchantmentWrapper enchantment, @Nullable Level world) {
        return createEnchantedBook(enchantment, world, 1);
    }
}
