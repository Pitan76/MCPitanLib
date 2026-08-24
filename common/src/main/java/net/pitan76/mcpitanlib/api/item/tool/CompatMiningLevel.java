package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;

/**
 * ツールの採掘レベル。
 * 1.20.4以前はint、1.20.5以降は {@code INCORRECT_FOR_*_TOOL} タグで表現される。
 */
public enum CompatMiningLevel {

    WOOD(0),
    GOLD(0),
    STONE(1),
    IRON(2),
    DIAMOND(3),
    NETHERITE(4);

    private final int level;

    CompatMiningLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static CompatMiningLevel fromLevel(int level) {
        switch (level) {
            case 1:
                return STONE;
            case 2:
                return IRON;
            case 3:
                return DIAMOND;
            case 4:
                return NETHERITE;
            default:
                return WOOD;
        }
    }

    public TagKey<Block> getInverseTag() {
        return null;
    }
}
