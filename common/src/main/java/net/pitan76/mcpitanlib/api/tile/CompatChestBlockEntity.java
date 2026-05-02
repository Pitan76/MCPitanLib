package net.pitan76.mcpitanlib.api.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
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


    // deprecated

    /**
     * @deprecated Use {@link #writeNbt(WriteNbtArgs)} instead
     */
    @Deprecated
    public void writeNbtOverride(NbtCompound nbt) {

    }

    /**
     * @deprecated Use {@link #readNbt(ReadNbtArgs)} instead
     */
    @Deprecated
    public void readNbtOverride(NbtCompound nbt) {

    }

    @Deprecated
    private RegistryWrapper.WrapperLookup wrapperLookupCache;

    // ----

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        NbtCompound nbt = NbtUtil.create();
        writeNbt(nbt, this.getWorld().getRegistryManager());
        writeNbt(new WriteNbtArgs(nbt, view, new CompatRegistryLookup(this.getWorld().getRegistryManager())));
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        NbtCompound nbt = NbtDataConverter.data2nbt(view);
        readNbt(nbt, view.getRegistries());
        readNbt(new ReadNbtArgs(nbt, view, new CompatRegistryLookup(view.getRegistries())));
    }

    @Deprecated
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        // deprecated
        wrapperLookupCache = registryLookup;
        writeNbtOverride(nbt);
        // ----
    }

    @Deprecated
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
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