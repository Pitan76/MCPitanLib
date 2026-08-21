package net.pitan76.mcpitanlib.api.transfer.item.v1.neoforge;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.Nullable;

public class ItemTransferUtilImpl {

    public static int insertTo(World world, BlockPos pos, @Nullable Direction side, ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        ResourceHandler<ItemResource> handler = world.getCapability(Capabilities.Item.BLOCK, pos, side);
        if (handler == null) return 0;

        try (Transaction transaction = Transaction.open(null)) {
            // 既存のスロットに寄せてから空きスロットを使うので、バニラのホッパーに近い挙動になる
            int inserted = ResourceHandlerUtil.insertStacking(handler, ItemResource.of(stack), stack.getCount(), transaction);
            if (!simulate) transaction.commit();

            return inserted;
        }
    }
}
