package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.entity.InitDataTrackerArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;

public class CompatEntity extends Entity {
    public CompatEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Deprecated
    @Override
    public void initDataTracker() {
        initDataTracker(new InitDataTrackerArgs());
    }

    public void initDataTracker(InitDataTrackerArgs args) {

    }

    public void readCustomDataFromNbt(ReadNbtArgs nbt) {

    }

    public void writeCustomDataToNbt(WriteNbtArgs nbt) {

    }

    @Deprecated
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        readCustomDataFromNbt(new ReadNbtArgs(nbt));
    }

    @Deprecated
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        writeCustomDataToNbt(new WriteNbtArgs(nbt));
    }

    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return null;
    }

    public void writeNbt(WriteNbtArgs args) {
        super.writeNbt(args.getNbt());
    }

    public void readNbt(ReadNbtArgs args) {
        super.readNbt(args.getNbt());
    }

    @Deprecated
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        writeNbt(new WriteNbtArgs(nbt));
        return nbt;
    }

    @Deprecated
    @Override
    public void readNbt(NbtCompound nbt) {
        readNbt(new ReadNbtArgs(nbt));
    }

    @Override
    public World getWorld() {
        return super.getWorld();
    }
}