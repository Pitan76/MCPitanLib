package net.pitan76.mcpitanlib.api.transfer.energy.v1.neoforge;

import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;

public class NeoForgeEnergyStorage implements IEnergyStorage {

    public final EnergyHandler handler;

    public NeoForgeEnergyStorage(EnergyHandler handler) {
        this.handler = handler;
    }

    public EnergyHandler getRaw() {
        return handler;
    }

    @Override
    public long getEnergyStored() {
        return handler.getAmountAsLong();
    }

    @Override
    public long getCapacityEnergy() {
        return handler.getCapacityAsLong();
    }

    @Override
    public long insertEnergy(long amount, boolean simulate) {
        if (amount <= 0) return 0;

        try (Transaction transaction = openTransaction()) {
            long inserted = handler.insert(toInt(amount), transaction);
            if (!simulate) transaction.commit();
            return inserted;
        }
    }

    @Override
    public long extractEnergy(long amount, boolean simulate) {
        if (amount <= 0) return 0;

        try (Transaction transaction = openTransaction()) {
            long extracted = handler.extract(toInt(amount), transaction);
            if (!simulate) transaction.commit();
            return extracted;
        }
    }

    private static Transaction openTransaction() {
        TransactionContext current = Transaction.getCurrentOpenedTransaction();
        if (current != null) return Transaction.open(current);

        return Transaction.openRoot();
    }

    /**
     * NeoForgeのEnergyHandlerには可否の申告が無いためシミュレーションで判定する。
     */
    @Override
    public boolean canInsertEnergy() {
        return isFullEnergy() || insertEnergy(1, true) > 0;
    }

    @Override
    public boolean canExtractEnergy() {
        return isEmptyEnergy() || extractEnergy(1, true) > 0;
    }

    @Override
    public long getMaxInputEnergy() {
        return insertEnergy(Integer.MAX_VALUE, true);
    }

    @Override
    public long getMaxOutputEnergy() {
        return extractEnergy(Integer.MAX_VALUE, true);
    }

    /**
     * NeoForgeのEnergyHandlerはintで扱うため、範囲を丸める。
     */
    private static int toInt(long amount) {
        if (amount > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (amount < 0) return 0;

        return (int) amount;
    }
}
