package net.pitan76.mcpitanlib.midohra.recipe;

import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.input.CraftingRecipeInputOrInventory;

public class ShapedRecipe extends CraftingRecipe {
    private final net.minecraft.world.item.crafting.ShapedRecipe recipe;

    protected ShapedRecipe(net.minecraft.world.item.crafting.ShapedRecipe recipe) {
        super(null);
        this.recipe = recipe;
    }

    public static ShapedRecipe of(net.minecraft.world.item.crafting.ShapedRecipe recipe) {
        return new ShapedRecipe(recipe);
    }

    public net.minecraft.world.item.crafting.ShapedRecipe getRaw() {
        return recipe;
    }

    public net.minecraft.world.item.crafting.ShapedRecipe toMinecraft() {
        return getRaw();
    }

    public boolean matches(CraftingRecipeInputOrInventory input) {
        return getRaw().matches(input.getRaw(), null);
    }

    @Deprecated
    public net.minecraft.world.item.ItemStack craft() {
        return getRaw().assemble(null);
    }

    public net.minecraft.world.item.ItemStack craft(CompatRegistryLookup registryLookup) {
        return getRaw().assemble(null);
    }

    public net.minecraft.world.item.ItemStack craft(Level world) {
        return getRaw().assemble(null);
    }

    public net.minecraft.world.item.ItemStack craft(net.pitan76.mcpitanlib.midohra.world.World world) {
        return craft(world.getRaw());
    }

    public ItemStack craftMidohra() {
        return ItemStack.of(craft());
    }

    public int getWidth() {
        return getRaw().getWidth();
    }

    public int getHeight() {
        return getRaw().getHeight();
    }
}
