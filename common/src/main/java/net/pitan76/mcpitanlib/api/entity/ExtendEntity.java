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
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.nbt.NbtTag;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;

public class ExtendEntity extends Entity {
    public ExtendEntity(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Deprecated
    @Override
    public void defineSynchedData(SynchedEntityData.Builder builder) {
        initDataTracker();
    }

    @Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
        return false;
    }

    public void initDataTracker() {
    }

    public void readCustomDataFromNbt(CompoundTag nbt) {

    }

    public void writeCustomDataToNbt(CompoundTag nbt) {

    }

    public Packet<ClientGamePacketListener> createSpawnPacket() {
        return null;
    }

    // 互換性用 (NbtTag型をOverrideすること)
    public void writeNbt(NbtTag nbt) {

    }

    public void readNbt(NbtTag nbt) {

    }

    @Deprecated
    @Override
    public void saveWithoutId(ValueOutput view) {
        super.saveWithoutId(view);
        CompoundTag nbt = new CompoundTag();
        writeNbt(NbtTag.from(nbt));
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    @Deprecated
    @Override
    public void load(ValueInput view) {
        super.load(view);
        CompoundTag nbt = NbtDataConverter.data2nbt(view);
        readNbt(NbtTag.from(nbt));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        CompoundTag nbt = NbtDataConverter.data2nbt(view);
        readCustomDataFromNbt(nbt);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput view) {
        CompoundTag nbt = new CompoundTag();
        writeCustomDataToNbt(nbt);
        NbtDataConverter.nbt2writeData(nbt, view);
    }

    // 1.14
    public CompoundTag toTag(CompoundTag nbt) {
        this.writeNbt(NbtTag.from(nbt));
        return nbt;
    }

    public CompoundTag fromTag(CompoundTag nbt) {
        this.readNbt(NbtTag.from(nbt));
        return nbt;
    }

    @Override
    public Level level() {
        return super.level();
    }
}