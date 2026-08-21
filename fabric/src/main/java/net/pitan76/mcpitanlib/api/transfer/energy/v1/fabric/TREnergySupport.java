package net.pitan76.mcpitanlib.api.transfer.energy.v1.fabric;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.lookup.block.BlockApiLookupWithDirection;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

/**
 * Team Reborn Energy に触れる処理をまとめたクラス。
 * <p>
 * team_reborn_energy が導入されていない環境ではこのクラスをロードしてはならない。
 * 呼び出しは必ず {@link EnergyStorageUtilImpl#isSupported()} で分岐した後に行うこと。
 */
public class TREnergySupport {

    private static BlockApiLookupWithDirection<EnergyStorage> lookup;

    /**
     * Team Reborn Energyの {@code EnergyStorage.SIDED} をラップしたlookupを返す。
     * <p>
     * {@code new BlockApiLookupWithDirection<>(EnergyStorage.SIDED)} を毎回書かずに済む。
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
    public static IEnergyStorage getEnergyStorage(Level world, BlockPos pos, @Nullable Direction side) {
        EnergyStorage storage = EnergyStorage.SIDED.find(world, pos, side);
        if (storage == null) return null;

        return new FabricEnergyStorage(storage);
    }

    public static void registerEnergyStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        EnergyStorage.SIDED.registerForBlockEntities((blockEntity, direction) -> {
            IEnergyStorage storage = provider.apply(blockEntity, direction);
            if (storage == null) return null;

            return toRaw(storage);
        }, type);
    }

    /**
     * IEnergyStorageをTeam Reborn EnergyのEnergyStorageに変換する。
     * ラッパー越しの二重変換を避けるため、元がFabricEnergyStorageならそのまま取り出す。
     */
    public static EnergyStorage toRaw(IEnergyStorage storage) {
        if (storage instanceof FabricEnergyStorage)
            return ((FabricEnergyStorage) storage).getRaw();

        return new TRWrappedEnergyStorage(storage);
    }
}
