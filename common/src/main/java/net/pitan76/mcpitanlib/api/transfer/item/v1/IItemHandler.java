package net.pitan76.mcpitanlib.api.transfer.item.v1;

import net.minecraft.item.ItemStack;

/**
 * アイテムの出し入れだけを行う薄いビュー。
 * <p>
 * {@link ItemLookup} が他MODのインベントリを返すときの型。スロットの列挙はできない。
 */
public interface IItemHandler {
    /**
     * @param stack 挿入するアイテム (種類と最大数を兼ねる)
     * @return 挿入された数
     */
    int insert(ItemStack stack, boolean simulate);

    /**
     * @param stack 取り出すアイテム (種類と最大数を兼ねる)
     * @return 取り出された数
     */
    int extract(ItemStack stack, boolean simulate);

    default boolean supportsInsertion() {
        return true;
    }

    default boolean supportsExtraction() {
        return true;
    }

    default int insert(ItemStack stack) {
        return insert(stack, false);
    }

    default int extract(ItemStack stack) {
        return extract(stack, false);
    }

    default boolean canInsert(ItemStack stack) {
        return insert(stack, true) >= stack.getCount();
    }

    default boolean canExtract(ItemStack stack) {
        return extract(stack, true) >= stack.getCount();
    }

    default int insert(net.pitan76.mcpitanlib.midohra.item.ItemStack stack, boolean simulate) {
        return insert(stack.toMinecraft(), simulate);
    }

    default int extract(net.pitan76.mcpitanlib.midohra.item.ItemStack stack, boolean simulate) {
        return extract(stack.toMinecraft(), simulate);
    }
}
