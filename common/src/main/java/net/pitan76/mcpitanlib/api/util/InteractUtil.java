package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnEntityEvent;

public class InteractUtil {

    public static TypedActionResult<ItemStack> useItem(Item item, ItemUseEvent event) {
        return item.use(event.getWorld(), event.user.getEntity(), event.getHand());
    }

    public static ActionResult useItemOnBlock(Item item, ItemUsageContext context) {
        return item.useOnBlock(context);
    }

    public static ActionResult useItemOnBlock(Item item, ItemUseOnBlockEvent event) {
        return useItemOnBlock(item, event.toIUC());
    }

    public static ActionResult useItemOnEntity(Item item, ItemUseOnEntityEvent event) {
        return item.useOnEntity(event.getStack(), event.getUser().getEntity(), event.getEntity(), event.getHand());
    }

    public static ActionResult useBlock(BlockState state, World world, Player player, BlockHitResult hitResult) {
        return BlockStateUtil.onUse(state, world, player, hitResult);
    }

    public static ActionResult useBlock(BlockState state, World world, Player player, Direction dir, BlockPos blockPos) {
        return BlockStateUtil.onUse(state, world, player, dir, blockPos);
    }
}
