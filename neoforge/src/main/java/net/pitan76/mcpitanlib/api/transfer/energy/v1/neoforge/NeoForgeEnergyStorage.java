package net.pitan76.mcpitanlib.api.transfer.energy.v1.neoforge;

import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;

/**
 * NeoForgeのIEnergyStorageをMCPitanLibのIEnergyStorageとして扱うためのラッパー。
 */
public class NeoForgeEnergyStorage implements IEnergyStorage {

    public final net.neoforged.neoforge.energy.IEnergyStorage handler;

    public NeoForgeEnergyStorage(net.neoforged.neoforge.energy.IEnergyStorage handler) {
        this.handler = handler;
    }

    public net.neoforged.neoforge.energy.IEnergyStorage getRaw() {
        return handler;
    }

    @Override
    public long getAmount() {
        return handler.getEnergyStored();
    }

    @Override
    public long getCapacity() {
        return handler.getMaxEnergyStored();
    }

    @Override
    public long insert(long maxAmount, boolean simulate) {
        return handler.receiveEnergy(toInt(maxAmount), simulate);
    }

    @Override
    public long extract(long maxAmount, boolean simulate) {
        return handler.extractEnergy(toInt(maxAmount), simulate);
    }

    @Override
    public boolean supportsInsertion() {
        return handler.canReceive();
    }

    @Override
    public boolean supportsExtraction() {
        return handler.canExtract();
    }

    /**
     * NeoForgeのIEnergyStorageはintで扱うため、範囲を丸める。
     */
    private static int toInt(long amount) {
        if (amount > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (amount < 0) return 0;

        return (int) amount;
    }
}
