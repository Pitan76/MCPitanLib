package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.core.BlockPos;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.packet.UpdatePacketType;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.BlockEntityUtil;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import org.jetbrains.annotations.Nullable;

public class CompatChestBlockEntity extends ChestBlockEntity implements ICompatBlockEntity {
    protected CompatChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public CompatChestBlockEntity(BlockEntityType<?> type, TileCreateEvent event) {
        this(type, event.getBlockPos(), event.getBlockState());
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


    // deprecated

    /**
     * @deprecated Use {@link #writeNbt(WriteNbtArgs)} instead
     */
    @Deprecated
    public void writeNbtOverride(CompoundTag nbt) {

    }

    /**
     * @deprecated Use {@link #readNbt(ReadNbtArgs)} instead
     */
    @Deprecated
    public void readNbtOverride(CompoundTag nbt) {

    }

    @Deprecated
    private HolderLookup.Provider wrapperLookupCache;

    // ----

    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);
        CompoundTag nbt = NbtUtil.create();
        writeNbt(nbt, this.getLevel().registryAccess());
        writeNbt(new WriteNbtArgs(nbt, view, new CompatRegistryLookup(this.getLevel().registryAccess())));
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