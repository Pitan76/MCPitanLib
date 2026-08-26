package net.pitan76.mcpitanlib.api.potion;

import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

/**
 * ポーションのItemStackを組み立てるユーティリティ。
 * 1.20.5でNBTからDataComponentへ変わった差を吸収する。
 */
public class PotionUtil {

    public static Holder<Potion> toEntry(Potion potion) {
        return BuiltInRegistries.POTION.wrapAsHolder(potion);
    }

    public static Holder<Potion> toEntry(RegistryResult<Potion> potion) {
        return toEntry(potion.get());
    }

    public static Holder<MobEffect> toEffectEntry(MobEffect effect) {
        return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
    }

    public static Holder<MobEffect> toEffectEntry(RegistryResult<MobEffect> effect) {
        return toEffectEntry(effect.get());
    }

    public static Potion getPotion(CompatIdentifier id) {
        return BuiltInRegistries.POTION.getValue(id.toMinecraft());
    }

    /**
     * 通常の飲むポーション。
     */
    public static ItemStack createPotion(Holder<Potion> potion) {
        return PotionContents.createItemStack(Items.POTION, potion);
    }

    public static ItemStack createPotion(RegistryResult<Potion> potion) {
        return createPotion(toEntry(potion));
    }

    public static ItemStack createPotion(Potion potion) {
        return createPotion(toEntry(potion));
    }

    /**
     * スプラッシュポーション。
     */
    public static ItemStack createSplashPotion(Holder<Potion> potion) {
        return PotionContents.createItemStack(Items.SPLASH_POTION, potion);
    }

    public static ItemStack createSplashPotion(RegistryResult<Potion> potion) {
        return createSplashPotion(toEntry(potion));
    }

    public static ItemStack createSplashPotion(Potion potion) {
        return createSplashPotion(toEntry(potion));
    }

    /**
     * 残留ポーション。
     */
    public static ItemStack createLingeringPotion(Holder<Potion> potion) {
        return PotionContents.createItemStack(Items.LINGERING_POTION, potion);
    }

    public static ItemStack createLingeringPotion(RegistryResult<Potion> potion) {
        return createLingeringPotion(toEntry(potion));
    }

    public static ItemStack createLingeringPotion(Potion potion) {
        return createLingeringPotion(toEntry(potion));
    }

    /**
     * 濃厚ポーションの矢。
     */
    public static ItemStack createTippedArrow(Holder<Potion> potion) {
        return PotionContents.createItemStack(Items.TIPPED_ARROW, potion);
    }

    public static ItemStack createTippedArrow(RegistryResult<Potion> potion) {
        return createTippedArrow(toEntry(potion));
    }

    /**
     * ItemStackに入っているポーションを取得する。
     */
    public static Holder<Potion> getPotion(ItemStack stack) {
        PotionContents contents = stack.get(net.minecraft.core.component.DataComponents.POTION_CONTENTS);
        if (contents == null) return null;

        return contents.potion().orElse(null);
    }

    public static boolean isPotion(ItemStack stack, Holder<Potion> potion) {
        Holder<Potion> current = getPotion(stack);

        return current != null && current.equals(potion);
    }
}
