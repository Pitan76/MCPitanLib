package net.pitan76.mcpitanlib.midohra.recipe;

import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.collection.DefaultedList;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.input.CraftingRecipeInputOrInventory;
import net.pitan76.mcpitanlib.midohra.world.World;

public class CraftingRecipe extends Recipe {
    private final net.minecraft.recipe.CraftingRecipe recipe;

    protected CraftingRecipe(net.minecraft.recipe.CraftingRecipe recipe) {
        super(null);
        this.recipe = recipe;
    }

    public static CraftingRecipe of(net.minecraft.recipe.CraftingRecipe recipe) {
        return new CraftingRecipe(recipe);
    }

    public net.minecraft.recipe.CraftingRecipe getRaw() {
        return recipe;
    }

    public net.minecraft.recipe.CraftingRecipe toMinecraft() {
        return getRaw();
    }

    public CraftingRecipeCategory getRawCategory() {
        return getRaw().getCategory();
    }

    public boolean matches(CraftingRecipeInputOrInventory input, World world) {
        return getRaw().matches(input.getRaw(), world.getRaw());
    }

    public net.minecraft.item.ItemStack craft(CraftingRecipeInputOrInventory input, CompatRegistryLookup registryLookup) {
        return getRaw().craft(input.getRaw(), registryLookup.getRegistryLookup());
    }

    public ItemStack craftMidohra(CraftingRecipeInputOrInventory input, CompatRegistryLookup registryLookup) {
        return ItemStack.of(craft(input, registryLookup));
    }

    public DefaultedList<net.minecraft.item.ItemStack> getRecipeRemaindersDefaultedList(CraftingRecipeInputOrInventory input) {
        return getRaw().getRecipeRemainders(input.getRaw());
    }

    public ItemStackList getRecipeRemainders(CraftingRecipeInputOrInventory input) {
        return ItemStackList.of(getRecipeRemaindersDefaultedList(input));
    }
}
