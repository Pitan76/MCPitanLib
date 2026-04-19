package net.pitan76.mcpitanlib.midohra.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class TypedEntityTypeWrapper<T extends Entity> extends EntityTypeWrapper {
    protected TypedEntityTypeWrapper(EntityType<T> type) {
        super(type);
    }

    public static <T extends Entity> TypedEntityTypeWrapper<T> ofRaw(EntityType<T> type) {
        return new TypedEntityTypeWrapper<>(type);
    }

    public static <T extends Entity> TypedEntityTypeWrapper<T> of(EntityTypeWrapper wrapper) {
        if (wrapper instanceof SupplierEntityTypeWrapper) {
            SupplierEntityTypeWrapper supplierWrapper = (SupplierEntityTypeWrapper) wrapper;
            return SupplierTypedEntityTypeWrapper.of(supplierWrapper);
        }

        return new TypedEntityTypeWrapper<>((EntityType<T>) wrapper.get());
    }

    @Override
    public EntityType<T> get() {
        return (EntityType<T>) super.get();
    }

    public TypedEntityWrapper<T> createEntity(ServerWorld world, SpawnReason spawnReason) {
        return TypedEntityWrapper.ofRaw(get().create(world.getRaw()));
    }

    public TypedEntityWrapper<T> createEntity(ServerWorld world) {
        return createEntity(world, SpawnReason.NATURAL);
    }

    public TypedEntityWrapper<T> createTypedEntity(ServerWorld world, @Nullable Consumer<TypedEntityWrapper<T>> afterConsumer, BlockPos pos, SpawnReason reason, boolean alignPosition, boolean invertY) {
        Consumer<T> consumer = afterConsumer != null ? entity -> {
            TypedEntityWrapper<T> wrapper = TypedEntityWrapper.ofRaw(entity);
            afterConsumer.accept(wrapper);
        } : null;

        return TypedEntityWrapper.ofRaw(get().create(world.getRaw(), null, null, null, pos.toMinecraft(), reason.getRaw(), alignPosition, invertY));
    }
}
