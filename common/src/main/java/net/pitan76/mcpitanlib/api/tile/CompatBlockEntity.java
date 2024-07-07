package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.packet.UpdatePacketType;

public class CompatBlockEntity extends BlockEntity {
    public CompatBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    public CompatBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        this(type);
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

    public void writeNbt(WriteNbtArgs args) {

    }

    public void readNbt(ReadNbtArgs args) {

    }


    // deprecated

    /**
     * @deprecated Use {@link #writeNbt(WriteNbtArgs)} instead
     */
    @Deprecated
    public void writeNbtOverride(NbtCompound nbt) {
        super.writeNbt(nbt);
    }

    /**
     * @deprecated Use {@link #readNbt(ReadNbtArgs)} instead
     */
    @Deprecated
    public void readNbtOverride(NbtCompound nbt) {
        super.fromTag(getCachedState(), nbt);
    }

    // ----

    @Deprecated
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        // deprecated
        writeNbtOverride(nbt);
        // ----

        writeNbt(new WriteNbtArgs(nbt));
        return nbt;
    }

    @Deprecated
    @Override
    public void fromTag(BlockState state, NbtCompound nbt) {
        // deprecated
        readNbtOverride(nbt);
        // ----

        readNbt(new ReadNbtArgs(nbt));
    }

}
