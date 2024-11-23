package net.pitan76.mcpitanlib.midohra.recipe.input;

import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.input.RecipeInput;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class RecipeInputOrInventory {
    private final RecipeInput recipeInput;

    public static RecipeInputOrInventory NONE = new RecipeInputOrInventory(null);

    protected RecipeInputOrInventory(RecipeInput recipeInput) {
        this.recipeInput = recipeInput;
    }

    public static RecipeInputOrInventory of(RecipeInput recipeInput) {
        return new RecipeInputOrInventory(recipeInput);
    }

    public static RecipeInputOrInventory of(Inventory inventory) {
        if (inventory instanceof RecipeInput) {
            return of((RecipeInput) inventory);
        }

        return NONE;
    }

    @Nullable
    public RecipeInput getRaw() {
        return recipeInput;
    }

    @Nullable
    public RecipeInput toMinecraft() {
        return getRaw();
    }

    @Nullable
    public RecipeInput getRecipeInput() {
        return getRaw();
    }

    @Nullable
    public Inventory getInventory() {
        if (getRaw() instanceof Inventory) {
            return (Inventory) getRaw();
        }

        return null;
    }

    public boolean isNone() {
        return getRaw() == null;
    }

    public int size() {
        if (isNone()) return 0;

        return getRaw().size();
    }

    public boolean isEmpty() {
        if (isNone()) return true;

        return getRaw().isEmpty();
    }

    public net.minecraft.item.ItemStack getStack(int slot) {
        if (isNone()) return net.minecraft.item.ItemStack.EMPTY;

        return getRaw().getStackInSlot(slot);
    }

    public ItemStack getMidohraStack(int slot) {
        if (isNone()) return ItemStack.EMPTY;

        return ItemStack.of(getStack(slot));
    }
}
