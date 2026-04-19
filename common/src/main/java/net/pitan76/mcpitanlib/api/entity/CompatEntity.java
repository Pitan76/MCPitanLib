package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.entity.InitDataTrackerArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;
import net.pitan76.mcpitanlib.midohra.entity.EntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

public class CompatEntity extends Entity {
    public CompatEntity(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Deprecated
    @Override
    public void defineSynchedData(SynchedEntityData.Builder builder) {
        initDataTracker(new InitDataTrackerArgs(builder));
    }

    @Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
        return false;
    }

    public void initDataTracker(InitDataTrackerArgs args) {

    }

    public void readCustomDataFromNbt(ReadNbtArgs nbt) {
        readAdditionalSaveData(nbt.view);
    }

    public void writeCustomDataToNbt(WriteNbtArgs nbt) {
        addAdditionalSaveData(nbt.view);
    }

    @Deprecated
    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        CompoundTag nbt = NbtDataConverter.data2nbt(view);
        readCustomDataFromNbt(new ReadNbtArgs(nbt, view));
    }

    @Deprecated
    @Override
    protected void addAdditionalSaveData(ValueOutput view) {
        CompoundTag nbt = new CompoundTag();
        writeCustomDataToNbt(new WriteNbtArgs(nbt, view));
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    public Packet<ClientGamePacketListener> createSpawnPacket() {
        return null;
    }

    public void writeNbt(WriteNbtArgs args) {
        saveWithoutId(args.view);
    }

    public void readNbt(ReadNbtArgs args) {
        load(args.view);
    }

    @Deprecated
    @Override
    public void saveWithoutId(ValueOutput view) {
        super.saveWithoutId(view);
        CompoundTag nbt = new CompoundTag();
        writeNbt(new WriteNbtArgs(nbt, view));
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    @Deprecated
    @Override
    public void load(ValueInput view) {
        super.load(view);
        CompoundTag nbt = NbtDataConverter.data2nbt(view);
        readNbt(new ReadNbtArgs(nbt, view));
    }

    @Deprecated
    @Override
    public Level level() {
        return callGetWorld();
    }

    public Level callGetWorld() {
        return super.level();
    }

    public BlockPos callGetBlockPos() {
        return blockPosition();
    }

    public Vec3 callGetPos() {
        return position();
    }

    public boolean hasServerWorld() {
        return callGetWorld() instanceof ServerLevel;
    }

    public ServerLevel getServerWorld() {
        return (ServerLevel) level();
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

    public CompatEntity(EntityTypeWrapper type, net.pitan76.mcpitanlib.midohra.world.World world) {
        super(type.get(), world.toMinecraft());
    }
}