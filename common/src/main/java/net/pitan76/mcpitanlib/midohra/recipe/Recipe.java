package net.pitan76.mcpitanlib.midohra.recipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.pitan76.mcpitanlib.api.util.inventory.CompatInventory;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.input.RecipeInputOrInventory;
import net.pitan76.mcpitanlib.midohra.recipe.input.TypedRecipeInputOrInventory;
import net.pitan76.mcpitanlib.midohra.world.World;

import java.util.List;
import java.util.stream.Collectors;

public class Recipe {
    private final net.minecraft.recipe.Recipe<?> recipe;

    protected Recipe(net.minecraft.recipe.Recipe<?> recipe) {
        this.recipe = recipe;
    }

    public static Recipe of(net.minecraft.recipe.Recipe<?> recipe) {
        return new Recipe(recipe);
    }

    public static CraftingRecipe of(net.minecraft.recipe.CraftingRecipe recipe) {
        return new CraftingRecipe(recipe);
    }

    public static ShapedRecipe of(net.minecraft.recipe.ShapedRecipe recipe) {
        return new ShapedRecipe(recipe);
    }

    public static ShapelessRecipe of(net.minecraft.recipe.ShapelessRecipe recipe) {
        return new ShapelessRecipe(recipe);
    }

    public net.minecraft.recipe.Recipe<?> getRaw() {
        return recipe;
    }

    public net.minecraft.recipe.Recipe<?> toMinecraft() {
        return getRaw();
    }

    public RecipeSerializer<?> getRawSerializer() {
        return recipe.getSerializer();
    }

    public boolean matches(RecipeInputOrInventory input, World world) {
        if (input instanceof TypedRecipeInputOrInventory) {
            return matches(input, world);
        }

        return false;
    }

    public net.minecraft.item.ItemStack craft(RecipeInputOrInventory input, World world) {
        if (input instanceof TypedRecipeInputOrInventory) {
            return craft((TypedRecipeInputOrInventory) input, world);
        }

        return net.minecraft.item.ItemStack.EMPTY;
    }

    public ItemStack craftMidohra(RecipeInputOrInventory input, World world) {
        return ItemStack.of(craft(input, world));
    }

    public <T extends Inventory> boolean matches(TypedRecipeInputOrInventory<T> input, World world) {
        return ((net.minecraft.recipe.Recipe<T>)getRaw()).matches(input.getRecipeInput(), world.getRaw());
    }

    public <T extends Inventory> net.minecraft.item.ItemStack craft(TypedRecipeInputOrInventory<T> input, World world) {
        return ((net.minecraft.recipe.Recipe<T>)getRaw()).craft(input.getRecipeInput());
    }

    public <T extends Inventory> ItemStack craftMidohra(TypedRecipeInputOrInventory<T> input, World world) {
        return ItemStack.of(craft(input, world));
    }

    public String getGroup() {
        return getRaw().getGroup();
    }

    public RecipeType getType() {
        return RecipeType.of(getRaw().getType());
    }

    public List<Ingredient> getInputs() {
        return getRaw().getIngredients();
    }

    public ItemStack getOutput(World world) {
        List<net.pitan76.mcpitanlib.midohra.recipe.Ingredient> ingredients = getInputs().stream()
                .map(net.pitan76.mcpitanlib.midohra.recipe.Ingredient::of)
                .collect(Collectors.toList());

        CompatInventory inventory = new CompatInventory(ingredients.size());
        for (int i = 0; i < ingredients.size(); i++) {
            ItemStack stack = ingredients.get(i).getMatchingStacksAsMidohra()[0];
            inventory.callSetStack(i, stack);
        }

        return craftMidohra(RecipeInputOrInventory.of(inventory), world);
    }

    private ItemStack cachedOutput = null;

    public ItemStack getCachedOutput(World world) {
        if (cachedOutput == null)
            cachedOutput = getOutput(world);

        return cachedOutput;
    }
}
