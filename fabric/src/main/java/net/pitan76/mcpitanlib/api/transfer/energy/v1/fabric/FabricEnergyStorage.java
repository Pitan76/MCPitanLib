package net.pitan76.mcpitanlib.api.transfer.energy.v1.fabric;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import team.reborn.energy.api.EnergyStorage;

/**
 * Team Reborn Energy の EnergyStorage を IEnergyStorage として扱うためのラッパー。
 * <p>
 * このクラスは Team Reborn Energy が導入されている場合のみロードされる。
 * 直接参照するとクラスロードで落ちるため、{@link TREnergySupport} 経由でのみ触ること。
 */
public class FabricEnergyStorage implements IEnergyStorage {

    public final EnergyStorage storage;

    public FabricEnergyStorage(EnergyStorage storage) {
        this.storage = storage;
    }

    public EnergyStorage getRaw() {
        return storage;
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
    public long insert(long maxAmount, boolean simulate) {
        try (Transaction transaction = Transaction.openOuter()) {
            long inserted = storage.insert(maxAmount, transaction);
            if (!simulate) transaction.commit();
            return inserted;
        }
    }

    @Override
    public long extract(long maxAmount, boolean simulate) {
        try (Transaction transaction = Transaction.openOuter()) {
            long extracted = storage.extract(maxAmount, transaction);
            if (!simulate) transaction.commit();
            return extracted;
        }
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
