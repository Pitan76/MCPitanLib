package net.pitan76.mcpitanlib.api.item.v4;

import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;
import net.pitan76.mcpitanlib.api.item.tool.CompatMiningLevel;

/**
 * used CompatMiningLevel
 */
public interface CompatToolMaterial extends net.pitan76.mcpitanlib.api.item.v3.CompatToolMaterial {

    CompatMiningLevel getCompatMiningLevelEnum();

    @Override
    default int getCompatMiningLevel() {
        return getCompatMiningLevelEnum().getLevel();
    }

    @Override
    default TagKey<Block> getInverseTag() {
        return getCompatMiningLevelEnum().getInverseTag();
    }
}
