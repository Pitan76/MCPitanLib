package net.pitan76.mcpitanlib.api.transfer.energy.v1;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

/**
 * エネルギーの相互運用を行うユーティリティ。
 * <p>
 * NeoForgeでは常に利用できるが、Fabricでは Team Reborn Energy が導入されている場合のみ動作する。
 * 利用できない場合、取得系は null を、挿入系は 0 を返し、登録系は何もしない。
 * 事前に {@link #isSupported()} で判定できる。
 */
public class EnergyStorageUtil {

    /**
     * この環境でエネルギーAPIが利用できるかどうか。
     */
    @ExpectPlatform
    public static boolean isSupported() {
        throw new AssertionError();
    }

    /**
     * 単純なエネルギーストレージを作成する。
     * @param capacity 容量
     * @param maxInsert 一度に挿入できる最大量
     * @param maxExtract 一度に取り出せる最大量
     * @return エネルギーストレージ (利用できない場合はnull)
     */
    @ExpectPlatform
    @Nullable
    public static IEnergyStorage create(long capacity, long maxInsert, long maxExtract) {
        throw new AssertionError();
    }

    /**
     * 指定した位置のエネルギーストレージを取得する。他MODの機械も対象になる。
     * @param world ワールド
     * @param pos 位置
     * @param side 面 (nullで面を指定しない)
     * @return エネルギーストレージ (存在しない場合はnull)
     */
    @ExpectPlatform
    @Nullable
    public static IEnergyStorage getEnergyStorage(Level world, BlockPos pos, @Nullable Direction side) {
        throw new AssertionError();
    }

    /**
     * BlockEntityの登録時にエネルギーストレージを紐付ける。
     * @param type BlockEntityType
     * @param provider BlockEntityと面からエネルギーストレージを返す関数
     */
    @ExpectPlatform
    public static void registerEnergyStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        throw new AssertionError();
    }

    /**
     * 他MODのBlockEntityへエネルギーを直接注入する。
     * @param blockEntity 対象のBlockEntity
     * @param amount 注入する最大量
     * @param side 面 (nullで面を指定しない)
     * @return 実際に注入された量
     */
    @ExpectPlatform
    public static long addEnergyToForeignTile(BlockEntity blockEntity, long amount, @Nullable Direction side) {
        throw new AssertionError();
    }

    /**
     * 指定した位置のエネルギーストレージを取得する。
     */
    @Nullable
    public static IEnergyStorage getEnergyStorage(Level world, BlockPos pos) {
        return getEnergyStorage(world, pos, null);
    }

    /**
     * BlockEntityのエネルギーストレージを取得する。
     */
    @Nullable
    public static IEnergyStorage getEnergyStorage(BlockEntity blockEntity, @Nullable Direction side) {
        if (blockEntity == null || blockEntity.getLevel() == null) return null;
        return getEnergyStorage(blockEntity.getLevel(), blockEntity.getBlockPos(), side);
    }

    /**
     * 他MODのBlockEntityへエネルギーを直接注入する。
     */
    public static long addEnergyToForeignTile(BlockEntity blockEntity, long amount) {
        return addEnergyToForeignTile(blockEntity, amount, null);
    }
}
