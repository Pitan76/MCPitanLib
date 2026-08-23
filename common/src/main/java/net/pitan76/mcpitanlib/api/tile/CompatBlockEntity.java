package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.packet.UpdatePacketType;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.BlockEntityUtil;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;
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
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        switch (getUpdatePacketType().name) {
            case "BLOCK_ENTITY_UPDATE_S2C":
                return ClientboundBlockEntityDataPacket.create(this);
        }
        return super.getUpdatePacket();
    }

    public UpdatePacketType getUpdatePacketType() {
        return UpdatePacketType.NONE;
    }

    public void writeNbt(WriteNbtArgs args) {

    }

    public void readNbt(ReadNbtArgs args) {

    }

    public CompoundTag toInitialChunkDataNbt(CompatRegistryLookup registryLookup) {
        return super.getUpdateTag(registryLookup.getRegistryLookup());
    }

    @Deprecated
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return toInitialChunkDataNbt(new CompatRegistryLookup(registries));
    }

    // deprecated

    /**
     * @deprecated Use {@link #writeNbt(WriteNbtArgs)} instead
     */
    @Deprecated
    public void writeNbtOverride(CompoundTag nbt) {
        //super.writeNbt(nbt, wrapperLookupCache);
    }

    /**
     * @deprecated Use {@link #readNbt(ReadNbtArgs)} instead
     */
    @Deprecated
    public void readNbtOverride(CompoundTag nbt) {
        //super.readNbt(nbt, wrapperLookupCache);
    }

    @Deprecated
    private HolderLookup.Provider wrapperLookupCache;

    // ----


    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);
        CompoundTag nbt = NbtUtil.create();
        writeNbt(nbt, this.callGetWorld().registryAccess());
        writeNbt(new WriteNbtArgs(nbt, view, new CompatRegistryLookup(this.callGetWorld().registryAccess())));
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        super.loadAdditional(view);
        CompoundTag nbt = NbtDataConverter.data2nbt(view);
        readNbt(nbt, view.lookup());
        readNbt(new ReadNbtArgs(nbt, view, new CompatRegistryLookup(view.lookup())));
    }

    @Deprecated
    public void writeNbt(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        // deprecated
        wrapperLookupCache = registryLookup;
        writeNbtOverride(nbt);
        // ----
    }

    @Deprecated
    public void readNbt(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        // deprecated
        wrapperLookupCache = registryLookup;
        readNbtOverride(nbt);
        // ----
    }

    public boolean isClient() {
        if (getLevel() == null)
            return false;

        return WorldUtil.isClient(getLevel());
    }

    @Deprecated
    @Override
    public @Nullable Level getLevel() {
        return callGetWorld();
    }

    @Deprecated
    @Override
    public BlockPos getBlockPos() {
        return callGetPos();
    }

    public Level callGetWorld() {
        return super.getLevel();
    }

    public BlockPos callGetPos() {
        return super.getBlockPos();
    }

    public BlockState callGetBlockState() {
        return BlockEntityUtil.getBlockState(this);
    }

    public BlockState callGetCachedState() {
        return BlockEntityUtil.getCachedState(this);
    }

    public boolean hasServerWorld() {
        return callGetWorld() instanceof ServerLevel;
    }

    public ServerLevel getServerWorld() {
        return BlockEntityUtil.getServerWorld(this);
    }

    public void callMarkDirty() {
        BlockEntityUtil.markDirty(this);
    }

    @Deprecated
    @Override
    public void setRemoved() {
        markRemovedOverride();
    }

    public void markRemovedOverride() {
        super.setRemoved();
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
