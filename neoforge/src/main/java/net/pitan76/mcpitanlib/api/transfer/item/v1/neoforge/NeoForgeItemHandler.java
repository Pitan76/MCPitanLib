package net.pitan76.mcpitanlib.api.transfer.item.v1.neoforge;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.pitan76.mcpitanlib.api.transfer.item.v1.IItemHandler;

public class NeoForgeItemHandler implements IItemHandler {

    public final ResourceHandler<ItemResource> handler;

    public NeoForgeItemHandler(ResourceHandler<ItemResource> handler) {
        this.handler = handler;
    }

    public ResourceHandler<ItemResource> getRaw() {
        return handler;
    }

    @Override
    public int insert(ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        try (Transaction transaction = Transaction.open(null)) {
            // 既存のスロットに寄せてから空きスロットを使う (バニラのホッパーに近い挙動)
            int inserted = ResourceHandlerUtil.insertStacking(handler, ItemResource.of(stack), stack.getCount(), transaction);
            if (!simulate) transaction.commit();

            return inserted;
        }
    }

    @Override
    public int extract(ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        try (Transaction transaction = Transaction.open(null)) {
            int extracted = handler.extract(ItemResource.of(stack), stack.getCount(), transaction);
            if (!simulate) transaction.commit();

            return extracted;
        }
    }
}
