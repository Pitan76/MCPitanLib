package net.pitan76.mcpitanlib.api.util.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class BlockUtil {
    /**
     * Check if two blocks are equal.
     * @param block Block to compare.
     * @param block2 Block to compare.
     * @return If two blocks are equal.
     */
    public static boolean isEqual(Block block, Block block2) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.isEqual(block, block2);
    }

    /**
     * Get block from CompatIdentifier.
     * @param id CompatIdentifier of the block.
     * @return Block of the CompatIdentifier.
     */
    public static Block fromId(CompatIdentifier id) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.fromId(id);
    }

    public static Block fromId(String id) {
        return fromId(CompatIdentifier.of(id));
    }

    public static Block fromId(String namespace, String path) {
        return fromId(CompatIdentifier.of(namespace, path));
    }

    /**
     * Get CompatIdentifier from Block.
     * @param block Block to get CompatIdentifier.
     * @return CompatIdentifier of the Block.
     */
    public static CompatIdentifier toId(Block block) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.toCompatID(block);
    }

    public static String toIdAsString(Block block) {
        return toId(block).toString();
    }

    /**
     * Check if the block exist.
     * @param id CompatIdentifier of the block.
     * @return If the block exist.
     */
    public static boolean isExist(CompatIdentifier id) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.isExist(id);
    }

    public static boolean isExist(String id) {
        return isExist(CompatIdentifier.of(id));
    }

    public static boolean isExist(String namespace, String path) {
        return isExist(CompatIdentifier.of(namespace, path));
    }

    public static boolean isMinecraftBlock(Block block) {
        return CompatIdentifier.isMinecraftNamespace(toId(block));
    }

    /**
     * Create a new Block
     * @param settings Block settings
     * @return The new Block
     */
    public static Block create(CompatibleBlockSettings settings) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.of(settings);
    }

    /**
     * Get all blocks.
     * @return List of all blocks.
     */
    public static List<Block> getBlocks() {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.getAllBlocks();
    }

    /**
     * Get blocks from tag key.
     * @param tagKey Tag key of the blocks.
     * @return Blocks of the tag key.
     */
    public static List<Block> getInTag(TagKey<Block> tagKey) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.getBlocks(tagKey);
    }

    /**
     * Get blocks from tag key.
     * @param id CompatIdentifier of the tag key.
     * @return Blocks of the tag key.
     */
    public static List<Block> getInTag(CompatIdentifier id) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.getBlocks(id.toMinecraft());
    }

    public static List<Block> getInTag(String id) {
        return getInTag(CompatIdentifier.of(id));
    }

    public static List<Block> getInTag(String namespace, String path) {
        return getInTag(CompatIdentifier.of(namespace, path));
    }

    /**
     * Check if the block is in the tag.
     * @param block Block to check.
     * @param tagKey Tag key of the tag.
     * @return If the block is in the tag.
     */
    public static boolean isInTag(Block block, TagKey<Block> tagKey) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.isIn(block, tagKey);
    }

    /**
     * Check if the block is in the tag.
     * @param block Block to check.
     * @param id CompatIdentifier of the tag.
     * @return If the block is in the tag.
     */
    public static boolean isInTag(Block block, CompatIdentifier id) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.isBlockInTag(block, id.toMinecraft());
    }

    public static boolean isInTag(Block block, String id) {
        return isInTag(block, CompatIdentifier.of(id));
    }

    public static boolean isInTag(Block block, String namespace, String path) {
        return isInTag(block, CompatIdentifier.of(namespace, path));
    }

    /**
     * Get raw id of the block.
     * @param block Block to get raw id.
     * @return Raw id of the block.
     */
    public static int getRawId(Block block) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.getRawId(block);
    }

    /**
     * Get block from raw id.
     * @param rawId Raw id of the block.
     * @return Block of the raw id.
     */
    public static Block fromRawId(int rawId) {
        return net.pitan76.mcpitanlib.api.util.BlockUtil.fromIndex(rawId);
    }

    /**
     * Get blocks in the namespace.
     * @param namespace Namespace of the blocks.
     * @return List of blocks in the namespace.
     */
    public static List<Block> getBlocksInNamespace(String namespace) {
        List<Block> blocks = new ArrayList<>();

        for (Block block : getBlocks()) {
            if (toId(block).getNamespace().equals(namespace)) {
                blocks.add(block);
            }
        }

        return blocks;
    }

    /**
     * Get number of all blocks.
     * @return Number of all blocks.
     */
    public static int getNumberOfBlocks() {
        return getBlocks().size();
    }

    /**
     * Get item from block.
     * @param block Block
     * @return Item
     */
    public static Item toItem(Block block) {
        return block.asItem();
    }

    /**
     * Get name of the block.
     * @param block Block
     * @return Name of the block.
     */
    public static String getNameAsString(Block block) {
        return block.getName().getString();
    }

    /**
     * Get name of the block.
     * @param block Block
     * @return Name of the block.
     */
    public static TextComponent getName(Block block) {
        return new TextComponent(block.getName());
    }

    /**
     * Get translation key of the block.
     * @param block Block
     * @return Translation key of the block.
     */
    public static String getTranslationKey(Block block) {
        return block.getTranslationKey();
    }
}
