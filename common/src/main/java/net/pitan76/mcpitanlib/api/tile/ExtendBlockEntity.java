package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.world.ExtendWorld;

public class ExtendBlockEntity extends CompatBlockEntity {
    public ExtendWorld world;

    public ExtendBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    public ExtendBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        this(type);
    }

    @Override
    public void setLocation(World world, BlockPos pos) {
        super.setLocation(world, pos);
        this.world = new ExtendWorld(world);
    }
}
