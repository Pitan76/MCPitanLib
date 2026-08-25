package net.pitan76.mcpitanlib.api.transfer.energy.v1.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * FabricにはNeoForgeのCapabilityに相当する標準のエネルギーAPIが無いため、
 * 事実上の標準であるTeam Reborn Energyへ橋渡しする。
 * <p>
 * Team Reborn Energyは必須依存ではないので、導入されていない場合は
 * 取得系はnull、挿入系は0を返し、登録系は何もしない。
 */
public class EnergyStorageUtilImpl {

    public static final String ENERGY_MOD_ID = "team_reborn_energy";

    private static Boolean supported;

    public static boolean isSupported() {
        if (supported == null)
            supported = FabricLoader.getInstance().isModLoaded(ENERGY_MOD_ID);

        return supported;
    }

    @Nullable
    public static IEnergyStorage create(long capacity, long maxInsert, long maxExtract) {
        if (!isSupported()) return null;

        return TREnergySupport.create(capacity, maxInsert, maxExtract);
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(World world, BlockPos pos, @Nullable Direction side) {
        if (!isSupported()) return null;

        return TREnergySupport.getEnergyStorage(world, pos, side);
    }

    public static void registerEnergyStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        if (!isSupported()) return;

        TREnergySupport.registerEnergyStorage(type, provider);
    }

    public static void registerEnergyStorageLazy(Supplier<BlockEntityType<?>> typeSupplier, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        if (!isSupported()) return;

        TREnergySupport.registerEnergyStorageLazy(typeSupplier, provider);
    }

    public static long addEnergyToForeignTile(BlockEntity blockEntity, long amount, @Nullable Direction side) {
        if (!isSupported()) return 0;
        if (blockEntity == null || blockEntity.getWorld() == null) return 0;

        IEnergyStorage storage = getEnergyStorage(blockEntity.getWorld(), blockEntity.getPos(), side);
        if (storage == null || !storage.canInsertEnergy()) return 0;

        return storage.insertEnergy(amount);
    }
}
