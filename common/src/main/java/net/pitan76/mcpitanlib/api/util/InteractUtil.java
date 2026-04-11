package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnEntityEvent;
import net.pitan76.mcpitanlib.api.item.args.UseActionArgs;
import net.pitan76.mcpitanlib.api.item.consume.CompatUseAction;

public class InteractUtil {

    public static StackActionResult useItem(Item item, ItemUseEvent event) {
        CompatActionResult result = CompatActionResult.create(item.use(event.getWorld(), event.user.getEntity(), event.getHand()));
        return StackActionResult.create(result, event.getStack());
    }

    public static CompatActionResult useItemOnBlock(Item item, UseOnContext context) {
        return CompatActionResult.create(item.useOn(context));
    }

    public static CompatActionResult useItemOnBlock(Item item, ItemUseOnBlockEvent event) {
        return useItemOnBlock(item, event.toIUC());
    }

    public static CompatActionResult useItemOnEntity(Item item, ItemUseOnEntityEvent event) {
        return CompatActionResult.create(item.interactLivingEntity(event.getStack(), event.getUser().getEntity(), event.getEntity(), event.getHand()));
    }

    public static CompatUseAction getUseAction(Item item, UseActionArgs args) {
        return CompatUseAction.of(item.getUseAnimation(args.stack));
    }

    public static CompatUseAction getUseAction(Item item, ItemStack stack) {
        return CompatUseAction.of(item.getUseAnimation(stack));
    }

    public static CompatActionResult useBlock(BlockState state, Level world, Player player, BlockHitResult hitResult) {
        return BlockStateUtil.onUse(state, world, player, hitResult);
    }

    public static CompatActionResult useBlock(BlockState state, Level world, Player player, Direction dir, BlockPos blockPos) {
        return BlockStateUtil.onUse(state, world, player, dir, blockPos);
    }

    public static boolean onStoppingUsing(Item item, ItemStack stack, Level world, Player player, int remainingUseTicks) {
        return item.releaseUsing(stack, world, player.getEntity(), remainingUseTicks);
    }
}
