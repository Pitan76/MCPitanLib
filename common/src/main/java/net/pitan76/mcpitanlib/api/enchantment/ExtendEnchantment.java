package net.pitan76.mcpitanlib.api.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.pitan76.mcpitanlib.api.enchantment.effect.EnchantmentEffectEvent;
import net.pitan76.mcpitanlib.api.enchantment.effect.EnchantmentEffectHandler;

/**
 * このバージョンではエンチャントがコードから登録できるため、
 * {@link EnchantmentBuilder} の内容をそのままEnchantmentとして実装する。
 * <p>
 * 1.21以降はデータパックレジストリへ移るので、そちらのブランチでは
 * builderがJSONを書き出す実装に差し替わる。
 */
public class ExtendEnchantment extends Enchantment {

    public final EnchantmentBuilder builder;

    public ExtendEnchantment(EnchantmentBuilder builder) {
        super(toRarity(builder.weight), toTarget(builder.supportedItems), toSlots(builder.slots));
        this.builder = builder;
    }

    @Override
    public int getMaxLevel() {
        return builder.maxLevel;
    }

    @Override
    public int getMinPower(int level) {
        return builder.minCostBase + (level - 1) * builder.minCostPerLevel;
    }

    @Override
    public int getMaxPower(int level) {
        return builder.maxCostBase + (level - 1) * builder.maxCostPerLevel;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        super.onTargetDamaged(user, target, level);

        EnchantmentEffectHandler handler = builder.getPostAttackHandler();
        if (handler == null) return;
        if (!(user.getEntityWorld() instanceof ServerWorld)) return;

        handler.apply(new EnchantmentEffectEvent((ServerWorld) user.getEntityWorld(), level, user,
                user.getMainHandStack(), target, target.getPos()));
    }

    /**
     * 1.21以降のweightに相当するものが無いので、近いRarityに割り当てる。
     */
    public static Rarity toRarity(int weight) {
        if (weight >= 10) return Rarity.COMMON;
        if (weight >= 5) return Rarity.UNCOMMON;
        if (weight >= 2) return Rarity.RARE;

        return Rarity.VERY_RARE;
    }

    /**
     * 1.21以降のアイテムタグ指定を、このバージョンのEnchantmentTargetに割り当てる。
     */
    public static EnchantmentTarget toTarget(String supportedItems) {
        if (supportedItems == null) return EnchantmentTarget.BREAKABLE;

        String value = supportedItems.toLowerCase();
        if (value.contains("weapon") || value.contains("sword")) return EnchantmentTarget.WEAPON;
        if (value.contains("armor")) return EnchantmentTarget.ARMOR;
        if (value.contains("head")) return EnchantmentTarget.ARMOR_HEAD;
        if (value.contains("chest")) return EnchantmentTarget.ARMOR_CHEST;
        if (value.contains("leg")) return EnchantmentTarget.ARMOR_LEGS;
        if (value.contains("foot") || value.contains("boots")) return EnchantmentTarget.ARMOR_FEET;
        if (value.contains("mining") || value.contains("digger") || value.contains("tool")) return EnchantmentTarget.DIGGER;
        if (value.contains("crossbow")) return EnchantmentTarget.CROSSBOW;
        if (value.contains("bow")) return EnchantmentTarget.BOW;
        if (value.contains("trident")) return EnchantmentTarget.TRIDENT;
        if (value.contains("fishing")) return EnchantmentTarget.FISHING_ROD;
        if (value.contains("wearable")) return EnchantmentTarget.WEARABLE;

        return EnchantmentTarget.BREAKABLE;
    }

    public static EquipmentSlot[] toSlots(java.util.List<String> slots) {
        if (slots == null || slots.isEmpty()) return new EquipmentSlot[]{EquipmentSlot.MAINHAND};

        java.util.List<EquipmentSlot> result = new java.util.ArrayList<>();
        for (String slot : slots) {
            switch (slot.toLowerCase()) {
                case "mainhand":
                    result.add(EquipmentSlot.MAINHAND);
                    break;
                case "offhand":
                    result.add(EquipmentSlot.OFFHAND);
                    break;
                case "hand":
                    result.add(EquipmentSlot.MAINHAND);
                    result.add(EquipmentSlot.OFFHAND);
                    break;
                case "head":
                    result.add(EquipmentSlot.HEAD);
                    break;
                case "chest":
                    result.add(EquipmentSlot.CHEST);
                    break;
                case "legs":
                    result.add(EquipmentSlot.LEGS);
                    break;
                case "feet":
                    result.add(EquipmentSlot.FEET);
                    break;
                case "armor":
                    result.add(EquipmentSlot.HEAD);
                    result.add(EquipmentSlot.CHEST);
                    result.add(EquipmentSlot.LEGS);
                    result.add(EquipmentSlot.FEET);
                    break;
                default:
                    break;
            }
        }

        if (result.isEmpty()) return new EquipmentSlot[]{EquipmentSlot.MAINHAND};

        return result.toArray(new EquipmentSlot[0]);
    }
}
