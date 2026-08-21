package net.pitan76.mcpitanlib.api.transfer.item.v1.neoforge;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

public class ItemTransferUtilImpl {

    public static int insertTo(World world, BlockPos pos, @Nullable Direction side, ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        IItemHandler handler = world.getCapability(Capabilities.ItemHandler.BLOCK, pos, side);
        if (handler == null) return 0;

        // 既存のスロットに寄せてから空きスロットを使うので、バニラのホッパーに近い挙動になる
        ItemStack remainder = ItemHandlerHelper.insertItemStacked(handler, stack, simulate);

        return stack.getCount() - remainder.getCount();
    }
}
