package net.pitan76.mcpitanlib.api.transfer.energy.v1;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * エネルギーの相互運用を行うユーティリティ。
 * <p>
 * NeoForgeでは常に利用できるが、Fabricでは Team Reborn Energy が導入されている場合のみ動作する。
 * 利用できない場合、取得系は null を、挿入系は 0 を返し、登録系は何もしない。
 * 事前に {@link #isSupported()} で判定できる。
 */
public class EnergyStorageUtil {

    @ExpectPlatform
    public static boolean isSupported() {
        throw new AssertionError();
    }

    /**
     * @param maxInsert 1tickあたりに挿入できる最大量
     * @param maxExtract 1tickあたりに取り出せる最大量
     * @return 利用できない場合はnull
     */
    @ExpectPlatform
    @Nullable
    public static IEnergyStorage create(long capacity, long maxInsert, long maxExtract) {
        throw new AssertionError();
    }

    /**
     * 他MODの機械も対象になる。
     * @param side 面 (nullで面を指定しない)
     */
    @ExpectPlatform
    @Nullable
    public static IEnergyStorage getEnergyStorage(Level world, BlockPos pos, @Nullable Direction side) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerEnergyStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        throw new AssertionError();
    }

    /**
     * Forge / NeoForge ではBlockEntityTypeがDeferredRegister経由で登録されるため、
     * MOD初期化時点ではまだインスタンスが存在しない。
     * {@code RegistryResult#getOrNull()} をそのまま渡すとnullになり、
     * <b>何も登録されないまま黙って素通りする</b>ので、その場合はこちらを使うこと。
     * <p>
     * supplierは登録が実際に必要になるタイミング (Fabricでは即時、Forge/NeoForgeではCapability登録時) に評価される。
     *
     */
    @ExpectPlatform
    public static void registerEnergyStorageLazy(Supplier<BlockEntityType<?>> typeSupplier, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        throw new AssertionError();
    }

    /**
     * @return 実際に注入された量
     */
    @ExpectPlatform
    public static long addEnergyToForeignTile(BlockEntity blockEntity, long amount, @Nullable Direction side) {
        throw new AssertionError();
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(Level world, BlockPos pos) {
        return getEnergyStorage(world, pos, null);
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(BlockEntity blockEntity, @Nullable Direction side) {
        if (blockEntity == null || blockEntity.getLevel() == null) return null;
        return getEnergyStorage(blockEntity.getLevel(), blockEntity.getBlockPos(), side);
    }

    public static long addEnergyToForeignTile(BlockEntity blockEntity, long amount) {
        return addEnergyToForeignTile(blockEntity, amount, null);
    }
}
