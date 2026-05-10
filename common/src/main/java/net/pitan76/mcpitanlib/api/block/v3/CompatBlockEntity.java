package net.pitan76.mcpitanlib.api.block.v3;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;

public class CompatBlockEntity extends net.pitan76.mcpitanlib.api.tile.CompatBlockEntity {
    public CompatBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
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
    public CompoundTag toInitialChunkDataNbt(CompatRegistryLookup registryLookup) {
        return toInitChunkDataNbt(registryLookup).toMinecraft();
    }
}
