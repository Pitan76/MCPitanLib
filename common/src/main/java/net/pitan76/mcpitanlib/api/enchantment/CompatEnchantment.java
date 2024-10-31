package net.pitan76.mcpitanlib.api.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.EnchantmentUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CompatEnchantment {
    private final RegistryKey<Enchantment> registryKey;

    @Deprecated
    public CompatEnchantment(RegistryKey<Enchantment> registryKey) {
        this.registryKey = registryKey;
    }

    public CompatEnchantment of(Identifier identifier) {
        return EnchantmentUtil.getEnchantment(identifier);
    }

    public Identifier getId() {
        return registryKey.getRegistry();
    }

    @Deprecated
    public RegistryKey<Enchantment> getRegistryKey() {
        return registryKey;
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

    @Deprecated
    public RegistryEntry<Enchantment> getEntry(@Nullable World world) {
        Optional<RegistryEntry.Reference<Enchantment>> optionalEntry;
        if (world == null) {
            optionalEntry = BuiltinRegistries.createWrapperLookup().createRegistryLookup()
                    .getOptionalEntry(RegistryKeys.ENCHANTMENT, registryKey);
        } else {
            optionalEntry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(registryKey);
        }

        return optionalEntry.orElseThrow();
    }

    public Enchantment getEnchantment(@Nullable World world) {
        return getEntry(world).value();
    }

    public int getLevel(ItemStack stack, @Nullable World world) {
        return EnchantmentHelper.getLevel(getEntry(world), stack);
    }
}
