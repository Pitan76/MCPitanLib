package net.pitan76.mcpitanlib.api.util.block.entity;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.recipe.MatchGetter;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.api.util.recipe.input.SingleStackRecipeInputUtil;

public class FurnaceUtil {
    public static int getDefaultCookTime() {
        return AbstractFurnaceBlockEntity.DEFAULT_COOK_TIME;
    }

    public static boolean canUseAsFuel(net.pitan76.mcpitanlib.midohra.item.ItemStack stack, World world) {
        return canUseAsFuel(stack.toMinecraft(), world);
    }

    public static boolean canUseAsFuel(ItemStack stack, World world) {
        return world.getFuelRegistry().isFuel(stack);
    }

    public static void tick(World world, BlockPos pos, AbstractFurnaceBlockEntity blockEntity) {
        AbstractFurnaceBlockEntity.tick((ServerWorld) world, pos, WorldUtil.getBlockState(world, pos), blockEntity);
    }

    public static int getCookTime(World world, AbstractFurnaceBlockEntity furnace, MatchGetter<SingleStackRecipeInput, ? extends AbstractCookingRecipe> matchGetter) {
        return getCookTime(world, furnace.getStack(0), matchGetter);
    }

    public static int getCookTime(World world, ItemStack stack, MatchGetter<SingleStackRecipeInput, ? extends AbstractCookingRecipe> matchGetter) {
        CompatRecipeInput<SingleStackRecipeInput> input = (CompatRecipeInput<SingleStackRecipeInput>) SingleStackRecipeInputUtil.create(stack);

        matchGetter.getFirstMatch(input, world);

        return matchGetter.getFirstMatch(input, world).map(
                (recipe) -> (recipe.getRecipe()).getCookingTime()).orElse(200);
    }
}
