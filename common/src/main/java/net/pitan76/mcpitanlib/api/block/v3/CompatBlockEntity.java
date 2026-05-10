package net.pitan76.mcpitanlib.api.block.v3;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;

public class CompatBlockEntity extends net.pitan76.mcpitanlib.api.tile.CompatBlockEntity {
    public CompatBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type);
    }

    public CompatBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        super(type, event);
    }

    public CompatBlockEntity(BlockEntityTypeWrapper type, TileCreateEvent event) {
        super(type, event);
    }

    public NbtCompound toInitChunkDataNbt(CompatRegistryLookup registryLookup) {
        return NbtCompound.of(super.toInitialChunkDataNbt(registryLookup));
    }

    @Override
    @Deprecated
    public net.minecraft.nbt.NbtCompound toInitialChunkDataNbt(CompatRegistryLookup registryLookup) {
        return toInitChunkDataNbt(registryLookup).toMinecraft();
    }
}
