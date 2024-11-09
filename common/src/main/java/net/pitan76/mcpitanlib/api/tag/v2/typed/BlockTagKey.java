package net.pitan76.mcpitanlib.api.tag.v2.typed;

import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.block.BlockUtil;

import java.util.List;

public class BlockTagKey extends CompatTagKey<Block> {
    @Deprecated
    public BlockTagKey(TagKey<Block> tagKey) {
        super(tagKey);
    }

    public static BlockTagKey of(CompatIdentifier identifier) {
        return new BlockTagKey(TagKey.of(CompatTagKeyType.BLOCK.getRegistryKey(), identifier.toMinecraft()));
    }

    public List<Block> values() {
        return BlockUtil.getInTag(this);
    }
}
