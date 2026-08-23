package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.packet.UpdatePacketType;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.BlockEntityUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import org.jetbrains.annotations.Nullable;

public class CompatBlockEntity extends BlockEntity implements ICompatBlockEntity {
    public CompatBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CompatBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        this(type, event.getBlockPos(), event.getBlockState());
    }

    public CompatBlockEntity(BlockEntityTypeWrapper type, BlockPos pos, BlockState state) {
        this(type.get(), pos, state);
    }

    public CompatBlockEntity(BlockEntityTypeWrapper type, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, net.pitan76.mcpitanlib.midohra.block.BlockState state) {
        this(type.get(), pos.toMinecraft(), state.toMinecraft());
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

    public void writeNbt(WriteNbtArgs args) {

    }

    public void readNbt(ReadNbtArgs args) {

    }

    public NbtCompound toInitialChunkDataNbt(CompatRegistryLookup registryLookup) {
        return super.toInitialChunkDataNbt();
    }

    @Deprecated
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return toInitialChunkDataNbt(new CompatRegistryLookup());
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
        super.readNbt(nbt);
    }

    // ----

    @Deprecated
    @Override
    public void writeNbt(NbtCompound nbt) {
        // deprecated
        writeNbtOverride(nbt);
        // ----

        writeNbt(new WriteNbtArgs(nbt));
    }

    @Deprecated
    @Override
    public void readNbt(NbtCompound nbt) {
        // deprecated
        readNbtOverride(nbt);
        // ----

        readNbt(new ReadNbtArgs(nbt));
    }

    public boolean isClient() {
        if (getWorld() == null)
            return false;

        return WorldUtil.isClient(getWorld());
    }

    @Deprecated
    @Override
    public @Nullable World getWorld() {
        return callGetWorld();
    }

    @Deprecated
    @Override
    public BlockPos getPos() {
        return callGetPos();
    }

    public World callGetWorld() {
        return super.getWorld();
    }

    public BlockPos callGetPos() {
        return super.getPos();
    }

    public BlockState callGetBlockState() {
        return BlockEntityUtil.getBlockState(this);
    }

    public BlockState callGetCachedState() {
        return BlockEntityUtil.getCachedState(this);
    }

    public boolean hasServerWorld() {
        return callGetWorld() instanceof ServerWorld;
    }

    public ServerWorld getServerWorld() {
        return BlockEntityUtil.getServerWorld(this);
    }

    public void callMarkDirty() {
        BlockEntityUtil.markDirty(this);
    }

    @Deprecated
    @Override
    public void markRemoved() {
        markRemovedOverride();
    }

    public void markRemovedOverride() {
        super.markRemoved();
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(callGetWorld());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(callGetPos());
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraBlockState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(callGetBlockState());
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraCachedState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(callGetCachedState());
    }

    public CompatBlockEntity(BlockEntityTypeWrapper type, TileCreateEvent event) {
        this(type.get(), event.getBlockPos(), event.getBlockState());
    }
}
