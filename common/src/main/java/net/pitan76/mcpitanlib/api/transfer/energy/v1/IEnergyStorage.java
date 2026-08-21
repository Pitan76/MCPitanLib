package net.pitan76.mcpitanlib.api.transfer.energy.v1;

/**
 * プラットフォーム間で共通のエネルギーストレージ。
 * Fabricでは Team Reborn Energy、NeoForgeでは Capability の実装に橋渡しされる。
 * <p>
 * Fabricでは Team Reborn Energy が導入されていない場合があるため、
 * このインターフェースを取得する側は null を許容すること。
 */
public interface IEnergyStorage {
    long getAmount();

    long getCapacity();

    /**
     * エネルギーを挿入する。
     * @param maxAmount 挿入する最大量
     * @param simulate trueの場合は実際には挿入しない
     * @return 実際に挿入された量
     */
    long insert(long maxAmount, boolean simulate);

    /**
     * エネルギーを取り出す。
     * @param maxAmount 取り出す最大量
     * @param simulate trueの場合は実際には取り出さない
     * @return 実際に取り出された量
     */
    long extract(long maxAmount, boolean simulate);

    default long insert(long maxAmount) {
        return insert(maxAmount, false);
    }

    default long extract(long maxAmount) {
        return extract(maxAmount, false);
    }

    default boolean supportsInsertion() {
        return true;
    }

    default boolean supportsExtraction() {
        return true;
    }

    default boolean isEmpty() {
        return getAmount() <= 0;
    }

    default boolean isFull() {
        return getAmount() >= getCapacity();
    }

    default long getSpace() {
        return getCapacity() - getAmount();
    }

    default boolean canInsert(long amount) {
        return insert(amount, true) == amount;
    }

    default boolean canExtract(long amount) {
        return extract(amount, true) == amount;
    }
}
