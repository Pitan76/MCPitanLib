package net.pitan76.mcpitanlib.api.transfer.energy.v1.forge;

import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;

/**
 * ForgeのIEnergyStorageをMCPitanLibのIEnergyStorageとして扱うためのラッパー。
 */
public class ForgeEnergyStorage implements IEnergyStorage {

    public final net.minecraftforge.energy.IEnergyStorage handler;

    public ForgeEnergyStorage(net.minecraftforge.energy.IEnergyStorage handler) {
        this.handler = handler;
    }

    public net.minecraftforge.energy.IEnergyStorage getRaw() {
        return handler;
    }

    @Override
    public long getEnergyStored() {
        return handler.getEnergyStored();
    }

    @Override
    public long getCapacityEnergy() {
        return handler.getMaxEnergyStored();
    }

    @Override
    public long insertEnergy(long amount, boolean simulate) {
        if (amount <= 0) return 0;

        return handler.receiveEnergy(toInt(amount), simulate);
    }

    @Override
    public long extractEnergy(long amount, boolean simulate) {
        if (amount <= 0) return 0;

        return handler.extractEnergy(toInt(amount), simulate);
    }

    @Override
    public boolean canInsertEnergy() {
        return handler.canReceive();
    }

    @Override
    public boolean canExtractEnergy() {
        return handler.canExtract();
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
     * ForgeのIEnergyStorageはintで扱うため、範囲を丸める。
     */
    private static int toInt(long amount) {
        if (amount > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (amount < 0) return 0;

        return (int) amount;
    }
}
