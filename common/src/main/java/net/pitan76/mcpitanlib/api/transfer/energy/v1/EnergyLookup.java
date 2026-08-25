package net.pitan76.mcpitanlib.api.transfer.energy.v1;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
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

    public boolean isSupported() {
        return EnergyStorageUtil.isSupported();
    }

    /**
     * 他MODの機械も対象になる。
     * <p>
     * <b>sideは原則として必ず渡すこと。</b> nullは「面を指定しない」を意味するが、
     * 面ごとにストレージを持つ機械 (TechRebornのケーブル等) は面なしのアクセスを想定しておらず、
     * 他MOD側で例外が発生しうる。MCPitanLibはnull面で取得したストレージについては
     * 例外を握り潰すが、正確な値が返る保証は無い。
     * 隣接ブロックとやり取りする場合は接触面 ({@code dir.getOpposite()}) を渡すこと。
     *
     * @param side 面 (nullで面を指定しない。非推奨)
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
    public IEnergyStorage find(net.minecraft.world.level.Level world, net.minecraft.core.BlockPos pos, @Nullable net.minecraft.core.Direction side) {
        return EnergyStorageUtil.getEnergyStorage(world, pos, side);
    }

    @Nullable
    public IEnergyStorage find(BlockEntityWrapper blockEntity, @Nullable Direction side) {
        return EnergyStorageUtil.getEnergyStorage(blockEntity.get(), side == null ? null : side.toMinecraft());
    }

    @Nullable
    public IEnergyStorage find(BlockEntity blockEntity, @Nullable net.minecraft.core.Direction side) {
        return EnergyStorageUtil.getEnergyStorage(blockEntity, side);
    }

    public void registerForBlockEntity(BiFunction<BlockEntity, net.minecraft.core.Direction, IEnergyStorage> provider, BlockEntityType<?> type) {
        EnergyStorageUtil.registerEnergyStorage(type, provider);
    }

    public void registerForBlockEntityWrapper(BiFunction<BlockEntityWrapper, Direction, IEnergyStorage> provider, BlockEntityTypeWrapper type) {
        EnergyStorageUtil.registerEnergyStorageLazy(type::get, (blockEntity, direction) ->
                provider.apply(BlockEntityWrapper.of(blockEntity), direction == null ? null : Direction.of(direction)));
    }

    /**
     * Forge / NeoForge ではBlockEntityTypeがMOD初期化時点ではまだ解決されていないため、
     * {@code registerForBlockEntity(provider, result.getOrNull())} と書くと
     * nullになって<b>何も登録されないまま黙って素通りする</b>。
     * {@link RegistryResult} を持っている場合は必ずこちらを使うこと。
     */
    public void registerForBlockEntity(BiFunction<BlockEntity, net.minecraft.core.Direction, IEnergyStorage> provider, RegistryResult<BlockEntityType<?>> type) {
        EnergyStorageUtil.registerEnergyStorageLazy(type::getOrNull, provider);
    }

    /**
     * @see #registerForBlockEntity(BiFunction, RegistryResult)
     */
    public void registerForBlockEntityWrapper(BiFunction<BlockEntityWrapper, Direction, IEnergyStorage> provider, RegistryResult<BlockEntityType<?>> type) {
        registerForBlockEntity((blockEntity, direction) ->
                provider.apply(BlockEntityWrapper.of(blockEntity), direction == null ? null : Direction.of(direction)), type);
    }

    /**
     * @return 実際に注入された量
     */
    public long addEnergyToForeignTile(BlockEntity blockEntity, long amount, @Nullable net.minecraft.core.Direction side) {
        return EnergyStorageUtil.addEnergyToForeignTile(blockEntity, amount, side);
    }

    public long addEnergyToForeignTile(BlockEntityWrapper blockEntity, long amount, @Nullable Direction side) {
        return EnergyStorageUtil.addEnergyToForeignTile(blockEntity.get(), amount, side == null ? null : side.toMinecraft());
    }
}
