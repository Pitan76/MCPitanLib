package net.pitan76.mcpitanlib.midohra.recipe.input;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import org.jetbrains.annotations.Nullable;

public class CraftingRecipeInputOrInventory extends RecipeInputOrInventory {
    private final CraftingRecipeInput recipeInput;
    private CraftingInventory inventory = null;

    public static CraftingRecipeInputOrInventory EMPTY = new CraftingRecipeInputOrInventory(CraftingRecipeInput.EMPTY);

    protected CraftingRecipeInputOrInventory(CraftingRecipeInput recipeInput) {
        super(null);
        this.recipeInput = recipeInput;
    }

    protected CraftingRecipeInputOrInventory(CraftingInventory inventory) {
        super(null);
        this.recipeInput = inventory.createRecipeInput();
        this.inventory = inventory;
    }

    public static CraftingRecipeInputOrInventory of(CraftingRecipeInput recipeInput) {
        return new CraftingRecipeInputOrInventory(recipeInput);
    }

    public static CraftingRecipeInputOrInventory of(Inventory inventory) {
        if (inventory instanceof CraftingRecipeInput) {
            return of((CraftingRecipeInput) inventory);
        }

        return EMPTY;
    }

    public static CraftingRecipeInputOrInventory of(CraftingInventory inventory) {
        return new CraftingRecipeInputOrInventory(inventory);
    }

    @Nullable
    @Override
    public CraftingRecipeInput getRaw() {
        return recipeInput;
    }

    @Nullable
    @Override
    public CraftingRecipeInput toMinecraft() {
        return getRaw();
    }

    @Nullable
    @Override
    public CraftingRecipeInput getRecipeInput() {
        return getRaw();
    }

    @Nullable
    @Override
    public Inventory getInventory() {
        if (inventory != null)
            return inventory;

        return super.getInventory();
    }
}
