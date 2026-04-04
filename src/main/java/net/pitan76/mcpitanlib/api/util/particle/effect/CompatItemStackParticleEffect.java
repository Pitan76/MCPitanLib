package net.pitan76.mcpitanlib.api.util.particle.effect;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.ParticleEffectUtil;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

public class CompatItemStackParticleEffect extends CompatParticleEffect {
    @Deprecated
    public CompatItemStackParticleEffect(ItemParticleOption effect) {
        super(effect);
    }

    public CompatItemStackParticleEffect(ItemStack stack) {
        this(ParticleEffectUtil.itemStack.createTypedItem(stack));
    }

    @Deprecated
    public static CompatItemStackParticleEffect of(ItemParticleOption effect) {
        return new CompatItemStackParticleEffect(effect);
    }

    public static CompatItemStackParticleEffect of(ItemStack stack) {
        return new CompatItemStackParticleEffect(stack);
    }

    public static CompatItemStackParticleEffect of(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return new CompatItemStackParticleEffect(stack.toMinecraft());
    }

    public static CompatItemStackParticleEffect of(CompatParticleEffect effect) {
        if (effect.getRaw() instanceof ItemParticleOption itemEffect) {
            return new CompatItemStackParticleEffect(itemEffect);
        }
        throw new IllegalArgumentException("The provided particle effect is not an ItemParticleOption.");
    }

    public static CompatItemStackParticleEffect of(Item item) {
        return new CompatItemStackParticleEffect(ItemStackUtil.create(item));
    }

    public static CompatItemStackParticleEffect of(ItemWrapper item) {
        return of(item.get());
    }

    public ItemStack getStack() {
        ItemParticleOption itemEffect = (ItemParticleOption) getRaw();
        ItemStackTemplate template = itemEffect.getItem();
        return template.create();
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack());
    }
}
