package net.pitan76.mcpitanlib.api.util.recipe;

import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.util.LoggerUtil;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.CraftingRecipe;
import net.pitan76.mcpitanlib.midohra.recipe.ShapedRecipe;
import net.pitan76.mcpitanlib.midohra.recipe.ShapelessRecipe;
import net.pitan76.mcpitanlib.midohra.recipe.entry.RecipeEntry;
import net.pitan76.mcpitanlib.midohra.recipe.input.CraftingRecipeInputOrInventory;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;
import net.pitan76.mcpitanlib.midohra.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.stream.Collectors;

public class CraftingRecipeUtil {
    @Nullable
    public static ItemStack getOutput(CraftingRecipe recipe, CraftingRecipeInputOrInventory input, World world) {
        if (recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe) {
            return recipe.getOutputMidohra(input, world);
        } else {
            try {
                return recipe.getOutputMidohra(input, world);
            } catch (ArrayIndexOutOfBoundsException e) {
                String id = "Unknown recipe";
                for (RecipeEntry entry : RecipeUtil.getRecipeEntries(world)) {
                    if (entry.getRawRecipe() == recipe.getRaw()) {
                        id = entry.getId().toString();
                        break;
                    }
                }

                LoggerUtil.getLogger().error("{}: {}", e.getMessage(), id);
                return null;
            } catch (Exception ignored) {
                return null;
            }
        }
    }

    public static net.minecraft.world.item.ItemStack getOutputRaw(CraftingRecipe recipe, CraftingRecipeInputOrInventory input, Level world) {
        return getOutput(recipe, input, World.of(world)).toMinecraft();
    }

    public static Collection<CraftingRecipe> getCraftingRecipes(ServerWorld world) {
        try {
            return RecipeUtil.getCraftingRecipes(world).stream().map(
                recipe -> {
                    if (recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe)
                        return recipe;

                    if (recipe.getRaw() instanceof net.minecraft.world.item.crafting.ShapedRecipe)
                        return ShapedRecipe.of((net.minecraft.world.item.crafting.ShapedRecipe) recipe.getRaw());

                    if (recipe.getRaw() instanceof net.minecraft.world.item.crafting.ShapelessRecipe)
                        return ShapelessRecipe.of((net.minecraft.world.item.crafting.ShapelessRecipe) recipe.getRaw());

                    return recipe;
                }
            ).collect(Collectors.toList());
        } catch (Exception e) {
            return RecipeUtil.getCraftingRecipes(world);
        }
    }
}
