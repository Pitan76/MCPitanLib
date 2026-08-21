package net.pitan76.mcpitanlib.api.transfer.energy.v1;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

/**
 * エネルギーストレージのlookup。
 * <p>
 * Fabricの {@code BlockApiLookupWithDirection}、NeoForgeのCapabilityを共通の形で扱う。
 * プラットフォームごとにlookupを用意する必要はなく、commonから {@link #ENERGY} をそのまま使える。
 * <p>
 * Fabricでは Team Reborn Energy が導入されていない場合があるが、その場合も例外にはならず
 * {@link #find} はnullを、{@link #registerForBlockEntity} は何もしない。
 * 事前に判定したい場合は {@link #isSupported()} を使うこと。
 */
public class EnergyLookup {

    public static final EnergyLookup ENERGY = new EnergyLookup();

    protected EnergyLookup() {
    }

    /**
     * この環境でエネルギーAPIが利用できるかどうか。
     */
    public boolean isSupported() {
        return EnergyStorageUtil.isSupported();
    }

    /**
     * 指定した位置のエネルギーストレージを探す。他MODの機械も対象になる。
     * @param world ワールド
     * @param pos 位置
     * @param side 面 (nullで面を指定しない)
     * @return エネルギーストレージ (見つからない場合はnull)
     */
    @Nullable
    public IEnergyStorage find(World world, BlockPos pos, @Nullable Direction side) {
        return EnergyStorageUtil.getEnergyStorage(world.getRaw(), pos.toMinecraft(), side == null ? null : side.toMinecraft());
    }

    @Nullable
    public IEnergyStorage find(World world, BlockPos pos) {
        return find(world, pos, null);
    }

    @Nullable
    public IEnergyStorage find(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, @Nullable net.minecraft.util.math.Direction side) {
        return EnergyStorageUtil.getEnergyStorage(world, pos, side);
    }

    @Nullable
    public IEnergyStorage find(BlockEntityWrapper blockEntity, @Nullable Direction side) {
        return EnergyStorageUtil.getEnergyStorage(blockEntity.get(), side == null ? null : side.toMinecraft());
    }

    @Nullable
    public IEnergyStorage find(BlockEntity blockEntity, @Nullable net.minecraft.util.math.Direction side) {
        return EnergyStorageUtil.getEnergyStorage(blockEntity, side);
    }

    /**
     * BlockEntityTypeにエネルギーストレージを紐付ける。
     * @param provider BlockEntityと面からエネルギーストレージを返す関数
     * @param type BlockEntityType
     */
    public void registerForBlockEntity(BiFunction<BlockEntity, net.minecraft.util.math.Direction, IEnergyStorage> provider, BlockEntityType<?> type) {
        EnergyStorageUtil.registerEnergyStorage(type, provider);
    }

    /**
     * BlockEntityTypeにエネルギーストレージを紐付ける。
     */
    public void registerForBlockEntityWrapper(BiFunction<BlockEntityWrapper, Direction, IEnergyStorage> provider, BlockEntityTypeWrapper type) {
        registerForBlockEntity((blockEntity, direction) ->
                provider.apply(BlockEntityWrapper.of(blockEntity), direction == null ? null : Direction.of(direction)), type.get());
    }

    /**
     * 他MODのBlockEntityへエネルギーを直接注入する。
     * @return 実際に注入された量
     */
    public long addEnergyToForeignTile(BlockEntity blockEntity, long amount, @Nullable net.minecraft.util.math.Direction side) {
        return EnergyStorageUtil.addEnergyToForeignTile(blockEntity, amount, side);
    }

    public long addEnergyToForeignTile(BlockEntityWrapper blockEntity, long amount, @Nullable Direction side) {
        return EnergyStorageUtil.addEnergyToForeignTile(blockEntity.get(), amount, side == null ? null : side.toMinecraft());
    }
}
