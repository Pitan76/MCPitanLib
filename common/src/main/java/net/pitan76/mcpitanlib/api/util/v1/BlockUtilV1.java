package net.pitan76.mcpitanlib.api.util.v1;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.tag.MineableToolTags;

import java.util.ArrayList;
import java.util.List;

public class BlockUtilV1 {
    public static Block block(Identifier id) {
        return BuiltInRegistries.BLOCK.getValue(id);
    }

    /**
     * ～1.16?
     * @param settings
     * @param toolTags
     * @param level
     * @return
     */
    public static BlockBehaviour.Properties breakByTool(BlockBehaviour.Properties settings, MineableToolTags toolTags, int level) {
        return settings;
    }

    public static BlockBehaviour.Properties dropsNothing(BlockBehaviour.Properties settings) {
        return settings.noLootTable();
    }

    public static BlockBehaviour.Properties requiresTool(BlockBehaviour.Properties settings) {
        return settings.requiresCorrectToolForDrops();
    }

    public static boolean isExist(Identifier identifier) {
        return BuiltInRegistries.BLOCK.containsKey(identifier);
    }

    public static Identifier toID(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public static Block fromId(Identifier identifier) {
        return BuiltInRegistries.BLOCK.getValue(identifier);
    }

    public static List<Block> getAllBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (Block block : BuiltInRegistries.BLOCK) {
            blocks.add(block);
        }
        return blocks;
    }

    @Deprecated
    public static Block of(BlockBehaviour.Properties settings) {
        return new Block(settings);
    }

    public static Block of(CompatibleBlockSettings settings) {
        return of(settings.build());
    }

    public static int getRawId(Block block) {
        return BuiltInRegistries.BLOCK.getId(block);
    }

    public static Block fromIndex(int index) {
        return BuiltInRegistries.BLOCK.byId(index);
    }
}
