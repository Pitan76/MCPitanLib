package net.pitan76.mcpitanlib.api.transfer.energy.v1.neoforge;

import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;

/**
 * {@link NeoForgeEnergyStorage} でない独自実装を登録した場合のフォールバック。
 */
public class NeoForgeWrappedEnergyHandler implements net.neoforged.neoforge.energy.IEnergyStorage {

    public final IEnergyStorage storage;

    public NeoForgeWrappedEnergyHandler(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public int receiveEnergy(int maxAmount, boolean simulate) {
        if (!storage.canInsertEnergy()) return 0;

        return (int) storage.insertEnergy(maxAmount, simulate);
    }

    @Override
    public int extractEnergy(int maxAmount, boolean simulate) {
        if (!storage.canExtractEnergy()) return 0;

        return (int) storage.extractEnergy(maxAmount, simulate);
    }

    @Override
    public int getEnergyStored() {
        return (int) storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return (int) storage.getCapacityEnergy();
    }

    @Override
    public boolean canExtract() {
        return storage.canExtractEnergy();
    }

    @Override
    public boolean canReceive() {
        return storage.canInsertEnergy();
    }
}
