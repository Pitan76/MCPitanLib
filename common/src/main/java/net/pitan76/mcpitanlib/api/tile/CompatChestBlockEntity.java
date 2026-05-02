package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.packet.UpdatePacketType;
import net.pitan76.mcpitanlib.api.util.BlockEntityUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;

public class CompatChestBlockEntity extends ChestBlockEntity implements ICompatBlockEntity {
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

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(BlockEntityUtil.getWorld(this));
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(BlockEntityUtil.getPos(this));
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraBlockState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(BlockEntityUtil.getBlockState(this));
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraCachedState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(BlockEntityUtil.getCachedState(this));
    }

    public CompatChestBlockEntity(BlockEntityTypeWrapper type, TileCreateEvent event) {
        this(type.get(), event.getBlockPos(), event.getBlockState());
    }
}