package net.pitan76.mcpitanlib.midohra.easybuilder.built;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.easybuilder.BlockEntityBuilder;

import java.util.function.BiConsumer;

public class BuiltBlockEntity extends CompatBlockEntity {

    public BiConsumer<BlockEntityWrapper, WriteNbtArgs> onWriteNbt;
    public BiConsumer<BlockEntityWrapper, ReadNbtArgs> onReadNbt;

    public BuiltBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public BuiltBlockEntity(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public BuiltBlockEntity(BlockEntityTypeWrapper type, TileCreateEvent e) {
        super(type.get(), e);
    }

    public BuiltBlockEntity(BlockEntityTypeWrapper type, BlockEntityBuilder builder, TileCreateEvent e) {
        this(type, e);
        init(builder);
    }

    protected void init(BlockEntityBuilder builder) {
        this.onWriteNbt = builder.onWriteNbt;
        this.onReadNbt = builder.onReadNbt;

        if (builder.onInit != null)
            builder.onInit.accept(_wrap(), builder);
    }

    @Override
    public void writeNbt(WriteNbtArgs args) {
        super.writeNbt(args);
        if (onWriteNbt != null) {
            onWriteNbt.accept(_wrap(), args);
        }
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        super.readNbt(args);
        if (onReadNbt != null) {
            onReadNbt.accept(_wrap(), args);
        }
    }

    public BlockEntityWrapper _wrap() {
        return BlockEntityWrapper.of(this);
    }
}
