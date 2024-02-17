package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.world.BlockView;
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
    @Override
    default BlockEntity createBlockEntity(BlockView world) {
        return createBlockEntity(new TileCreateEvent(world));
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

        // return new ...BlockEntity(world)
        return getBlockEntityType().instantiate();
    }

    @Nullable
    default <T extends BlockEntity> BlockEntityType<T> getBlockEntityType() {
        return null;
    }

    @Nullable
    default <T extends BlockEntity> ExtendBlockEntityTicker<T> getCompatibleTicker(World world, BlockState state, BlockEntityType<T> type) {

        return null;
    }

    default boolean isTick() {
        return false;
    }
}
