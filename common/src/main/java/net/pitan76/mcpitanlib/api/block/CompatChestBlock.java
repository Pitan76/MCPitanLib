package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;

import java.util.function.Supplier;

public class CompatChestBlock extends ChestBlock {
    public CompatChestBlock(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(settings, supplier);
    }

    /**
     * @deprecated Use {@link #createBlockEntity(TileCreateEvent)} instead.
     */
    @Deprecated
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return createBlockEntity(new TileCreateEvent(pos, state));
    }

    /**
     * @deprecated Use {@link #createBlockEntity(TileCreateEvent)} instead.
     */
    @Deprecated
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return createBlockEntity(new TileCreateEvent(world));
    }

    /**
     * @see ExtendBlockEntityProvider#createBlockEntity(TileCreateEvent)
     */
    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return super.createBlockEntity(event.getBlockView());
    }
}
