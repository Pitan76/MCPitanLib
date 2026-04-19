package net.pitan76.mcpitanlib.midohra.block.entity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.BlockView;

public class TypedBlockEntityTypeWrapper<T extends BlockEntity> extends BlockEntityTypeWrapper {
    protected TypedBlockEntityTypeWrapper(BlockEntityType<T> type) {
        super(type);
    }

    public static <T extends BlockEntity> TypedBlockEntityTypeWrapper<T> ofRaw(BlockEntityType<T> type) {
        return new TypedBlockEntityTypeWrapper<>(type);
    }

    public static <T extends BlockEntity> TypedBlockEntityTypeWrapper<T> of(BlockEntityTypeWrapper wrapper) {
        if (wrapper instanceof SupplierBlockEntityTypeWrapper) {
            SupplierBlockEntityTypeWrapper supplierWrapper = (SupplierBlockEntityTypeWrapper) wrapper;
            return SupplierTypedBlockEntityTypeWrapper.of(supplierWrapper);
        }

        return new TypedBlockEntityTypeWrapper<>((BlockEntityType<T>) wrapper.get());
    }

    @Override
    public BlockEntityType<T> get() {
        return (BlockEntityType<T>) super.get();
    }

    @Override
    public TypedBlockEntityWrapper<T> getBlockEntity(BlockView world, BlockPos pos) {
        return TypedBlockEntityWrapper.ofRaw(get().getBlockEntity(world.getRaw(), pos.toMinecraft()));
    }

    @Override
    public TypedBlockEntityWrapper<T> createBlockEntity(TileCreateEvent e) {
        return TypedBlockEntityWrapper.ofRaw(get().create(e.getBlockPos(), e.getBlockState()));
    }
}
