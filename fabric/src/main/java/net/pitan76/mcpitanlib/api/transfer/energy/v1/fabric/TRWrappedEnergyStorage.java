package net.pitan76.mcpitanlib.api.transfer.energy.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import team.reborn.energy.api.EnergyStorage;

/**
 * ケーブルは毎tick「入れてみて駄目なら中断する」トランザクションを開くため、
 * 中断を無視して即時確定すると挿入が残り、発電機を繋いでいないのに満充電になる。
 * {@link SnapshotParticipant} で残量を退避し、中断時は元に戻す。
 */
public class TRWrappedEnergyStorage extends SnapshotParticipant<Long> implements EnergyStorage {

    public final IEnergyStorage storage;

    public TRWrappedEnergyStorage(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        if (maxAmount <= 0 || !storage.canInsertEnergy()) return 0;

        updateSnapshots(transaction);
        return storage.insertEnergy(maxAmount, false);
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        if (maxAmount <= 0 || !storage.canExtractEnergy()) return 0;

        updateSnapshots(transaction);
        return storage.extractEnergy(maxAmount, false);
    }

    @Override
    protected Long createSnapshot() {
        return storage.getEnergyStored();
    }

    @Override
    protected void readSnapshot(Long snapshot) {
        long current = storage.getEnergyStored();
        if (current == snapshot) return;

        if (storage.supportsSetEnergyStored()) {
            storage.setEnergyStored(snapshot);
            return;
        }

        // setEnergyStoredが使えない場合は差分を打ち消す
        if (current > snapshot) {
            storage.extractEnergy(current - snapshot, false);
        } else {
            storage.insertEnergy(snapshot - current, false);
        }
    }

    @Override
    public long getAmount() {
        return storage.getEnergyStored();
    }

    @Override
    public long getCapacity() {
        return storage.getCapacityEnergy();
    }

    @Override
    public boolean supportsInsertion() {
        return storage.canInsertEnergy();
    }

    @Override
    public boolean supportsExtraction() {
        return storage.canExtractEnergy();
    }
}
