package net.pitan76.mcpitanlib.api.potion;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

/**
 * ポーションのItemStackを組み立てるユーティリティ。
 * このバージョンではNBTに保存される。
 */
public class PotionUtil {

    public static Potion toEntry(Potion potion) {
        return potion;
    }

    public static Potion toEntry(RegistryResult<Potion> potion) {
        return potion.get();
    }

    public static StatusEffect toEffectEntry(StatusEffect effect) {
        return effect;
    }

    public static StatusEffect toEffectEntry(RegistryResult<StatusEffect> effect) {
        return effect.get();
    }

    public static Potion getPotion(CompatIdentifier id) {
        return Registry.POTION.get(id.toMinecraft());
    }

    /**
     * 通常の飲むポーション。
     */
    public static ItemStack createPotion(Potion potion) {
        return net.minecraft.potion.PotionUtil.setPotion(new ItemStack(Items.POTION), potion);
    }

    public static ItemStack createPotion(RegistryResult<Potion> potion) {
        return createPotion(potion.get());
    }

    /**
     * スプラッシュポーション。
     */
    public static ItemStack createSplashPotion(Potion potion) {
        return net.minecraft.potion.PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), potion);
    }

    public static ItemStack createSplashPotion(RegistryResult<Potion> potion) {
        return createSplashPotion(potion.get());
    }

    /**
     * 残留ポーション。
     */
    public static ItemStack createLingeringPotion(Potion potion) {
        return net.minecraft.potion.PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), potion);
    }

    public static ItemStack createLingeringPotion(RegistryResult<Potion> potion) {
        return createLingeringPotion(potion.get());
    }

    /**
     * 効果付きの矢。
     */
    public static ItemStack createTippedArrow(Potion potion) {
        return net.minecraft.potion.PotionUtil.setPotion(new ItemStack(Items.TIPPED_ARROW), potion);
    }

    public static ItemStack createTippedArrow(RegistryResult<Potion> potion) {
        return createTippedArrow(potion.get());
    }

    /**
     * ItemStackに入っているポーションを取得する。
     */
    public static Potion getPotion(ItemStack stack) {
        return net.minecraft.potion.PotionUtil.getPotion(stack);
    }

    public static boolean isPotion(ItemStack stack, Potion potion) {
        return getPotion(stack) == potion;
    }
}
