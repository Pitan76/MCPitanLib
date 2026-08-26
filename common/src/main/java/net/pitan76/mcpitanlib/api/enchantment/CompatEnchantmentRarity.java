package net.pitan76.mcpitanlib.api.enchantment;

/**
 * エンチャントの希少度。
 * <p>
 * 1.21以降はweight (1〜10の重み) に置き換わっているため、
 * そちらのバージョンでは対応するweightとして扱われる。
 */
public enum CompatEnchantmentRarity {
    COMMON(10),
    UNCOMMON(5),
    RARE(2),
    VERY_RARE(1);

    private final int weight;

    CompatEnchantmentRarity(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public static CompatEnchantmentRarity fromWeight(int weight) {
        if (weight >= COMMON.weight) return COMMON;
        if (weight >= UNCOMMON.weight) return UNCOMMON;
        if (weight >= RARE.weight) return RARE;

        return VERY_RARE;
    }
}
