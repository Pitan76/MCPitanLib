package net.pitan76.mcpitanlib.api.transfer.energy.v1.neoforge;

import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;

/**
 * NeoForgeのEnergyHandlerをIEnergyStorageとして扱うためのラッパー。
 */
public class NeoForgeEnergyStorage implements IEnergyStorage {

    public final EnergyHandler handler;

    public NeoForgeEnergyStorage(EnergyHandler handler) {
        this.handler = handler;
    }

    public EnergyHandler getRaw() {
        return handler;
    }

    @Override
    public long getAmount() {
        return handler.getAmountAsLong();
    }

    @Override
    public long getCapacity() {
        return handler.getCapacityAsLong();
    }

    @Override
    public long insert(long maxAmount, boolean simulate) {
        try (Transaction transaction = Transaction.open(null)) {
            long inserted = handler.insert(toInt(maxAmount), transaction);
            if (!simulate) transaction.commit();
            return inserted;
        }
    }

    @Override
    public long extract(long maxAmount, boolean simulate) {
        try (Transaction transaction = Transaction.open(null)) {
            long extracted = handler.extract(toInt(maxAmount), transaction);
            if (!simulate) transaction.commit();
            return extracted;
        }
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
