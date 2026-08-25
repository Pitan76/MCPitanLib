package net.pitan76.mcpitanlib.api.transfer.fluid.v1;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

/**
 * 流体タンクのlookup。
 * <p>
 * Fabricの {@code FluidStorage.SIDED}、NeoForgeの {@code Capabilities.Fluid.BLOCK} を共通の形で扱う。
 * 使い方は {@link net.pitan76.mcpitanlib.api.transfer.energy.v1.EnergyLookup} と同じ。
 */
public class FluidLookup {

    public static final FluidLookup FLUID = new FluidLookup();

    protected FluidLookup() {
    }

    /**
     * 指定した位置の流体タンクを探す。他MODの機械も対象になる。
     * @param side 面 (nullで面を指定しない)
     * @return 流体タンク (見つからない場合はnull)
     */
    @Nullable
    public IFluidHandler find(World world, BlockPos pos, @Nullable Direction side) {
        return FluidStorageUtil.getFluidHandler(world.getRaw(), pos.toMinecraft(), side == null ? null : side.toMinecraft());
    }

    @Nullable
    public IFluidHandler find(World world, BlockPos pos) {
        return find(world, pos, null);
    }

    @Nullable
    public IFluidHandler find(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, @Nullable net.minecraft.util.math.Direction side) {
        return FluidStorageUtil.getFluidHandler(world, pos, side);
    }

    @Nullable
    public IFluidHandler find(BlockEntityWrapper blockEntity, @Nullable Direction side) {
        return find(blockEntity.get(), side == null ? null : side.toMinecraft());
    }

    @Nullable
    public IFluidHandler find(BlockEntity blockEntity, @Nullable net.minecraft.util.math.Direction side) {
        return FluidStorageUtil.getFluidHandler(blockEntity, side);
    }

    /**
     * BlockEntityTypeに流体タンクを紐付ける。
     * <p>
     * 渡せるのは {@link FluidStorageUtil#withFixedCapacity} で作った {@link IFluidStorage} で、
     * 自前実装のものは渡せない (プラットフォーム側のトランザクションを再現できないため)。
     * @param provider BlockEntityと面からタンクを返す関数
     */
    public void registerForBlockEntity(BiFunction<BlockEntity, net.minecraft.util.math.Direction, IFluidStorage> provider, BlockEntityType<?> type) {
        FluidStorageUtil.registerFluidStorage(type, provider);
    }

    /**
     * Forge / NeoForge ではBlockEntityTypeがMOD初期化時点ではまだ解決されていないため、
     * {@code registerForBlockEntity(provider, result.getOrNull())} と書くと
     * nullになって<b>何も登録されないまま黙って素通りする</b>。
     * {@link RegistryResult} を持っている場合は必ずこちらを使うこと。
     */
    public void registerForBlockEntity(BiFunction<BlockEntity, net.minecraft.util.math.Direction, IFluidStorage> provider, RegistryResult<BlockEntityType<?>> type) {
        FluidStorageUtil.registerFluidStorageLazy(type::getOrNull, provider);
    }

    public void registerForBlockEntityWrapper(BiFunction<BlockEntityWrapper, Direction, IFluidStorage> provider, BlockEntityTypeWrapper type) {
        FluidStorageUtil.registerFluidStorageLazy(type::get, (blockEntity, direction) ->
                provider.apply(BlockEntityWrapper.of(blockEntity), direction == null ? null : Direction.of(direction)));
    }
}
