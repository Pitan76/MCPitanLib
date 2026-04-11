package net.pitan76.mcpitanlib.midohra.recipe.input;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeInput;
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

    public static RecipeInputOrInventory of(Container inventory) {
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
    public Container getInventory() {
        if (getRaw() instanceof Container) {
            return (Container) getRaw();
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

    public net.minecraft.world.item.ItemStack getStack(int slot) {
        if (isNone()) return net.minecraft.world.item.ItemStack.EMPTY;

        return getRaw().getItem(slot);
    }

    public ItemStack getMidohraStack(int slot) {
        if (isNone()) return ItemStack.EMPTY;

        return ItemStack.of(getStack(slot));
    }
}
