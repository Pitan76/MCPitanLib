package net.pitan76.mcpitanlib.api.util.particle.effect;

import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;

// Use: ParticleEffectUtil
public class ItemStackParticleEffectUtil {

    public ItemStackParticleEffectUtil() {
        // Empty constructor
    }

    public ItemStackParticleEffect create(ParticleType<ItemStackParticleEffect> type, ItemStack stack) {
        return new ItemStackParticleEffect(type, stack);
    }

    public ItemStackParticleEffect createTypedItem(ItemStack stack) {
        return new ItemStackParticleEffect(ParticleTypes.ITEM, stack);
    }
}
