package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;

public interface ExtendBlockEntityTicker<T extends BlockEntity> {
    void tick(TileTickEvent event);
}
