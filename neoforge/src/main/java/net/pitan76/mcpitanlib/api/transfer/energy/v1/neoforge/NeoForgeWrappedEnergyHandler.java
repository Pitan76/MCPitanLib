package net.pitan76.mcpitanlib.api.transfer.energy.v1.neoforge;

import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;

/**
 * ケーブルは毎tick「入れてみて駄目なら中断する」トランザクションを開くため、
 * 中断を無視して即時確定すると挿入が残り、発電機を繋いでいないのに満充電になる。
 * {@link SnapshotJournal} で残量を退避し、中断時は元に戻す。
 */
public class NeoForgeWrappedEnergyHandler implements EnergyHandler {

    public final IEnergyStorage storage;

    private final SnapshotJournal<Long> journal = new SnapshotJournal<Long>() {
        @Override
        protected Long createSnapshot() {
            return storage.getEnergyStored();
        }

        @Override
        protected void revertToSnapshot(Long snapshot) {
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
    };

    public NeoForgeWrappedEnergyHandler(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public long getAmountAsLong() {
        return storage.getEnergyStored();
    }

    @Override
    public long getCapacityAsLong() {
        return storage.getCapacityEnergy();
    }

    @Override
    public int insert(int maxAmount, TransactionContext transaction) {
        if (maxAmount <= 0 || !storage.canInsertEnergy()) return 0;

        journal.updateSnapshots(transaction);
        return (int) storage.insertEnergy(maxAmount, false);
    }

    @Override
    public int extract(int maxAmount, TransactionContext transaction) {
        if (maxAmount <= 0 || !storage.canExtractEnergy()) return 0;

        journal.updateSnapshots(transaction);
        return (int) storage.extractEnergy(maxAmount, false);
    }
}
