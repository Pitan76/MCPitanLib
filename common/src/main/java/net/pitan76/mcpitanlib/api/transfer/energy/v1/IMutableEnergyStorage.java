package net.pitan76.mcpitanlib.api.transfer.energy.v1;

import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.util.nbt.NbtRWUtil;

/**
 * 残量を直接設定できるエネルギーストレージ
 */
public interface IMutableEnergyStorage extends IEnergyStorage {

    @Override
    void setEnergyStored(long energy);

    @Override
    long getMaxInputEnergy();

    @Override
    long getMaxOutputEnergy();

    @Override
    default boolean supportsSetEnergyStored() {
        return true;
    }

    @Override
    default long insertEnergy(long amount, boolean simulate) {
        if (amount <= 0 || !canInsertEnergy()) return 0;

        long inserted = Math.min(getUsableCapacity(), Math.min(getMaxInputEnergy(), amount));
        if (inserted <= 0) return 0;
        if (!simulate) setEnergyStored(getEnergyStored() + inserted);

        return inserted;
    }

    @Override
    default long extractEnergy(long amount, boolean simulate) {
        if (amount <= 0 || !canExtractEnergy()) return 0;

        long extracted = Math.min(getEnergyStored(), Math.min(getMaxOutputEnergy(), amount));
        if (extracted <= 0) return 0;
        if (!simulate) setEnergyStored(getEnergyStored() - extracted);

        return extracted;
    }

    /**
     * 自分で自分の残量を増やす。発電などの内部処理向けで、
     * {@link #canInsertEnergy()} と {@link #getMaxInputEnergy()} は無視する。
     */
    default void addEnergyStored(long amount) {
        addEnergyStored(amount, true);
    }

    /**
     * @param check trueなら容量を超える場合に何もしない
     */
    default void addEnergyStored(long amount, boolean check) {
        if (check && getEnergyStored() + amount > getCapacityEnergy()) return;

        setEnergyStored(getEnergyStored() + amount);
    }

    /**
     * 自分で自分の残量を減らす。消費などの内部処理向けで、
     * {@link #canExtractEnergy()} と {@link #getMaxOutputEnergy()} は無視する。
     */
    default long removeEnergyStored(long amount) {
        return removeEnergyStored(amount, true);
    }

    /**
     * @param check trueなら残量が足りない場合に何もしない
     */
    default long removeEnergyStored(long amount, boolean check) {
        if (check && getEnergyStored() < amount) return 0;

        long removed = Math.min(getEnergyStored(), amount);
        setEnergyStored(getEnergyStored() - removed);

        return removed;
    }

    /**
     * @return 指定量を全て消費できた場合のみtrue
     */
    default boolean useEnergy(long amount) {
        if (getEnergyStored() < amount) return false;

        setEnergyStored(getEnergyStored() - amount);
        return true;
    }

    default void readEnergyNbt(ReadNbtArgs args, String key) {
        setEnergyStored(NbtRWUtil.getLongOrDefault(args, key, 0));
    }

    default void readEnergyNbt(ReadNbtArgs args) {
        readEnergyNbt(args, "energy");
    }
}
