package net.pitan76.mcpitanlib.api.tile;

import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;
import net.minecraft.block.entity.BlockEntity;

public interface ExtendBlockEntityTicker<T extends BlockEntity> {
    void tick(TileTickEvent event);
}
