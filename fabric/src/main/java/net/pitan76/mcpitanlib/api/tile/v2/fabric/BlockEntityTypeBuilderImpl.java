package net.pitan76.mcpitanlib.api.tile.v2.fabric;

import com.mojang.datafixers.types.Type;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.tile.v2.BlockEntityTypeBuilder;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityTypeBuilderImpl {
    public static <T extends BlockEntity> BlockEntityType<T> build(BlockEntityTypeBuilder<T> builder, Type<?> type) {
        List<Block> blocks = new ArrayList<>();
        if (builder.consumer != null)
            builder.consumer.accept(blocks);

        return BlockEntityType.Builder.<T>create((pos, state) -> builder.factory.create(pos, state), blocks.toArray(new Block[0])).build(type);
    }
}
