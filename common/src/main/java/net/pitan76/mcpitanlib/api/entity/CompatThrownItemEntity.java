package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.entity.CollisionEvent;
import net.pitan76.mcpitanlib.api.event.entity.EntityHitEvent;
import net.pitan76.mcpitanlib.api.event.entity.InitDataTrackerArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;

public abstract class CompatThrownItemEntity extends ThrownItemEntity {

    public CompatThrownItemEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public CompatThrownItemEntity(EntityType<? extends ThrownItemEntity> entityType, double d, double e, double f, World world) {
        super(entityType, d, e, f, world);
    }

    public CompatThrownItemEntity(EntityType<? extends ThrownItemEntity> entityType, LivingEntity livingEntity, World world) {
        super(entityType, livingEntity, world);
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
        initDataTracker(new InitDataTrackerArgs(builder));
    }

    public void initDataTracker(InitDataTrackerArgs args) {
        super.initDataTracker(args.getBuilder());
    }

    public void readCustomDataFromNbt(ReadNbtArgs nbt) {
        super.readCustomDataFromNbt(nbt.getNbt());
    }

    public void writeCustomDataToNbt(WriteNbtArgs nbt) {
        super.writeCustomDataToNbt(nbt.getNbt());
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
