package net.pitan76.mcpitanlib.api.transfer.energy.v1.neoforge;

import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;

/**
 * MCPitanLibのIEnergyStorageをNeoForgeのIEnergyStorageとして公開するためのラッパー。
 * <p>
 * {@link NeoForgeEnergyStorage} でない独自実装を登録した場合のフォールバック。
 */
public class NeoForgeWrappedEnergyHandler implements net.neoforged.neoforge.energy.IEnergyStorage {

    public final IEnergyStorage storage;

    public NeoForgeWrappedEnergyHandler(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public int receiveEnergy(int maxAmount, boolean simulate) {
        if (!storage.supportsInsertion()) return 0;

        return (int) storage.insert(maxAmount, simulate);
    }

    @Override
    public int extractEnergy(int maxAmount, boolean simulate) {
        if (!storage.supportsExtraction()) return 0;

        return (int) storage.extract(maxAmount, simulate);
    }

    @Override
    public int getEnergyStored() {
        return (int) storage.getAmount();
    }

    @Override
    public int getMaxEnergyStored() {
        return (int) storage.getCapacity();
    }

    @Override
    public boolean canExtract() {
        return storage.supportsExtraction();
    }

    @Override
    public boolean canReceive() {
        return storage.supportsInsertion();
    }
}
