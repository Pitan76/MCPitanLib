package net.pitan76.mcpitanlib.api.util.block.entity;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.recipe.MatchGetter;
import net.pitan76.mcpitanlib.api.recipe.input.CompatRecipeInput;
import net.pitan76.mcpitanlib.api.util.recipe.input.SingleStackRecipeInputUtil;

public class FurnaceUtil {
    public static int getDefaultCookTime() {
        return 200;
    }

    public static boolean canUseAsFuel(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return canUseAsFuel(stack.toMinecraft());
    }

    public static boolean canUseAsFuel(ItemStack stack) {
        return AbstractFurnaceBlockEntity.canUseAsFuel(stack);
    }

    public static void tick(World world, BlockPos pos, AbstractFurnaceBlockEntity blockEntity) {
        blockEntity.tick();
    }

    public static int getCookTime(World world, AbstractFurnaceBlockEntity furnace, MatchGetter<Inventory, ? extends AbstractCookingRecipe> matchGetter) {
        return getCookTime(world, furnace.getStack(0), matchGetter);
    }

    public static int getCookTime(World world, ItemStack stack, MatchGetter<Inventory, ? extends AbstractCookingRecipe> matchGetter) {
        CompatRecipeInput<Inventory> input = (CompatRecipeInput<Inventory>) SingleStackRecipeInputUtil.create(stack);

        matchGetter.getFirstMatch(input, world);

        return matchGetter.getFirstMatch(input, world).map((entry) -> ((AbstractCookingRecipe)entry.getRecipe()).getCookTime()).orElse(200);
    }
}
