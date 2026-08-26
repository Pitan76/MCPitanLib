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
        super(toRarity(builder.getRarity()), toTarget(builder.getTarget()), toSlots(builder.slots));
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

    public static Rarity toRarity(CompatEnchantmentRarity rarity) {
        switch (rarity) {
            case COMMON:
                return Rarity.COMMON;
            case UNCOMMON:
                return Rarity.UNCOMMON;
            case RARE:
                return Rarity.RARE;
            default:
                return Rarity.VERY_RARE;
        }
    }

    public static EnchantmentTarget toTarget(CompatEnchantmentTarget target) {
        switch (target) {
            case ARMOR:
                return EnchantmentTarget.ARMOR;
            case ARMOR_HEAD:
                return EnchantmentTarget.ARMOR_HEAD;
            case ARMOR_CHEST:
                return EnchantmentTarget.ARMOR_CHEST;
            case ARMOR_LEGS:
                return EnchantmentTarget.ARMOR_LEGS;
            case ARMOR_FEET:
                return EnchantmentTarget.ARMOR_FEET;
            case WEAPON:
                return EnchantmentTarget.WEAPON;
            case DIGGER:
                return EnchantmentTarget.DIGGER;
            case FISHING_ROD:
                return EnchantmentTarget.FISHING_ROD;
            case TRIDENT:
                return EnchantmentTarget.TRIDENT;
            case BOW:
                return EnchantmentTarget.BOW;
            case WEARABLE:
                return EnchantmentTarget.WEARABLE;
            case CROSSBOW:
                return EnchantmentTarget.CROSSBOW;
            case VANISHABLE:
                return EnchantmentTarget.VANISHABLE;
            default:
                return EnchantmentTarget.BREAKABLE;
        }
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
