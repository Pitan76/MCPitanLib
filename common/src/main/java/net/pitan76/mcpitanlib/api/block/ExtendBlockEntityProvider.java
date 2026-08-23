package net.pitan76.mcpitanlib.api.block;

import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.tile.ExtendBlockEntityTicker;
import org.jetbrains.annotations.Nullable;

public interface ExtendBlockEntityProvider extends EntityBlock {

    /**
     * @deprecated Use {@link #createBlockEntity(TileCreateEvent)} instead.
     */
    @Deprecated
    @Nullable
    default BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
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
        net.pitan76.mcpitanlib.api.tile.CompatBlockEntity compatBlockEntity = createCompatBlockEntity(event);
        if (compatBlockEntity != null) return compatBlockEntity;

        if (getBlockEntityType() == null) return null;

        // return new ...BlockEntity(pos, state)
        return getBlockEntityType().create(event.getBlockPos(), event.getBlockState());
    }

    /**
     * Override this instead of {@link #createBlockEntity(TileCreateEvent)} to avoid the vanilla BlockEntity type.
     * @return null to fall back to {@link #createBlockEntity(TileCreateEvent)}
     */
    @Nullable
    default net.pitan76.mcpitanlib.api.tile.CompatBlockEntity createCompatBlockEntity(TileCreateEvent event) {
        return null;
    }

    @Nullable
    default <T extends BlockEntity> BlockEntityType<T> getBlockEntityType() {
        return null;
    }

    @Nullable
    @Override
    default <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        if (isTick()) {
            return ((world1, pos, state1, blockEntity) -> {
                if (getBlockEntityType() == null || blockEntity == getBlockEntityType().getBlockEntity(world, pos)) {
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
        return EntityBlock.super.getTicker(world, state, type);
    }

    @Nullable
    default <T extends BlockEntity> ExtendBlockEntityTicker<T> getCompatibleTicker(Level world, BlockState state, BlockEntityType<T> type) {
        BlockEntityTicker<T> ticker = getTicker(world, state, type);
        if (ticker instanceof ExtendBlockEntityTicker<T>)
            return (ExtendBlockEntityTicker<T>) ticker;

        return null;
    }

    default boolean isTick() {
        return false;
    }
}
