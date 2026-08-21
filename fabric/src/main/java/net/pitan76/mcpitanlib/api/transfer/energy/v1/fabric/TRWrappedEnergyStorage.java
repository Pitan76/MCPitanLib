package net.pitan76.mcpitanlib.api.transfer.energy.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import team.reborn.energy.api.EnergyStorage;

/**
 * IEnergyStorage を Team Reborn Energy の EnergyStorage として公開するためのラッパー。
 * <p>
 * {@link FabricEnergyStorage} でない独自実装を登録した場合のフォールバック。
 * IEnergyStorage 側がトランザクションを持たないため、ここでの操作は即時確定になる。
 * トランザクションを正しく扱いたい場合は
 * {@code EnergyStorageUtil#create} が返すストレージを登録すること。
 * <p>
 * このクラスは Team Reborn Energy が導入されている場合のみロードされる。
 */
public class TRWrappedEnergyStorage implements EnergyStorage {

    public final IEnergyStorage storage;

    public TRWrappedEnergyStorage(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        return storage.insert(maxAmount, false);
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        return storage.extract(maxAmount, false);
    }

    @Override
    public long getAmount() {
        return storage.getAmount();
    }

    @Override
    public long getCapacity() {
        return storage.getCapacity();
    }

    @Override
    public boolean supportsInsertion() {
        return storage.supportsInsertion();
    }

    @Override
    public boolean supportsExtraction() {
        return storage.supportsExtraction();
    }
}
