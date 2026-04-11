package net.pitan76.mcpitanlib.midohra.recipe.input;

import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.CraftingInput;
import org.jetbrains.annotations.Nullable;

public class CraftingRecipeInputOrInventory extends RecipeInputOrInventory {
    private final CraftingInput recipeInput;
    private TransientCraftingContainer inventory = null;

    public static CraftingRecipeInputOrInventory EMPTY = new CraftingRecipeInputOrInventory(CraftingInput.EMPTY);

    protected CraftingRecipeInputOrInventory(CraftingInput recipeInput) {
        super(null);
        this.recipeInput = recipeInput;
    }

    protected CraftingRecipeInputOrInventory(TransientCraftingContainer inventory) {
        super(null);
        this.recipeInput = inventory.asCraftInput();
        this.inventory = inventory;
    }

    public static CraftingRecipeInputOrInventory of(CraftingInput recipeInput) {
        return new CraftingRecipeInputOrInventory(recipeInput);
    }

    public static CraftingRecipeInputOrInventory of(Container inventory) {
        if (inventory instanceof CraftingInput) {
            return of((CraftingInput) inventory);
        }

        return EMPTY;
    }

    public static CraftingRecipeInputOrInventory of(TransientCraftingContainer inventory) {
        return new CraftingRecipeInputOrInventory(inventory);
    }

    @Nullable
    @Override
    public CraftingInput getRaw() {
        return recipeInput;
    }

    @Nullable
    @Override
    public CraftingInput toMinecraft() {
        return getRaw();
    }

    @Nullable
    @Override
    public CraftingInput getRecipeInput() {
        return getRaw();
    }

    @Nullable
    @Override
    public Container getInventory() {
        if (inventory != null)
            return inventory;

        return super.getInventory();
    }
}
