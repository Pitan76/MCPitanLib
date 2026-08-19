package net.pitan76.mcpitanlib.api.tile.fabric;

import com.mojang.datafixers.types.Type;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder;

public class BlockEntityTypeBuilderImpl {
    public static <T extends BlockEntity> BlockEntityType<T> build(BlockEntityTypeBuilder<T> builder, Type<?> type) {
        return BlockEntityType.Builder.<T>create((pos, state) -> builder.factory.create(pos, state), builder.blocks.toArray(new Block[0])).build(type);
    }
}
