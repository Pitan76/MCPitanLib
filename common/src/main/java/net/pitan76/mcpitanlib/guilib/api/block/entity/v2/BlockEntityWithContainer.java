package net.pitan76.mcpitanlib.guilib.api.block.entity.v2;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;

public abstract class BlockEntityWithContainer extends net.pitan76.mcpitanlib.guilib.api.block.entity.BlockEntityWithContainer {
    public BlockEntityWithContainer(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public BlockEntityWithContainer(BlockEntityTypeWrapper type, TileCreateEvent e) {
        super(type.get(), e);
    }
}
