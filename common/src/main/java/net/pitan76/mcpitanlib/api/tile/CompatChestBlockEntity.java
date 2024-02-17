package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.packet.UpdatePacketType;

public class CompatChestBlockEntity extends ChestBlockEntity {
    protected CompatChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType);
    }

    public CompatChestBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        this(type, event.getBlockPos(), event.getBlockState());
    }

    @Override
    @Deprecated
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        switch (getUpdatePacketType().name) {
            case "BLOCK_ENTITY_UPDATE_S2C":
                NbtCompound nbt = new NbtCompound();
                writeNbtOverride(nbt);
                return new BlockEntityUpdateS2CPacket(getPos(), 1, nbt);
        }
        return super.toUpdatePacket();
    }

    public UpdatePacketType getUpdatePacketType() {
        return UpdatePacketType.NONE;
    }

    public void writeNbtOverride(NbtCompound nbt) {
        super.writeNbt(nbt);
    }

    public void readNbtOverride(NbtCompound nbt) {
        super.fromTag(getCachedState(), nbt);
    }

    @Deprecated
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        writeNbtOverride(nbt);
        return nbt;
    }

    @Deprecated
    @Override
    public void fromTag(BlockState state, NbtCompound nbt) {
        readNbtOverride(nbt);
    }
}
