package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.entity.CollisionEvent;
import net.pitan76.mcpitanlib.api.event.entity.EntityHitEvent;
import net.pitan76.mcpitanlib.api.event.entity.InitDataTrackerArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;

public abstract class CompatThrownItemEntity extends ThrowableItemProjectile {

    public CompatThrownItemEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    public CompatThrownItemEntity(EntityType<? extends ThrowableItemProjectile> entityType, double d, double e, double f, Level world) {
        super(entityType, d, e, f, world, ItemStackUtil.empty());
    }

    public CompatThrownItemEntity(EntityType<? extends ThrowableItemProjectile> entityType, LivingEntity livingEntity, Level world) {
        super(entityType, livingEntity, world, ItemStackUtil.empty());
    }

    public abstract Item getDefaultItemOverride();

    @Deprecated
    @Override
    protected Item getDefaultItem() {
        return getDefaultItemOverride();
    }

    public ItemStack callGetItem() {
        return super.getItem();
    }

    @Deprecated
    @Override
    public ItemStack getItem() {
        return callGetItem();
    }

    public void callSetItem(ItemStack stack) {
        super.setItem(stack);
    }

    @Deprecated
    @Override
    public void setItem(ItemStack stack) {
        callSetItem(stack);
    }

    public void callHandleStatus(byte status) {
        super.handleEntityEvent(status);
    }

    @Deprecated
    @Override
    public void handleEntityEvent(byte status) {
        callHandleStatus(status);
    }

    public void onEntityHit(EntityHitEvent event) {
        super.onHitEntity(event.entityHitResult);
    }

    @Deprecated
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        onEntityHit(new EntityHitEvent(entityHitResult));
    }

    public void onCollision(CollisionEvent event) {
        super.onHit(event.hitResult);
    }

    @Deprecated
    @Override
    protected void onHit(HitResult hitResult) {
        onCollision(new CollisionEvent(hitResult));
    }

    // ------------------ ExtendEntity ------------------

    @Deprecated
    @Override
    public void defineSynchedData(SynchedEntityData.Builder builder) {
        initDataTracker(new InitDataTrackerArgs(builder, entityData));
    }

    public void initDataTracker(InitDataTrackerArgs args) {
        super.defineSynchedData(args.getBuilder());
    }

    public void readCustomDataFromNbt(ReadNbtArgs nbt) {
        super.readAdditionalSaveData(nbt.view);
    }

    public void writeCustomDataToNbt(WriteNbtArgs nbt) {
        super.addAdditionalSaveData(nbt.view);
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

    public void writeNbt(WriteNbtArgs args) {
        super.saveWithoutId(args.view);
    }

    public void readNbt(ReadNbtArgs args) {
        super.load(args.view);
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

    @Override
    public Level level() {
        return super.level();
    }
}
