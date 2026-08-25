package net.pitan76.mcpitanlib.api.transfer.energy.v1;

import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.util.nbt.NbtRWUtil;

/**
 * プラットフォーム間で共通のエネルギーストレージ。
 */
public interface IEnergyStorage {

    long getEnergyStored();

    long getCapacityEnergy();

    long insertEnergy(long amount, boolean simulate);

    long extractEnergy(long amount, boolean simulate);

    default boolean canInsertEnergy() {
        return true;
    }

    default boolean canExtractEnergy() {
        return true;
    }

    default long getMaxInputEnergy() {
        if (!canInsertEnergy()) return 0;

        return insertEnergy(Long.MAX_VALUE, true);
    }

    default long getMaxOutputEnergy() {
        if (!canExtractEnergy()) return 0;

        return extractEnergy(Long.MAX_VALUE, true);
    }

    default long getInsertableEnergy() {
        if (!canInsertEnergy()) return 0;

        return insertEnergy(getMaxInputEnergy(), true);
    }

    default long getExtractableEnergy() {
        if (!canExtractEnergy()) return 0;

        return extractEnergy(getMaxOutputEnergy(), true);
    }

    default boolean supportsSetEnergyStored() {
        return false;
    }

    /**
     * @throws UnsupportedOperationException {@link #supportsSetEnergyStored()} がfalseの場合
     */
    default void setEnergyStored(long energy) {
        throw new UnsupportedOperationException("setEnergyStored is not supported by " + getClass().getName());
    }

    /**
     * ケーブル等の導管かどうか。
     */
    default boolean isEnergyConduit() {
        return false;
    }

    /**
     * @param check trueなら {@link #canInsertEnergy()} を尊重する
     */
    default long insertEnergy(long amount, boolean simulate, boolean check) {
        if (check && !canInsertEnergy()) return 0;

        return insertEnergy(amount, simulate);
    }

    /**
     * @param check trueなら {@link #canExtractEnergy()} を尊重する
     */
    default long extractEnergy(long amount, boolean simulate, boolean check) {
        if (check && !canExtractEnergy()) return 0;

        return extractEnergy(amount, simulate);
    }

    default long insertEnergy(long amount) {
        return insertEnergy(amount, false);
    }

    default long extractEnergy(long amount) {
        return extractEnergy(amount, false);
    }

    default boolean isFullEnergy() {
        return getEnergyStored() >= getCapacityEnergy();
    }

    default boolean isEmptyEnergy() {
        return getEnergyStored() <= 0;
    }

    default boolean hasEnergy() {
        return !isEmptyEnergy();
    }

    default boolean isNotEmptyEnergy() {
        return !isEmptyEnergy();
    }

    default boolean isNotFullEnergy() {
        return !isFullEnergy();
    }

    default long getUsableCapacity() {
        return getCapacityEnergy() - getEnergyStored();
    }

    default boolean canInsertEnergyFully(long amount) {
        return insertEnergy(amount, true) == amount;
    }

    default boolean canExtractEnergyFully(long amount) {
        return extractEnergy(amount, true) == amount;
    }

    default boolean canInsertAnyEnergy() {
        return getInsertableEnergy() > 0;
    }

    default boolean canExtractAnyEnergy() {
        return getExtractableEnergy() > 0;
    }

    default void writeEnergyNbt(WriteNbtArgs args, String key) {
        NbtRWUtil.putLong(args, key, getEnergyStored());
    }

    default void writeEnergyNbt(WriteNbtArgs args) {
        writeEnergyNbt(args, "energy");
    }

}
