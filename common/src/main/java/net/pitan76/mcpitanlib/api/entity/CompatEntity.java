package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.entity.InitDataTrackerArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

public class CompatEntity extends Entity {
    public CompatEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Deprecated
    @Override
    public void initDataTracker() {
        initDataTracker(new InitDataTrackerArgs(dataTracker));
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

    public World callGetWorld() {
        return super.world;
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
        return (ServerWorld) super.world;
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(callGetWorld());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraBlockPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(callGetBlockPos());
    }

     public Vector3d getMidohraPos() {
        return Vector3d.of(callGetPos());
     }

     public net.pitan76.mcpitanlib.midohra.world.ServerWorld getMidohraServerWorld() {
         return net.pitan76.mcpitanlib.midohra.world.ServerWorld.of(getServerWorld());
     }
}