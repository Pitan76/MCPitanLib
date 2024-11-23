package net.pitan76.mcpitanlib.midohra.recipe.input;

import net.minecraft.inventory.Inventory;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class RecipeInputOrInventory {
    private final Inventory recipeInput;

    public static RecipeInputOrInventory NONE = new RecipeInputOrInventory(null);

    protected RecipeInputOrInventory(Inventory recipeInput) {
        this.recipeInput = recipeInput;
    }

    public static RecipeInputOrInventory of(Inventory recipeInput) {
        return new RecipeInputOrInventory(recipeInput);
    }

    @Nullable
    public Inventory getRaw() {
        return recipeInput;
    }

    @Nullable
    public Inventory toMinecraft() {
        return getRaw();
    }

    @Nullable
    public Inventory getRecipeInput() {
        return getRaw();
    }

    @Nullable
    public Inventory getInventory() {
        return getRaw();
    }

    public boolean isNone() {
        return getRaw() == null;
    }

    public int size() {
        if (isNone()) return 0;

        return getRaw().getSize();
    }

    public boolean isEmpty() {
        if (isNone()) return true;

        return getRaw().isEmpty();
    }

    public net.minecraft.item.ItemStack getStack(int slot) {
        if (isNone()) return net.minecraft.item.ItemStack.EMPTY;

        return getRaw().getStack(slot);
    }

    public ItemStack getMidohraStack(int slot) {
        if (isNone()) return ItemStack.EMPTY;

        return ItemStack.of(getStack(slot));
    }
}
