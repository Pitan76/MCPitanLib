package net.pitan76.mcpitanlib.api.enchantment;

/**
 * エンチャントを付けられるアイテムの種類。
 * <p>
 * 1.21以降はアイテムタグ (supported_items) に置き換わっているため、
 * そちらのバージョンでは対応するタグとして扱われる。
 */
public enum CompatEnchantmentTarget {
    ARMOR("#minecraft:enchantable/armor"),
    ARMOR_HEAD("#minecraft:enchantable/head_armor"),
    ARMOR_CHEST("#minecraft:enchantable/chest_armor"),
    ARMOR_LEGS("#minecraft:enchantable/leg_armor"),
    ARMOR_FEET("#minecraft:enchantable/foot_armor"),
    WEAPON("#minecraft:enchantable/melee_weapon"),
    DIGGER("#minecraft:enchantable/mining"),
    FISHING_ROD("#minecraft:enchantable/fishing"),
    TRIDENT("#minecraft:enchantable/trident"),
    BREAKABLE("#minecraft:enchantable/durability"),
    BOW("#minecraft:enchantable/bow"),
    WEARABLE("#minecraft:enchantable/equippable"),
    CROSSBOW("#minecraft:enchantable/crossbow"),
    VANISHABLE("#minecraft:enchantable/durability");

    private final String tag;

    CompatEnchantmentTarget(String tag) {
        this.tag = tag;
    }

    /**
     * 1.21以降の supported_items に書くタグ。
     */
    public String getTag() {
        return tag;
    }

    /**
     * タグ文字列から近いものを推測する。指定が無い場合のフォールバック用。
     */
    public static CompatEnchantmentTarget fromTag(String tag) {
        if (tag == null) return BREAKABLE;

        String value = tag.toLowerCase();
        if (value.contains("head")) return ARMOR_HEAD;
        if (value.contains("chest")) return ARMOR_CHEST;
        if (value.contains("leg")) return ARMOR_LEGS;
        if (value.contains("foot") || value.contains("boots")) return ARMOR_FEET;
        if (value.contains("armor")) return ARMOR;
        if (value.contains("weapon") || value.contains("sword") || value.contains("mace")) return WEAPON;
        if (value.contains("mining") || value.contains("digger") || value.contains("tool")) return DIGGER;
        if (value.contains("crossbow")) return CROSSBOW;
        if (value.contains("bow")) return BOW;
        if (value.contains("trident")) return TRIDENT;
        if (value.contains("fishing")) return FISHING_ROD;
        if (value.contains("equippable") || value.contains("wearable")) return WEARABLE;

        return BREAKABLE;
    }
}
