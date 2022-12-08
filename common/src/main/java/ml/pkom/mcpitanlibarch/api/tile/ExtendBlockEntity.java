package ml.pkom.mcpitanlibarch.api.tile;

import ml.pkom.mcpitanlibarch.api.event.block.TileCreateEvent;
import ml.pkom.mcpitanlibarch.api.world.ExtendWorld;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExtendBlockEntity extends BlockEntity {
    ExtendWorld world;
    public ExtendBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    public ExtendBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        this(type);
    }

    public void setWorld(World world) {
        super.setLocation(world, pos);
        this.world = new ExtendWorld(world);
    }

    @Override
    public void setLocation(World world, BlockPos pos) {
        super.setLocation(world, pos);
        this.world = new ExtendWorld(world);
    }
}