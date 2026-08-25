package net.pitan76.mcpitanlib.api.transfer.energy.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

/**
 * Team Reborn Energy が導入されている場合のみロードされる。
 * 直接参照するとクラスロードで落ちるため、{@link TREnergySupport} 経由でのみ触ること。
 */
public class FabricEnergyStorage implements IEnergyStorage {

    public final EnergyStorage storage;

    /**
     * side = null で取得したストレージかどうか。
     * 面ごとにストレージを持つ機械は面なしのアクセスでNPEを投げるため、この場合のみ例外を握り潰す。
     */
    public final boolean nullSide;

    public FabricEnergyStorage(EnergyStorage storage) {
        this(storage, false);
    }

    public FabricEnergyStorage(EnergyStorage storage, boolean nullSide) {
        this.storage = storage;
        this.nullSide = nullSide;
    }

    public EnergyStorage getRaw() {
        return storage;
    }

    @Override
    public long getEnergyStored() {
        try {
            return storage.getAmount();
        } catch (RuntimeException e) {
            if (!nullSide) throw e;
            return 0;
        }
    }

    @Override
    public long getCapacityEnergy() {
        try {
            return storage.getCapacity();
        } catch (RuntimeException e) {
            if (!nullSide) throw e;
            return 0;
        }
    }

    @Override
    public long insertEnergy(long amount, boolean simulate) {
        if (amount <= 0) return 0;

        try (Transaction transaction = openTransaction()) {
            long inserted = storage.insert(amount, transaction);
            if (!simulate) transaction.commit();
            return inserted;
        } catch (RuntimeException e) {
            if (!nullSide) throw e;
            return 0;
        }
    }

    @Override
    public long extractEnergy(long amount, boolean simulate) {
        if (amount <= 0) return 0;

        try (Transaction transaction = openTransaction()) {
            long extracted = storage.extract(amount, transaction);
            if (!simulate) transaction.commit();
            return extracted;
        } catch (RuntimeException e) {
            if (!nullSide) throw e;
            return 0;
        }
    }

    /**
     * 既にトランザクションが開かれている場合、{@code openOuter()} は例外になるのでネストして開く。
     */
    private static Transaction openTransaction() {
        if (Transaction.isOpen())
            return Transaction.openNested(Transaction.getCurrentUnsafe());

        return Transaction.openOuter();
    }

    @Override
    public boolean canInsertEnergy() {
        try {
            return storage.supportsInsertion();
        } catch (RuntimeException e) {
            if (!nullSide) throw e;
            return true;
        }
    }

    @Override
    public boolean canExtractEnergy() {
        try {
            return storage.supportsExtraction();
        } catch (RuntimeException e) {
            if (!nullSide) throw e;
            return true;
        }
    }

    @Override
    public long getMaxInputEnergy() {
        if (storage instanceof SimpleEnergyStorage)
            return ((SimpleEnergyStorage) storage).maxInsert;

        return IEnergyStorage.super.getMaxInputEnergy();
    }

    @Override
    public long getMaxOutputEnergy() {
        if (storage instanceof SimpleEnergyStorage)
            return ((SimpleEnergyStorage) storage).maxExtract;

        return IEnergyStorage.super.getMaxOutputEnergy();
    }
}
