package net.pitan76.mcpitanlib.api.util.particle.effect;

import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;

// Use: ParticleEffectUtil
public class ItemStackParticleEffectUtil {

    public ItemStackParticleEffectUtil() {
        // Empty constructor
    }

    public ItemParticleOption create(ParticleType<ItemParticleOption> type, ItemStack stack) {
        return new ItemParticleOption(type, stack.getItem());
    }

    public ItemParticleOption createTypedItem(ItemStack stack) {
        return new ItemParticleOption(ParticleTypes.ITEM, stack.getItem());
    }
}
