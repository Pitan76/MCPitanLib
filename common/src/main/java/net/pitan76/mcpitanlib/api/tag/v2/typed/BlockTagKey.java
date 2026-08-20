package net.pitan76.mcpitanlib.api.tag.v2.typed;

import net.pitan76.mcpitanlib.core.tag.TagHooks;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.List;

public class BlockTagKey extends CompatTagKey<Block> {
    @Deprecated
    public BlockTagKey(Tag.Identified<Block> tagKey) {
        super(tagKey);
    }

    public static BlockTagKey of(CompatIdentifier identifier) {
        return new BlockTagKey(TagHooks.getOptional(identifier.toMinecraft(), CompatTagKeyType.BLOCK::getTagGroup));
    }

    public List<Block> values() {
        return getTagKey().values();
    }
}
