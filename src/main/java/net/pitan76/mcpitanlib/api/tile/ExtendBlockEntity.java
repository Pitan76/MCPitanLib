package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.world.ExtendWorld;

public class ExtendBlockEntity extends CompatBlockEntity {
    public ExtendWorld world;

    public ExtendBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ExtendBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        this(type, event.getBlockPos(), event.getBlockState());
    }

    @Override
    public void setLevel(Level world) {
        super.setLevel(world);
        this.world = new ExtendWorld(world);
    }
}
