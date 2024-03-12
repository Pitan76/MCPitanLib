package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.packet.UpdatePacketType;
import org.jetbrains.annotations.Nullable;

public class CompatBlockEntity extends BlockEntity {
    public CompatBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CompatBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        this(type, event.getBlockPos(), event.getBlockState());
    }

    @Nullable
    @Override
    @Deprecated
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        switch (getUpdatePacketType().name) {
            case "BLOCK_ENTITY_UPDATE_S2C":
                return BlockEntityUpdateS2CPacket.create(this);
        }
        return super.toUpdatePacket();
    }

    public UpdatePacketType getUpdatePacketType() {
        return UpdatePacketType.NONE;
    }

    public void writeNbtOverride(NbtCompound nbt) {
        super.writeNbt(nbt, wrapperLookupCache);
    }

    public void readNbtOverride(NbtCompound nbt) {
        super.readNbt(nbt, wrapperLookupCache);
    }

    private RegistryWrapper.WrapperLookup wrapperLookupCache;

    @Deprecated
    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        wrapperLookupCache = registryLookup;
        writeNbtOverride(nbt);
    }

    @Deprecated
    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        wrapperLookupCache = registryLookup;
        readNbtOverride(nbt);
    }


}
