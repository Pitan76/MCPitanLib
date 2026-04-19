package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.entity.CollisionEvent;
import net.pitan76.mcpitanlib.api.event.entity.EntityHitEvent;
import net.pitan76.mcpitanlib.api.event.entity.InitDataTrackerArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;
import net.pitan76.mcpitanlib.midohra.entity.EntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.entity.IEntityM;

public abstract class CompatThrownItemEntity extends ThrownItemEntity implements IEntityM {

    public CompatThrownItemEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public CompatThrownItemEntity(EntityType<? extends ThrownItemEntity> entityType, double d, double e, double f, World world) {
        super(entityType, d, e, f, world, ItemStackUtil.empty());
    }

    public CompatThrownItemEntity(EntityType<? extends ThrownItemEntity> entityType, LivingEntity livingEntity, World world) {
        super(entityType, livingEntity, world, ItemStackUtil.empty());
    }

    public abstract Item getDefaultItemOverride();

    @Deprecated
    @Override
    protected Item getDefaultItem() {
        return getDefaultItemOverride();
    }

    public ItemStack callGetItem() {
        return super.getStack();
    }

    @Deprecated
    @Override
    public ItemStack getStack() {
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
        super.handleStatus(status);
    }

    @Deprecated
    @Override
    public void handleStatus(byte status) {
        callHandleStatus(status);
    }

    public void onEntityHit(EntityHitEvent event) {
        super.onEntityHit(event.entityHitResult);
    }

    @Deprecated
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        onEntityHit(new EntityHitEvent(entityHitResult));
    }

    public void onCollision(CollisionEvent event) {
        super.onCollision(event.hitResult);
    }

    @Deprecated
    @Override
    protected void onCollision(HitResult hitResult) {
        onCollision(new CollisionEvent(hitResult));
    }

    // ------------------ ExtendEntity ------------------

    @Deprecated
    @Override
    public void initDataTracker(DataTracker.Builder builder) {
        initDataTracker(new InitDataTrackerArgs(builder, dataTracker));
    }

    public void initDataTracker(InitDataTrackerArgs args) {
        super.initDataTracker(args.getBuilder());
    }

    public void readCustomDataFromNbt(ReadNbtArgs nbt) {
        super.readCustomData(nbt.view);
    }

    public void writeCustomDataToNbt(WriteNbtArgs nbt) {
        super.writeCustomData(nbt.view);
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

    public void writeNbt(WriteNbtArgs args) {
        super.writeData(args.view);
    }

    public void readNbt(ReadNbtArgs args) {
        super.readData(args.view);
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

    @Override
    public World getEntityWorld() {
        return super.getEntityWorld();
    }

    public void setStack(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        callSetItem(stack.toMinecraft());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(callGetItem());
    }

    public CompatThrownItemEntity(EntityTypeWrapper type, net.pitan76.mcpitanlib.midohra.world.World world) {
        this((EntityType<? extends ThrownItemEntity>) type.get(), world.toMinecraft());
    }
}
