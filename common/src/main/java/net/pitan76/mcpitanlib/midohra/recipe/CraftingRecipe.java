package net.pitan76.mcpitanlib.midohra.recipe;

import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.core.NonNullList;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.input.CraftingRecipeInputOrInventory;
import net.pitan76.mcpitanlib.midohra.world.World;

public class CraftingRecipe extends Recipe {
    private final net.minecraft.world.item.crafting.CraftingRecipe recipe;

    protected CraftingRecipe(net.minecraft.world.item.crafting.CraftingRecipe recipe) {
        super(null);
        this.recipe = recipe;
    }

    public static CraftingRecipe of(net.minecraft.world.item.crafting.CraftingRecipe recipe) {
        return new CraftingRecipe(recipe);
    }

    public net.minecraft.world.item.crafting.CraftingRecipe getRaw() {
        return recipe;
    }

    public net.minecraft.world.item.crafting.CraftingRecipe toMinecraft() {
        return getRaw();
    }

    public CraftingBookCategory getRawCategory() {
        return getRaw().category();
    }

    public boolean matches(CraftingRecipeInputOrInventory input, World world) {
        return getRaw().matches(input.getRaw(), world.getRaw());
    }

    public net.minecraft.world.item.ItemStack craft(CraftingRecipeInputOrInventory input, World world) {
        return getRaw().assemble(input.getRaw());
    }

    public ItemStack craftMidohra(CraftingRecipeInputOrInventory input, World world) {
        return ItemStack.of(craft(input, world));
    }

    public net.minecraft.world.item.ItemStack craft(CraftingRecipeInputOrInventory input, CompatRegistryLookup registryLookup) {
        return getRaw().assemble(input.getRaw());
    }

    public net.minecraft.world.item.ItemStack craft(CraftingRecipeInputOrInventory input, net.minecraft.world.level.Level world) {
        return getRaw().assemble(input.getRaw());
    }

    public NonNullList<net.minecraft.world.item.ItemStack> getRecipeRemaindersDefaultedList(CraftingRecipeInputOrInventory input) {
        return getRaw().getRemainingItems(input.getRaw());
    }

    public ItemStackList getRecipeRemainders(CraftingRecipeInputOrInventory input) {
        return ItemStackList.of(getRecipeRemaindersDefaultedList(input));
    }

    public net.minecraft.world.item.ItemStack getOutput(CraftingRecipeInputOrInventory input, net.minecraft.world.level.Level world) {
        return craft(input, world);
    }

    public net.minecraft.world.item.ItemStack getOutput(CraftingRecipeInputOrInventory input, World world) {
        return getOutput(input, world.getRaw());
    }

    public ItemStack getOutputMidohra(CraftingRecipeInputOrInventory input, World world) {
        return ItemStack.of(getOutput(input, world));
    }
}
