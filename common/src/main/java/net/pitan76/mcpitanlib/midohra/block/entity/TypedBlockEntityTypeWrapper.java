package net.pitan76.mcpitanlib.midohra.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.world.World;
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
        return new TypedBlockEntityTypeWrapper<>((BlockEntityType<T>) wrapper.get());
    }

    @Override
    public BlockEntityType<T> get() {
        return (BlockEntityType<T>) super.get();
    }

    @Override
    public TypedBlockEntityWrapper<T> getBlockEntity(BlockView world, BlockPos pos) {
        return TypedBlockEntityWrapper.ofRaw(get().get(world.getRaw(), pos.toMinecraft()));
    }

    @Override
    public TypedBlockEntityWrapper<T> createBlockEntity(TileCreateEvent e) {
        T blockEntity = get().instantiate();
        if (e.getBlockView() instanceof World)
            blockEntity.setLocation((World) e.getBlockView(), e.getBlockPos());
        else
            blockEntity.setPos(e.getBlockPos());

        return TypedBlockEntityWrapper.ofRaw(blockEntity);
    }
}
