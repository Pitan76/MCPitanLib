package net.pitan76.mcpitanlib.api.transfer.energy.v1.neoforge;

import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;

/**
 * IEnergyStorageをNeoForgeのEnergyHandlerとして公開するためのラッパー。
 * <p>
 * {@link NeoForgeEnergyStorage} でない独自実装を登録した場合のフォールバック。
 * IEnergyStorage側がトランザクションを持たないため、ここでの操作は即時確定になる。
 * トランザクションを正しく扱いたい場合は
 * {@code EnergyStorageUtil#create} が返すストレージを登録すること。
 */
public class NeoForgeWrappedEnergyHandler implements EnergyHandler {

    public final IEnergyStorage storage;

    public NeoForgeWrappedEnergyHandler(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public long getAmountAsLong() {
        return storage.getAmount();
    }

    @Override
    public long getCapacityAsLong() {
        return storage.getCapacity();
    }

    @Override
    public int insert(int maxAmount, TransactionContext transaction) {
        if (!storage.supportsInsertion()) return 0;

        return (int) storage.insert(maxAmount, false);
    }

    @Override
    public int extract(int maxAmount, TransactionContext transaction) {
        if (!storage.supportsExtraction()) return 0;

        return (int) storage.extract(maxAmount, false);
    }
}
