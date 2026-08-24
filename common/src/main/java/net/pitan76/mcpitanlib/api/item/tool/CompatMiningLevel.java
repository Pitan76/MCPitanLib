package net.pitan76.mcpitanlib.api.item.tool;

import net.minecraft.block.Block;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

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
        switch (this) {
            case WOOD:
                return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
            case STONE:
                return BlockTags.INCORRECT_FOR_STONE_TOOL;
            case IRON:
                return BlockTags.INCORRECT_FOR_IRON_TOOL;
            case GOLD:
                return BlockTags.INCORRECT_FOR_GOLD_TOOL;
            case DIAMOND:
                return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
            case NETHERITE:
                return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
            default:
                return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
        }
    }
}
