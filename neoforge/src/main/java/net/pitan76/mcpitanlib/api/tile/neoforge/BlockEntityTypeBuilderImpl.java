package net.pitan76.mcpitanlib.api.tile.neoforge;

import com.mojang.datafixers.types.Type;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder;

public class BlockEntityTypeBuilderImpl {
    @Deprecated
    public static <T extends BlockEntity> BlockEntityType<T> build(Type<?> type, BlockEntityTypeBuilder.Factory<? extends T> factory, Block... blocks) {
        return new BlockEntityType<>(factory::create, blocks);
    }
}
