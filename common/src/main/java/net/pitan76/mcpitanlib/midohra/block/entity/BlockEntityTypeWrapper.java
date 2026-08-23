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

    public boolean hasBlockEntity(BlockView world, BlockPos pos) {
        if (isEmpty()) return false;
        return get().get(world.getRaw(), pos.toMinecraft()) != null;
    }


    public BlockEntityWrapper getBlockEntity(BlockView world, BlockPos pos) {
        if (isEmpty())
            return BlockEntityWrapper.EMPTY;

        return BlockEntityWrapper.of(get().get(world.getRaw(), pos.toMinecraft()));
    }

    public BlockEntityWrapper createBlockEntity(TileCreateEvent e) {
        if (isEmpty())
            return BlockEntityWrapper.EMPTY;

        return BlockEntityWrapper.of(get().instantiate());
    }

    @Override
    public int hashCode() {
        return isEmpty() ? 0 : get().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BlockEntityTypeWrapper)) return false;

        BlockEntityTypeWrapper other = (BlockEntityTypeWrapper) obj;

        if (isEmpty() && other.isEmpty()) return true;
        if (isEmpty() || other.isEmpty()) return false;

        return get().equals(other.get());
    }
}
