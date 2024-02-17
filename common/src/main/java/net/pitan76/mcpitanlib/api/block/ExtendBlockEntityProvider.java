package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.tile.ExtendBlockEntityTicker;
import org.jetbrains.annotations.Nullable;

public interface ExtendBlockEntityProvider extends BlockEntityProvider {

    /**
     * @deprecated Use {@link #createBlockEntity(TileCreateEvent)} instead.
     */
    @Deprecated
    @Nullable
    default BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return createBlockEntity(new TileCreateEvent(pos, state));
    }

    /**
     * create instance of BlockEntity
     * @param event TileCreateEvent
     * @return BlockEntity
     *
     * <pre>{@code
     * public BlockEntity createBlockEntity(TileCreateEvent e) {
     *    return new ExampleBlockEntity(e); // ExampleBlockEntity extends CompatBlockEntity
     * }</pre>
     */
    @Nullable
    default BlockEntity createBlockEntity(TileCreateEvent event) {
        if (getBlockEntityType() == null) return null;

        // return new ...BlockEntity(pos, state)
        return getBlockEntityType().instantiate(event.getBlockPos(), event.getBlockState());
    }

    @Nullable
    default <T extends BlockEntity> BlockEntityType<T> getBlockEntityType() {
        return null;
    }

    @Nullable
    @Override
    default <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (isTick()) {
            return ((world1, pos, state1, blockEntity) -> {
                if (getBlockEntityType() == null || blockEntity == getBlockEntityType().get(world, pos)) {
                    if (blockEntity instanceof ExtendBlockEntityTicker<?>) {
                        ExtendBlockEntityTicker<T> ticker = (ExtendBlockEntityTicker<T>) blockEntity;
                        ticker.tick(world, pos, state, blockEntity);
                    } else if (blockEntity instanceof BlockEntityTicker<?>) {
                        BlockEntityTicker<T> ticker = (BlockEntityTicker<T>) blockEntity;
                        ticker.tick(world, pos, state, blockEntity);
                    }
                }
            });
        }
        return BlockEntityProvider.super.getTicker(world, state, type);
    }

    @Nullable
    default <T extends BlockEntity> ExtendBlockEntityTicker<T> getCompatibleTicker(World world, BlockState state, BlockEntityType<T> type) {
        BlockEntityTicker<T> ticker = getTicker(world, state, type);
        if (ticker instanceof ExtendBlockEntityTicker<T>)
            return (ExtendBlockEntityTicker<T>) ticker;

        return null;
    }

    default boolean isTick() {
        return false;
    }
}
