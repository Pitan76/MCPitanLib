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
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.nbt.NbtTag;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;

public class ExtendEntity extends Entity implements ICompatEntity {
    public ExtendEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Deprecated
    @Override
    public void initDataTracker(DataTracker.Builder builder) {
        initDataTracker();
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    public void initDataTracker() {
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {

    }

    public void writeCustomDataToNbt(NbtCompound nbt) {

    }

    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return null;
    }

    // 互換性用 (NbtTag型をOverrideすること)
    public void writeNbt(NbtTag nbt) {

    }

    public void readNbt(NbtTag nbt) {

    }

    @Deprecated
    @Override
    public void writeData(WriteView view) {
        super.writeData(view);
        NbtCompound nbt = new NbtCompound();
        writeNbt(NbtTag.from(nbt));
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    @Deprecated
    @Override
    public void readData(ReadView view) {
        super.readData(view);
        NbtCompound nbt = NbtDataConverter.data2nbt(view);
        readNbt(NbtTag.from(nbt));
    }

    @Override
    protected void readCustomData(ReadView view) {
        NbtCompound nbt = NbtDataConverter.data2nbt(view);
        readCustomDataFromNbt(nbt);
    }

    @Override
    protected void writeCustomData(WriteView view) {
        NbtCompound nbt = new NbtCompound();
        writeCustomDataToNbt(nbt);
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    // 1.14
    public NbtCompound toTag(NbtCompound nbt) {
        this.writeNbt(NbtTag.from(nbt));
        return nbt;
    }

    public NbtCompound fromTag(NbtCompound nbt) {
        this.readNbt(NbtTag.from(nbt));
        return nbt;
    }

    @Override
    public World getWorld() {
        return super.getWorld();
    }
}