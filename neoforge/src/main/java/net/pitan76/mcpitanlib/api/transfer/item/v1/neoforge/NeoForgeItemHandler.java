package net.pitan76.mcpitanlib.api.transfer.item.v1.neoforge;

import net.minecraft.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;

public class NeoForgeItemHandler implements net.pitan76.mcpitanlib.api.transfer.item.v1.IItemHandler {

    public final IItemHandler handler;

    public NeoForgeItemHandler(IItemHandler handler) {
        this.handler = handler;
    }

    public IItemHandler getRaw() {
        return handler;
    }

    @Override
    public int insert(ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        // 既存のスロットに寄せてから空きスロットを使う (バニラのホッパーに近い挙動)
        ItemStack remainder = ItemHandlerHelper.insertItemStacked(handler, stack, simulate);

        return stack.getCount() - remainder.getCount();
    }

    @Override
    public int extract(ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        // 旧APIはスロット単位でしか取り出せないので、一致するスロットを順に舐める
        int remaining = stack.getCount();
        int extracted = 0;

        for (int i = 0; i < handler.getSlots() && remaining > 0; i++) {
            ItemStack inSlot = handler.getStackInSlot(i);
            if (inSlot.isEmpty() || !ItemStackUtil.areItemsEqual(inSlot, stack) || !ItemStackUtil.areNbtOrComponentEqual(inSlot, stack)) continue;

            ItemStack got = handler.extractItem(i, remaining, simulate);
            extracted += got.getCount();
            remaining -= got.getCount();
        }

        return extracted;
    }
}
