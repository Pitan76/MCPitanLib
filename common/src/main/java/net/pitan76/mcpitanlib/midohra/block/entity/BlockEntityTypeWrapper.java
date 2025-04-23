package net.pitan76.mcpitanlib.midohra.block.entity;

import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.BlockView;

public class BlockEntityTypeWrapper {
    private final net.minecraft.block.entity.BlockEntityType<?> type;

    public static final BlockEntityTypeWrapper EMPTY = new BlockEntityTypeWrapper();

    protected BlockEntityTypeWrapper() {
        this.type = null;
    }

    protected BlockEntityTypeWrapper(net.minecraft.block.entity.BlockEntityType<?> blockEntity) {
        this.type = blockEntity;
    }

    public static BlockEntityTypeWrapper of(net.minecraft.block.entity.BlockEntityType<?> blockEntity) {
        return new BlockEntityTypeWrapper(blockEntity);
    }

    public static BlockEntityTypeWrapper of() {
        return EMPTY;
    }

    public net.minecraft.block.entity.BlockEntityType<?> get() {
        return type;
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean isPresent() {
        return get() != null;
    }

    public boolean supports(BlockState state) {
        return isPresent() && get().supports(state.getBlock().get());
    }

    public BlockEntityWrapper getBlockEntity(BlockView world, BlockPos pos) {
        if (isEmpty())
            return BlockEntityWrapper.EMPTY;

        return SupplierBlockEntityWrapper.of(get().get(world.getRaw(), pos.toMinecraft()));
    }

    public BlockEntityWrapper createBlockEntity(TileCreateEvent e) {
        if (isEmpty())
            return BlockEntityWrapper.EMPTY;

        return SupplierBlockEntityWrapper.of(get().instantiate());
    }
}
