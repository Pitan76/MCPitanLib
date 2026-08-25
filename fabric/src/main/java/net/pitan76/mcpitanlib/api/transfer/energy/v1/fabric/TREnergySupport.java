package net.pitan76.mcpitanlib.api.transfer.energy.v1.fabric;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.lookup.block.BlockApiLookupWithDirection;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Team Reborn Energy に触れる処理をまとめたクラス。
 * <p>
 * team_reborn_energy が導入されていない環境ではこのクラスをロードしてはならない。
 * 呼び出しは必ず {@link EnergyStorageUtilImpl#isSupported()} で分岐した後に行うこと。
 */
public class TREnergySupport {

    private static BlockApiLookupWithDirection<EnergyStorage> lookup;

    private static final List<PendingRegistration> pending = new CopyOnWriteArrayList<>();

    /**
     * 呼び出す前に {@code EnergyStorageUtil#isSupported()} で判定すること。
     */
    public static BlockApiLookupWithDirection<EnergyStorage> getLookup() {
        if (lookup == null)
            lookup = BlockApiLookupWithDirection.ofDir(EnergyStorage.SIDED);

        return lookup;
    }

    public static IEnergyStorage create(long capacity, long maxInsert, long maxExtract) {
        return new FabricEnergyStorage(new SimpleEnergyStorage(capacity, maxInsert, maxExtract));
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(World world, BlockPos pos, @Nullable Direction side) {
        flushPending();

        EnergyStorage storage = EnergyStorage.SIDED.find(world, pos, side);
        if (storage == null) return null;

        return fromRaw(storage, side == null);
    }

    public static void registerEnergyStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        if (type == null) return;

        EnergyStorage.SIDED.registerForBlockEntities((blockEntity, direction) -> {
            IEnergyStorage storage = provider.apply(blockEntity, direction);
            if (storage == null) return null;

            return toRaw(storage);
        }, type);
    }

    public static void registerEnergyStorageLazy(Supplier<BlockEntityType<?>> typeSupplier, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        BlockEntityType<?> type = typeSupplier.get();
        if (type != null) {
            registerEnergyStorage(type, provider);
            return;
        }

        pending.add(new PendingRegistration(typeSupplier, provider));
    }

    private static void flushPending() {
        if (pending.isEmpty()) return;

        for (PendingRegistration registration : pending) {
            BlockEntityType<?> type = registration.typeSupplier.get();
            if (type == null) continue;

            pending.remove(registration);
            registerEnergyStorage(type, registration.provider);
        }
    }

    /**
     * MCPitanLibを使うMODが登録したストレージは、往復変換で情報が落ちないよう中身をそのまま返す。
     */
    public static IEnergyStorage fromRaw(EnergyStorage storage, boolean nullSide) {
        if (storage instanceof TRWrappedEnergyStorage)
            return ((TRWrappedEnergyStorage) storage).storage;

        return new FabricEnergyStorage(storage, nullSide);
    }

    public static IEnergyStorage fromRaw(EnergyStorage storage) {
        return fromRaw(storage, false);
    }

    /**
     * ラッパー越しの二重変換を避けるため、元がFabricEnergyStorageならそのまま取り出す。
     */
    public static EnergyStorage toRaw(IEnergyStorage storage) {
        if (storage instanceof FabricEnergyStorage)
            return ((FabricEnergyStorage) storage).getRaw();

        return new TRWrappedEnergyStorage(storage);
    }

    private static class PendingRegistration {
        final Supplier<BlockEntityType<?>> typeSupplier;
        final BiFunction<BlockEntity, Direction, IEnergyStorage> provider;

        PendingRegistration(Supplier<BlockEntityType<?>> typeSupplier, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
            this.typeSupplier = typeSupplier;
            this.provider = provider;
        }
    }
}
