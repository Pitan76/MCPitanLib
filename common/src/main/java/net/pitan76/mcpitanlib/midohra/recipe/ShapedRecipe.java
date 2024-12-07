package net.pitan76.mcpitanlib.midohra.recipe;

import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.recipe.input.CraftingRecipeInputOrInventory;

public class ShapedRecipe extends CraftingRecipe {
    private final net.minecraft.recipe.ShapedRecipe recipe;

    protected ShapedRecipe(net.minecraft.recipe.ShapedRecipe recipe) {
        super(null);
        this.recipe = recipe;
    }

    public static ShapedRecipe of(net.minecraft.recipe.ShapedRecipe recipe) {
        return new ShapedRecipe(recipe);
    }

    public net.minecraft.recipe.ShapedRecipe getRaw() {
        return recipe;
    }

    public net.minecraft.recipe.ShapedRecipe toMinecraft() {
        return getRaw();
    }

    public boolean matches(CraftingRecipeInputOrInventory input) {
        return getRaw().matches(input.getRaw(), null);
    }

    @Deprecated
    public net.minecraft.item.ItemStack craft() {
        return getRaw().craft(null, null);
    }

    public net.minecraft.item.ItemStack craft(CompatRegistryLookup registryLookup) {
        if (registryLookup instanceof DynamicRegistryManager)
            return getRaw().craft(null, (DynamicRegistryManager) registryLookup.getRegistryLookup());

        DynamicRegistryManager manager = DynamicRegistryManager.of(Registries.REGISTRIES);
        return getRaw().craft(null, manager);
    }

    public net.minecraft.item.ItemStack craft(World world) {
        return getRaw().craft(null, world.getRegistryManager());
    }

    public net.minecraft.item.ItemStack craft(net.pitan76.mcpitanlib.midohra.world.World world) {
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
