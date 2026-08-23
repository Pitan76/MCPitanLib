package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;
import net.pitan76.mcpitanlib.api.packet.UpdatePacketType;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.BlockEntityUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import org.jetbrains.annotations.Nullable;

public class CompatBlockEntity extends BlockEntity implements ICompatBlockEntity, Tickable {
    public CompatBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    public CompatBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        this(type);
    }

    /**
     * 1.18以降はBlockEntityProvider#getTickerでtickされるが、1.16.5には無く、
     * Tickableを実装したBlockEntityしかtickされない。
     * ExtendBlockEntityTickerを実装している場合にそこへ流す。
     */
    @SuppressWarnings("unchecked")
    @Override
    public void tick() {
        if (!(this instanceof ExtendBlockEntityTicker)) return;
        if (world == null) return;

        BlockState state = getCachedState();
        if (state.getBlock() instanceof ExtendBlockEntityProvider
                && !((ExtendBlockEntityProvider) state.getBlock()).isTick()) return;

        ((ExtendBlockEntityTicker<CompatBlockEntity>) this)
                .tick(new TileTickEvent<>(world, getPos(), state, this));
    }

    public CompatBlockEntity(BlockEntityTypeWrapper type) {
        this(type.get());
    }

    @Nullable
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
        super.fromTag(getStateForNbt(), nbt);
    }

    // チャンク読み込み時のfromTagはworldがまだ無く、getCachedState()がNPEになる。
    // fromTagで渡された状態があればそちらを使う。
    private BlockState nbtState;

    private BlockState getStateForNbt() {
        if (nbtState != null) return nbtState;
        return world == null ? null : getCachedState();
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
        nbtState = state;

        try {
            // deprecated
            readNbtOverride(nbt);
            // ----

            readNbt(new ReadNbtArgs(nbt));
        } finally {
            nbtState = null;
        }
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
        this(type.get(), event);
    }
}
