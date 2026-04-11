package net.pitan76.mcpitanlib.api.util.block.entity;

import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.recipe.MatchGetter;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.recipe.input.SingleStackRecipeInputUtil;

public class FurnaceUtil {
    public static int getDefaultCookTime() {
        return AbstractFurnaceBlockEntity.BURN_TIME_STANDARD;
    }

    public static boolean canUseAsFuel(net.pitan76.mcpitanlib.midohra.item.ItemStack stack, Level world) {
        return canUseAsFuel(stack.toMinecraft(), world);
    }

    public static boolean canUseAsFuel(ItemStack stack, Level world) {
        return world.fuelValues().isFuel(stack);
    }

    public static void tick(Level world, BlockPos pos, AbstractFurnaceBlockEntity blockEntity) {
        AbstractFurnaceBlockEntity.serverTick((ServerLevel) world, pos, WorldUtil.getBlockState(world, pos), blockEntity);
    }

    public static int getCookTime(Level world, AbstractFurnaceBlockEntity furnace, MatchGetter<SingleRecipeInput, ? extends AbstractCookingRecipe> matchGetter) {
        return getCookTime(world, furnace.getItem(0), matchGetter);
    }

    public static int getCookTime(Level world, ItemStack stack, MatchGetter<SingleRecipeInput, ? extends AbstractCookingRecipe> matchGetter) {
        CompatRecipeInput<SingleRecipeInput> input = (CompatRecipeInput<SingleRecipeInput>) SingleStackRecipeInputUtil.create(stack);

        matchGetter.getFirstMatch(input, world);

        return matchGetter.getFirstMatch(input, world).map(
                (recipe) -> (recipe.getRecipe()).cookingTime()).orElse(200);
    }
}
