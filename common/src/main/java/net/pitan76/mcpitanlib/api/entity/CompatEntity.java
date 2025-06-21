package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.entity.InitDataTrackerArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;

public class CompatEntity extends Entity {
    public CompatEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Deprecated
    @Override
    public void initDataTracker(DataTracker.Builder builder) {
        initDataTracker(new InitDataTrackerArgs(builder));
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    public void initDataTracker(InitDataTrackerArgs args) {

    }

    public void readCustomDataFromNbt(ReadNbtArgs nbt) {
        readCustomData(nbt.view);
    }

    public void writeCustomDataToNbt(WriteNbtArgs nbt) {
        writeCustomData(nbt.view);
    }

    @Deprecated
    @Override
    protected void readCustomData(ReadView view) {
        NbtCompound nbt = NbtDataConverter.data2nbt(view);
        readCustomDataFromNbt(new ReadNbtArgs(nbt, view));
    }

    @Deprecated
    @Override
    protected void writeCustomData(WriteView view) {
        NbtCompound nbt = new NbtCompound();
        writeCustomDataToNbt(new WriteNbtArgs(nbt, view));
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return null;
    }

    public void writeNbt(WriteNbtArgs args) {
        writeData(args.view);
    }

    public void readNbt(ReadNbtArgs args) {
        readData(args.view);
    }

    @Deprecated
    @Override
    public void writeData(WriteView view) {
        super.writeData(view);
        NbtCompound nbt = new NbtCompound();
        writeNbt(new WriteNbtArgs(nbt, view));
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    @Deprecated
    @Override
    public void readData(ReadView view) {
        super.readData(view);
        NbtCompound nbt = NbtDataConverter.data2nbt(view);
        readNbt(new ReadNbtArgs(nbt, view));
    }

    @Deprecated
    @Override
    public World getWorld() {
        return callGetWorld();
    }

    public World callGetWorld() {
        return super.getWorld();
    }

    public BlockPos callGetBlockPos() {
        return getBlockPos();
    }

    public Vec3d callGetPos() {
        return getPos();
    }

    public boolean hasServerWorld() {
        return callGetWorld() instanceof ServerWorld;
    }

    public ServerWorld getServerWorld() {
        return (ServerWorld) getWorld();
    }
}