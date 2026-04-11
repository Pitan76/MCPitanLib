package net.pitan76.mcpitanlib.api.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.util.EnchantmentUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CompatEnchantment {
    private final ResourceKey<Enchantment> registryKey;

    @Deprecated
    public CompatEnchantment(ResourceKey<Enchantment> registryKey) {
        this.registryKey = registryKey;
    }

    public CompatEnchantment of(Identifier identifier) {
        return EnchantmentUtil.getEnchantment(identifier);
    }

    public Identifier getId() {
        return registryKey.registry();
    }

    @Deprecated
    public ResourceKey<Enchantment> getRegistryKey() {
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
    public Holder<Enchantment> getEntry(@Nullable Level world) {
        Optional<Holder.Reference<Enchantment>> optionalEntry;
        if (world == null) {
            optionalEntry = VanillaRegistries.createLookup()
                    .get(registryKey);
        } else {
            optionalEntry = world.registryAccess().get(registryKey);
        }

        return optionalEntry.orElseThrow();
    }

    public Enchantment getEnchantment(@Nullable Level world) {
        return getEntry(world).value();
    }

    public int getLevel(ItemStack stack, @Nullable Level world) {
        return EnchantmentHelper.getItemEnchantmentLevel(getEntry(world), stack);
    }
}
