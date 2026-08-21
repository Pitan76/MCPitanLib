package net.pitan76.mcpitanlib.api.transfer.item.v1.forge;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

public class ItemTransferUtilImpl {

    public static int insertTo(World world, BlockPos pos, @Nullable Direction side, ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null) return 0;

        IItemHandler handler = blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, side).resolve().orElse(null);
        if (handler == null) return 0;

        // 既存のスロットに寄せてから空きスロットを使うので、バニラのホッパーに近い挙動になる
        ItemStack remainder = ItemHandlerHelper.insertItemStacked(handler, stack, simulate);

        return stack.getCount() - remainder.getCount();
    }
}
