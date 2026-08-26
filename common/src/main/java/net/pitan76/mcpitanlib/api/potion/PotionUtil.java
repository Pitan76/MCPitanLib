package net.pitan76.mcpitanlib.api.potion;

import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

/**
 * ポーションのItemStackを組み立てるユーティリティ。
 * 1.20.5でNBTからDataComponentへ変わった差を吸収する。
 */
public class PotionUtil {

    public static RegistryEntry<Potion> toEntry(Potion potion) {
        return Registries.POTION.getEntry(potion);
    }

    public static RegistryEntry<Potion> toEntry(RegistryResult<Potion> potion) {
        return toEntry(potion.get());
    }

    public static RegistryEntry<StatusEffect> toEffectEntry(StatusEffect effect) {
        return Registries.STATUS_EFFECT.getEntry(effect);
    }

    public static RegistryEntry<StatusEffect> toEffectEntry(RegistryResult<StatusEffect> effect) {
        return toEffectEntry(effect.get());
    }

    public static Potion getPotion(CompatIdentifier id) {
        return Registries.POTION.get(id.toMinecraft());
    }

    /**
     * 通常の飲むポーション。
     */
    public static ItemStack createPotion(RegistryEntry<Potion> potion) {
        return PotionContentsComponent.createStack(Items.POTION, potion);
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
    public static ItemStack createSplashPotion(RegistryEntry<Potion> potion) {
        return PotionContentsComponent.createStack(Items.SPLASH_POTION, potion);
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
    public static ItemStack createLingeringPotion(RegistryEntry<Potion> potion) {
        return PotionContentsComponent.createStack(Items.LINGERING_POTION, potion);
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
    public static ItemStack createTippedArrow(RegistryEntry<Potion> potion) {
        return PotionContentsComponent.createStack(Items.TIPPED_ARROW, potion);
    }

    public static ItemStack createTippedArrow(RegistryResult<Potion> potion) {
        return createTippedArrow(toEntry(potion));
    }

    /**
     * ItemStackに入っているポーションを取得する。
     */
    public static RegistryEntry<Potion> getPotion(ItemStack stack) {
        PotionContentsComponent contents = stack.get(net.minecraft.component.DataComponentTypes.POTION_CONTENTS);
        if (contents == null) return null;

        return contents.potion().orElse(null);
    }

    public static boolean isPotion(ItemStack stack, RegistryEntry<Potion> potion) {
        RegistryEntry<Potion> current = getPotion(stack);

        return current != null && current.equals(potion);
    }
}
